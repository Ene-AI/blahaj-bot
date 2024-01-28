<template>
    <v-layout>
        <AppBar/>
        <v-main>
            <!-- table-->
            <v-container>
                <v-row>
                    <v-col>
                        <h1 class="text-center">View Jobs</h1>
                        <v-btn color='primary' :to="addlink">Add Jobs</v-btn>
                        <v-table>
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Label</th>
                                    <th scope="col">Message</th>
                                    <th scope="col">Minute</th>
                                    <th scope="col">Hour</th>
                                    <th scope="col">Day Of Month</th>
                                    <th scope="col">Month</th>
                                    <th scope="col">Day Of Week</th>
                                    <th scope="col">Repeat</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="scheduledMessage in scheduledMessages" :key="scheduledMessage.id.toString()">
                                    <th scope="row">{{ scheduledMessage.id.toString() }}</th>
                                    <td>{{ scheduledMessage.label }}</td>
                                    <td>{{ scheduledMessage.message }}</td>
                                    <td>{{ scheduledMessage.minute }}</td>
                                    <td>{{ scheduledMessage.hour }}</td>
                                    <td>{{ scheduledMessage.dayOfMonth }}</td>
                                    <td>{{ scheduledMessage.month }}</td>
                                    <td>{{ scheduledMessage.dayOfWeek }}</td>
                                    <td>{{ scheduledMessage.repeat }}</td>
                                    <td class="d-flex flex-wrap ga-3">
                                        <v-btn color='primary' :to="editlink + `${scheduledMessage.id}`">Edit</v-btn>

                                        <v-btn color='red' @click="deleteScheduledMessage(scheduledMessage.id)">Delete</v-btn>
                                    </td>
                                </tr>
                            </tbody>
                        </v-table>
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
                scheduledMessages: [],
                addlink: '',
                editlink: ''
            }
        },
        beforeMount(){
            let route = useRoute();
            watch(route, () => {this.getScheduledMessages(this.$route.params.guildId), this.addlink="/" + this.$route.params.guildId + "/add"});
            this.getScheduledMessages(this.$route.params.guildId);
            this.addlink="/" + this.$route.params.guildId + "/add";
            this.editlink="/" + this.$route.params.guildId + "/edit/";
        },
        methods: {
            getScheduledMessages(guildId){
                if(guildId){
                    fetch(`${this.$store.getters.backendLink}/${this.$route.params.guildId}/scheduled_messages`, {
                    })
                    .then(res => res.json())
                    .then(data => {
                        this.scheduledMessages = data
                        console.log(data)
                    })
                }
            },
            deleteScheduledMessage(id){
                fetch(`${this.$store.getters.backendLink}/${this.$route.params.guildId}/scheduled_message/${id}`, {
                    method: 'DELETE'
                })
                .then(data => {
                    console.log(data)
                    this.getScheduledMessages(this.$route.params.guildId)
                })
            },
            editScheduledMessage(){

            }
        }
    }

</script>