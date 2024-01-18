import { createStore, createLogger } from 'vuex';
import axios from 'axios'

const SET_USER = "SET_USER";

export default createStore({
  state: {
    currentUser: JSON.parse(localStorage.getItem("currentUser")) || {},
  },
  mutations: {
    [SET_USER](state, payload) {
      state.currentUser = payload;
    }
  },
  actions: {
    fetchUser({commit}) {
      axios.get("http://localhost:8080/users/@me", {withCredentials: true}).then(response => {
        commit(SET_USER, response.data);
        localStorage.setItem("currentUser", JSON.stringify(this.state.currentUser));
      }).catch(() => {
        commit(SET_USER, {});
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
  }
});
