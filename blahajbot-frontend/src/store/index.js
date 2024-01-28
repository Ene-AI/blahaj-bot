import { createStore, createLogger } from 'vuex';
import axios from 'axios'

const SET_BACKEND_LINK = "SET_BACKEND_LINK";
const SET_USER = "SET_USER";
const SET_CLIENT_ID = "SET_CLIENT_ID"

export default createStore({
  state: {
    currentUser: JSON.parse(localStorage.getItem("currentUser")) || {},
    backendLink: "",
    clientId: ""
  },
  mutations: {
    [SET_USER](state, payload) {
      state.currentUser = payload;
    },
    [SET_BACKEND_LINK](state, payload) {
      state.backendLink = payload;
    },
    [SET_CLIENT_ID](state, payload) {
      state.clientId = payload;
    }
  },
  actions: {
    fetchUser({commit}) {
      return axios.get(this.state.backendLink + "/users/@me", {withCredentials: true}).then(response => {
        commit(SET_USER, response.data);
        localStorage.setItem("currentUser", JSON.stringify(this.state.currentUser));
      }).catch(() => {
        commit(SET_USER, {});
      });
    },
    fetchClientId({commit}) {
      return axios.get(this.state.backendLink + "/clientId", {withCredentials: true, dataType: "json"}).then(response => {
        commit(SET_CLIENT_ID, response.data);
      });
    },
    logout({commit}) {
      commit(SET_USER, {});
      localStorage.removeItem("currentUser");
    }
  },
  getters: {
    isAuthenticated: (state) => {
      return JSON.stringify(state.currentUser).includes("name");
    },
    backendLink: (state) => {
      return state.backendLink;
    },
    clientId: (state) => {
      return state.clientId;
    }
  }
});
