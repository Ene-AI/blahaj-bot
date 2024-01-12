<template>
    <main>
        <NavBar/>
        <div class="my-5">
            <div class="mx-auto w-25" style="max-width: 100%">
                <h2 class="text-center mb-3">Add Job</h2>
                <form @submit.prevent="addScheduledMessage">
                    <!--guildId-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="guildId" class="form-label">Guild ID</label>
                            <input type="text" name="guildId" id="guildId" class="form-control" placeholder="guildId" required v-model="scheduledMessage.guildId">
                        </div>
                    </div>
                    <!--channelId-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="channelId" class="form-label">Channel Id</label>
                            <input type="text" name="channelId" id="channelId" class="form-control" placeholder="channelId" required v-model="scheduledMessage.channelId">
                        </div>
                    </div>
                    <!--name-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="label" class="form-label">Label</label>
                            <input type="text" name="label" id="label" class="form-control" placeholder="label" required v-model="scheduledMessage.label">
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
                            <input type="checkbox" value="repeat" id="repeat" v-model="scheduledMessage.repeat">
                        </div>
                    </div>
                    <!--minute-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="minute" class="form-label">Minute</label>
                            <input type="text" value="minute" id="minute" class="form-control" placeholder=-1 v-model.number="scheduledMessage.minute">
                        </div>
                    </div>
                    <!--hour-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="hour" class="form-label">Hour</label>
                            <input type="text" value="hour" id="hour" class="form-control" placeholder=-1 v-model.number="scheduledMessage.hour">
                        </div>
                    </div>
                    <!--dayOfMonth-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="dayOfMonth" class="form-label">Day Of Month</label>
                            <input type="text" value="dayOfMonth" id="dayOfMonth" class="form-control" placeholder=-1 v-model.number="scheduledMessage.dayOfMonth">
                        </div>
                    </div>
                    <!--month-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="month" class="form-label">Month</label>
                            <input type="text" value="month" id="month" class="form-control" placeholder=-1 v-model.number="scheduledMessage.month">
                        </div>
                    </div>
                    <!--dayOfWeek-->
                    <div class="row">
                        <div class="col-md-12 form-group mb-3">
                            <label for="dayOfWeek" class="form-label">Day Of Week</label>
                            <input type="text" value="dayOfWeek" id="dayOfWeek" class="form-control" placeholder=-1 v-model.number="scheduledMessage.dayOfWeek">
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
import NavBar from '../components/NavBar.vue'

export default {
    name: 'AddScheduledMessage',
    components: {
        NavBar
    },

    data() {
        return {
            scheduledMessage: {
                guildId: '',
                channelId: '',
                label: '',
                message: '',
                repeat: false,

                minute: 0,
                hour: -1,
                dayOfMonth: -1,
                month: -1,
                dayOfWeek: -1,
            }
        }
    },

    methods: {
        addScheduledMessage(){
            fetch('http://localhost:8080/add',{
                method:'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.scheduledMessage)
            })
            .then(data => {
                console.log(data)
                this.$router.push("/")
            })
        }
    }
}
</script>