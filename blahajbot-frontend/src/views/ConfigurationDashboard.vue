<template>
    <v-layout>
        <AppBar/>
        <v-main>
            <v-container>
                <v-row>
                    <v-col>
                    <h2 class="text-center mb-3">Bot Configuration</h2>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col
                        cols="4"
                    >
                        <v-card color="blue-lighten-4">
                            <v-container fluid>
                                <v-row>
                                    <v-col>

                                        Use Scheduled Messages:
                                    </v-col>
                                    <v-col
                                        cols="2"
                                    >
                                        <v-switch
                                            v-model='guildConfig.useScheduledMessages'
                                            @update:modelValue="updateConfig" 
                                        >
                                        </v-switch>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col>

                                    </v-col>
                                    <v-col
                                        cols="2"
                                    >
                                        <v-btn
                                            class="ma-2"
                                            color="blue"
                                            icon="mdi-wrench"
                                            :to="scheduledMessagesConfigLink"
                                        ></v-btn>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
    </v-layout>
</template>

<script>

import AppBar from '../components/AppBar.vue'
import { useRoute } from 'vue-router'
import { watch } from 'vue'

    export default {
        name: 'ViewScheduledMessages',
        components: {
            AppBar
        },
        data() {
            return {
                guildConfig: {
                    guildId: '',
                    useScheduledMessages: false,
                },
                scheduledMessagesConfigLink: ''
            }
        },
        beforeMount(){
            let route = useRoute();
            watch(route, () => {this.getConfig(this.$route.params.guildId), this.scheduledMessagesConfigLink="/" + this.$route.params.guildId + "/scheduled_messages"});
            this.getConfig(this.$route.params.guildId);
            this.scheduledMessagesConfigLink="/" + this.$route.params.guildId + "/scheduled_messages";
        },
        methods: {
            getConfig(guildId){
                if(guildId){
                    fetch(`${this.$store.getters.backendLink}/${this.$route.params.guildId}/config`, {
                    })
                    .then(res => res.json())
                    .then(data => {
                        this.guildConfig = data
                        console.log(data)
                    })
                }
            },
            updateConfig(guildId){
                fetch(`${this.$store.getters.backendLink}/${this.$route.params.guildId}/config`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.guildConfig)
                })
                .then(data => {
                    console.log(data);
                })
            }
        }
    }

</script>