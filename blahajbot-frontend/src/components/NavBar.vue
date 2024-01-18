<template>
    <v-app-bar color="blue">
        <v-avatar rounded="0" size="x-large">
            <v-img :src="blahajshark"></v-img>
        </v-avatar>
        <v-app-bar-title class="text-h5 font-weight-black text-uppercase">Blahajbot</v-app-bar-title>
        <v-avatar v-if="guild" v-bind:src="guild.icon">
                    <v-img v-bind:src="guild.icon"></v-img>
        </v-avatar>
        <div class="pt-3 ml-2 mr-5">
            <v-select bg-color="light-blue-lighten-3" width=30px label="Server Select" variant="solo"  :items="joinedGuilds" v-model='guild' return-object item-title="name" @update:modelValue="updateGuild">
                <template v-slot:item="{ props, item }">
                    <v-list-item v-bind="props" :prepend-avatar="item.raw.icon">
                    </v-list-item>
                </template>
                <template v-slot:append-item>
                    <v-divider></v-divider>           
                    <v-list>
                        <v-list-item v-for="guild in unjoinedGuilds" :title="guild.name" append-icon="mdi-plus" :prepend-avatar="guild.icon" :href="'https://discord.com/oauth2/authorize?client_id=1075956771425890354&permissions=8&guild_id='+ guild.id + '&response_type=code&redirect_uri=http%3A%2F%2Flocalhost%3A5173%2F&scope=bot+identify+guilds'">
                        </v-list-item>
                    </v-list>
                </template>
            </v-select>
        </div>
        <div>
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
                href ="/logout"
                >
                Logout
                </v-btn>
            </v-menu>
            <v-btn
                color="secondary"
                v-bind="props"
                href="http://localhost:8080/discord/callback"
                v-else
                >
                Login with Discord
                </v-btn>
        </div>
    </v-app-bar>
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
            blahajshark: ikeasblahajshark
        }
    },
    setup() {
        const store = useStore()
        store.dispatch("fetchUser")
        let currentUser = localStorage.getItem("currentUser")
        if (currentUser) {
            let user = JSON.parse(currentUser)
            return {
                user: user,
                guilds: user.guilds,
            }
        }
        return {
            guilds: []
        }
    },
    beforeMount(){
        if(this.guilds){
            this.guild = this.guilds.find((guild) => guild.id == useRoute().params.guildId)
            this.getBotGuilds()
            console.log(this.unjoinedGuilds)
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
                this.$router.push("/" + this.guild.id + "/scheduled_messages")
            }
        },
        login() {
        window.location.href = "http://localhost:8080/discord/callback";
        },
        getBotGuilds(){
            fetch(`http://localhost:8080/botGuilds`, {
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
        }
    }
}
</script>