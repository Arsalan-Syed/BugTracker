import axios from "axios";

const API_URL = "http://localhost:8080/";

const options = {
    headers: { 'Content-Type': 'application/json' }
};

function login(username, password) {
    axios.post(API_URL+"login", {
        username: username,
        password: password
    }, options)
    .then(response => {
        localStorage.setItem("authToken", response.data["authToken"]) //TODO switch to cookies instead of localstorage
        return response.data;
    })
    .catch(error => {
        console.log(error)
    });
}

//TODO redirect
function logout(){
    localStorage.removeItem("authToken");
}

function register(username, email, password) {
    axios.post(API_URL+"login", {
        username: username,
        password: password
    }, options)
}

function authHeader(){
    let token = localStorage.getItem("authToken");
    return "Bearer "+token;
}

export const authenticationService = {
    login,
    logout,
    register,
    authHeader
};