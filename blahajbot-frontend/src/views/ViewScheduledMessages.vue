<template>
    <main>
        <NavBar />

        <!-- table-->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">View Jobs</h1>

                    <a href="/add" class="btn btn-primary">Add Jobs</a>
                    <table class="table table-striped">
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
                                <td>
                                    <a class="btn btn-primary" :href="`/edit/${scheduledMessage.id}`">Edit</a>
                                    <button class="btn btn-danger mx-2" @click="deleteScheduledMessage(scheduledMessage.id)">Delete</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>
</template>


<script>

import NavBar from '../components/NavBar.vue'

    export default {
        name: 'ViewScheduledMessages',
        components: {
            NavBar
        },
        data() {
            return {
                scheduledMessages: []
            }
        },

        beforeMount(){
            this.getScheduledMessages()
        },

        methods: {
            getScheduledMessages(){
                fetch('http://localhost:8080/scheduled-messages')
                .then(res => res.json())
                .then(data => {
                    this.scheduledMessages = data
                    console.log(data)
                })
            },
            deleteScheduledMessage(id){
                fetch(`http://localhost:8080/scheduled-message/${id}`, {
                    method: 'DELETE'
                })
                .then(data => {
                    console.log(data)
                    this.getScheduledMessages()
                })
            }

        }
    }

</script>