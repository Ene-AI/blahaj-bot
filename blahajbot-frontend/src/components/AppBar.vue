<template>
    <v-app-bar color="blue">
        <v-avatar rounded="0" size="x-large">
            <v-img :src="blahajshark"></v-img>
        </v-avatar>
        <v-app-bar-title class="text-h5 font-weight-black text-uppercase">Blahajbot</v-app-bar-title>
        <v-avatar v-if="guild" v-bind:src="guild.icon">
                    <v-img v-bind:src="guild.icon"></v-img>
        </v-avatar>
        <div class="pt-3 ml-2 mr-5 w-25" v-if="user">
            <v-select bg-color="light-blue-lighten-3" width=30px label="Server Select" variant="solo"  :items="joinedGuilds" v-model='guild' return-object item-title="name" @update:modelValue="updateGuild">
                <template v-slot:item="{ props, item }">
                    <v-list-item v-bind="props" :prepend-avatar="item.raw.icon">
                    </v-list-item>
                </template>
                <template v-slot:append-item>
                    <v-divider></v-divider>           
                    <v-list>
                        <v-list-item v-for="guild in unjoinedGuilds" :title="guild.name" append-icon="mdi-plus" :prepend-avatar="guild.icon" :href="'https://discord.com/oauth2/authorize?client_id=' + clientId + '&permissions=8&guild_id='+ guild.id + '&response_type=code&redirect_uri=' + encodedUri + '&scope=bot+identify+guilds'">
                        </v-list-item>
                    </v-list>
                </template>
            </v-select>
        </div>
        <div class="ml-2 mr-5">
            <v-menu
            open-on-hover
            v-if="user"
            >
            <template v-slot:activator="{ props }">
                <v-btn
                v-bind="props"
                >
                <v-avatar v-bind:src='user.avatar'>
                    <v-img v-bind:src="user.avatar"></v-img>
                </v-avatar>
                <div class="ml-2" v-text="user.username"></div>
                </v-btn>
            </template>
                <v-btn
                color="secondary"
                @click="logout"
                >
                Logout
                </v-btn>
            </v-menu>
            <v-btn
                color="white"
                v-bind="props"
                :href="$store.getters.backendLink + '/discord/callback'"
                v-else
                >
                Login with Discord
                </v-btn>
        </div>
    </v-app-bar>
    <v-navigation-drawer v-if="guild" color="blue-darken-4">
        <v-list>
            <router-link 
                class="text-white text-decoration-none"
                :to="{path:'/' + guild.id + '/config'}">
                <v-list-item
                prepend-icon="mdi-home"
                title="Home"
                >
                </v-list-item>
            </router-link>
            <router-link 
                class="text-white text-decoration-none"
                :to="{path:'/' + guild.id + '/scheduled_messages'}">
                <v-list-item 
                    prepend-icon="mdi-clock" 
                    title="Scheduled Messages" 
                ></v-list-item>
            </router-link>
        </v-list>

    </v-navigation-drawer>
</template>


<script>
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import ikeasblahajshark from '@/assets/ikeasblahajshark.jpg'

export default {
    name: 'NavBar',
    data() {
        return {
            guild: {
                id: '',
                name: ''
            },
            joinedGuilds: [],
            unjoinedGuilds: [],
            blahajshark: ikeasblahajshark,
            encodedUri: encodeURI(window.location)
        }
    },
    async setup() {
        const store = useStore()
        await store.dispatch("fetchUser")
        let currentUser = localStorage.getItem("currentUser")
        await store.dispatch("fetchClientId")
        let clientId = "" + store.getters.clientId
        if (currentUser) {
            let user = JSON.parse(currentUser)
            return {
                user: user,
                guilds: user.guilds,
                clientId: clientId
            }
        }
        return {
            guilds: []
        }
    },
    mounted(){
        if(this.guilds){
            this.guild = this.guilds.find((guild) => guild.id == useRoute().params.guildId)
            this.getBotGuilds()
            console.log(this.unjoinedGuilds)
        }
        let currentUser = localStorage.getItem("currentUser")
        if(currentUser && !this.user){
            this.$router.go();
        }
    },

    methods: {
        updateGuild(){
            if(this.$route.params.guildId){  
                this.$router.push({
                    params: { guildId: this.guild.id}
                })
            }
            else{
                this.$router.push("/" + this.guild.id + "/config")
            }
        },
        getBotGuilds(){
            fetch(`${this.$store.getters.backendLink}/botGuilds`, {
            })
            .then(res => res.json())
            .then(data => {
                this.guilds.forEach((e) => {
                    if(data.includes(e.id)) {
                        this.joinedGuilds.push(e);
                    }
                    else {
                        this.unjoinedGuilds.push(e);
                    }
                })
            })
        },
        logout(){
            this.$store.dispatch("logout")
            fetch(`${this.$store.getters.backendLink}/logout`, {
                method: 'POST'
            })
            this.$router.go("/")
        }
    }
}
</script>