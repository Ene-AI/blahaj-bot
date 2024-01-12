<template>
    <main>
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">Blahajbot</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="navbarSupportContent" aria-controls="navbarSupportContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a href="/" class="nav-link">Home</a>
                        </li>
                        <li class="nav-item">
                            <a href="/add" class="nav-link">Add</a>
                        </li>
                    </ul>
                </div>
                <a id="login" v-if="access_token.length == 0" href="https://discord.com/api/oauth2/authorize?client_id=1075956771425890354&response_type=token&redirect_uri=http%3A%2F%2Flocalhost%3A5173%2F&scope=identify+guilds">
                    Login Using Discord
                </a>
                <div class="dropdown" v-if="access_token.length > 0">
                    <button class="btn btn-secondary dropdown-toggle" type="button" id="info" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    </button>
                    <div class="dropdown-menu" aria-labelledby="id">
                        <a class="dropdown-item" href="#">Logout</a>
                    </div>
                </div>
            </div>
        </nav>
    </main>
</template>


<script>
export default {
    name: 'NavBar',
    data() {
        return {
            access_token: '',
            token_type: ''
        }
    },
    methods: {
    }
}
</script>