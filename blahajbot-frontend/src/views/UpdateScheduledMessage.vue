<template>    
    <v-layout>
        <NavBar/>
        <v-main>
            <div class="my-5">
                <div class="mx-auto w-25" style="max-width: 100%">
                    <h2 class="text-center mb-3">Add Job</h2>
                    <v-form @submit.prevent="updateScheduledMessage">
                        <v-container>
                            <v-row>  
                                <!--channelId-->
                                <v-col class="md-12 form-group mb-3">
                                    <label for="channelId" class="form-label">Channel Id</label>
                                    <input type="text" name="channelId" id="channelId" class="form-control" placeholder="channelId" required v-model="scheduledMessage.channelId">
                                </v-col>
                            </v-row>
                            <v-row>
                                <!--name-->
                                <v-col class="col-md-12 form-group mb-3">
                                    <label for="label" class="form-label">Label</label>
                                    <input type="text" name="label" id="label" class="form-control" placeholder="label" required v-model="scheduledMessage.label">
                                </v-col>
                            </v-row>
                            <v-row>
                                <!--message-->
                                <v-col class="col-md-12 form-group mb-3">
                                    <label for="message" class="form-label">Message</label>
                                    <input type="text" name="message" id="message" class="form-control" placeholder="Message" required v-model="scheduledMessage.message">
                                </v-col>
                            </v-row>
                            <v-row>
                                <!--repeats-->
                                <v-col class="col-md-12 form-group mb-3">
                                    <v-checkbox label="Repeat" v-model="scheduledMessage.repeat"/>
                                </v-col>
                            </v-row>
                            <div v-if="scheduledMessage.repeat">
                                <v-row>
                                    <!--minute-->
                                    <v-col class="col-md-12 form-group mb-3">
                                        <label for="minute" class="form-label">Minute</label>
                                        <input type="text" value="minute" id="minute" class="form-control" placeholder=-1 v-model.number="scheduledMessage.minute">
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <!--hour-->
                                    <v-col class="col-md-12 form-group mb-3">
                                        <label for="hour" class="form-label">Hour</label>
                                        <input type="text" value="hour" id="hour" class="form-control" placeholder=-1 v-model.number="scheduledMessage.hour">
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <!--dayOfMonth-->
                                    <v-col class="col-md-12 form-group mb-3">
                                        <label for="dayOfMonth" class="form-label">Day Of Month</label>
                                        <input type="text" value="dayOfMonth" id="dayOfMonth" class="form-control" placeholder=-1 v-model.number="scheduledMessage.dayOfMonth">
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <!--month-->
                                    <v-col class="col-md-12 form-group mb-3">
                                        <label for="month" class="form-label">Month</label>
                                        <input type="text" value="month" id="month" class="form-control" placeholder=-1 v-model.number="scheduledMessage.month">
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <!--dayOfWeek-->
                                    <v-col class="col-md-12 form-group mb-3">
                                        <label for="dayOfWeek" class="form-label">Day Of Week</label>
                                        <input type="text" value="Day of Week" id="dayOfWeek" class="form-control" placeholder=-1 v-model.number="scheduledMessage.dayOfWeek">
                                    </v-col>
                                </v-row>
                            </div>
                            <div class="pb-5" v-else>
                                <v-row>
                                    <VueDatePicker v-model="date"></VueDatePicker>
                                </v-row>
                            </div>
                            <v-row>
                                <input type="submit" value="Submit" class="btn btn-primary w-100">
                            </v-row>
                        </v-container>
                    </v-form>
                </div>
            </div>
        </v-main>
    </v-layout>
</template>


<script>
import NavBar from '../components/NavBar.vue'
import VueDatePicker from '@vuepic/vue-datepicker'
import '@vuepic/vue-datepicker/dist/main.css'

export default {
    name: 'UpdateScheduledMessage',
    components: {
        NavBar,
        VueDatePicker
    },

    data(){
        return {
            scheduledMessage: {
                guildId: '',
                channelId: '',
                label: '',
                message: '',
                repeat: '',

                minute: '',
                hour: '',
                dayOfMonth: '',
                month: '',
                dayOfWeek: '',
            },
            date: null,
        }
    },

    beforeMount(){
        this.getScheduledMessage();
        this.setGuildId(this.$route.params.guildId);
    },

    methods: {
       getScheduledMessage(){
            fetch(`http://localhost:8080/${this.$route.params.guildId}/scheduled-message/${this.$route.params.id}`, {
                method: 'GET',
                credentials: 'include',
            })
            .then(res => res.json())
            .then(data => {
                this.scheduledMessage = data;
                console.log(this.scheduledMessage);
            })

        },
        updateScheduledMessage(){
            fetch(`http://localhost:8080/${this.$route.params.guildId}/scheduled-message`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.scheduledMessage)
            })
            .then(data => {
                console.log(data);
                this.$router.push(`/${this.$route.params.guildId}/scheduled_messages`);
            })
        },
        setGuildId(guildId){
            this.scheduledMessage.guildId = guildId;
        },
        setMessageFromDate(){
            this.scheduledMessage.minute=this.date.getMinutes()
            this.scheduledMessage.hour=this.date.getHours()
            this.scheduledMessage.dayOfMonth=this.date.getDate()
            this.scheduledMessage.month=this.date.getMonth() + 1
            this.scheduledMessage.year=this.date.getFullYear()
        },
    }
}

</script>