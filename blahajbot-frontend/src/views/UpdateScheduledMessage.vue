<template>
    <main>
        <NavBar/>
        <div class="my-5">
            <div class="mx-auto w-25 " style="max-width:100%;">
              <h2 class="text-center mb-3">Update Scheduled Message</h2>
              <form @submit.prevent="updateScheduledMessage">
                    <!--guildId-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="guildId" class="form-label">Guild ID</label>
                            <input type="text" name="guildId" id="guildId" class="form-control" placeholder="Guild Id" required v-model="scheduledMessage.guildId">
                        </div>
                    </div>
                    <!--channelId-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="channelId" class="form-label">Channel Id</label>
                            <input type="text" name="channelId" id="channelId" class="form-control" placeholder="Channel Id" required v-model="scheduledMessage.channelId">
                        </div>
                    </div>
                    <!--name-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="label" class="form-label">Label</label>
                            <input type="text" name="label" id="label" class="form-control" placeholder="Label" required v-model="scheduledMessage.label">
                        </div>
                    </div>
                    <!--message-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="message" class="form-label">Message</label>
                            <input type="text" name="message" id="message" class="form-control" placeholder="Message" required v-model="scheduledMessage.message">
                        </div>
                    </div>
                    <!--repeats-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="repeat" class="form-label">Repeat</label>
                            <input type="checkbox" id="repeat" v-model="scheduledMessage.repeat">
                        </div>
                    </div>
                    <!--minute-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="minute" class="form-label">Minute</label>
                            <input type="text" id="minute" class="form-control" v-model.number="scheduledMessage.minute">
                        </div>
                    </div>
                    <!--hour-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="hour" class="form-label">Hour</label>
                            <input type="text" id="hour" class="form-control" v-model.number="scheduledMessage.hour">
                        </div>
                    </div>
                    <!--dayOfMonth-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="dayOfMonth" class="form-label">Day Of Month</label>
                            <input type="text" id="dayOfMonth" class="form-control" v-model.number="scheduledMessage.dayOfMonth">
                        </div>
                    </div>
                    <!--month-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="month" class="form-label">Month</label>
                            <input type="text" id="month" class="form-control" v-model.number="scheduledMessage.month">
                        </div>
                    </div>
                    <!--dayOfWeek-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="dayOfWeek" class="form-label">Day Of Week</label>
                            <input type="text" id="dayOfWeek" class="form-control" v-model.number="scheduledMessage.dayOfWeek">
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-md-12 form-group">
                            <input type="submit" value="Submit" class="btn btn-primary w-100">
                        </div>
                    </div>
                </form>        
            </div>
          </div>

    </main>
</template>


<script>
import Navbar from '../components/NavBar.vue';

export default {
    name: 'UpdateScheduledMessage',
    components: {
        Navbar
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
            }
        }
    },

    beforeMount(){
        this.getScheduledMessages();
    },

    methods: {
        getScheduledMessages(){
            fetch(`http://localhost:8080/scheduled-message/${this.$route.params.id}`)
            .then(res => res.json())
            .then(data => {
                this.scheduledMessage = data;
                console.log(this.scheduledMessage);
            })

        },
        updateScheduledMessage(){
            fetch(`http://localhost:8080/scheduled-message`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.scheduledMessage)
            })
            .then(data => {
                console.log(data);
                this.$router.push('/');
            })
        }
    }
}

</script>