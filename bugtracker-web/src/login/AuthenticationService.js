import {restClient} from "../utils/RestClient";

async function login(username, password) {
    let data = {
        username: username,
        password: password
    }

    const response = await restClient.post("login", data);
    localStorage.setItem("authToken", response["authToken"]) //TODO switch to cookies instead of localstorage
}

//TODO redirect
function logout(){
    localStorage.removeItem("authToken");
}

//TODO
function register(username, email, password) {
}

export const authenticationService = {
    login
    //,
    //logout,
    //register
};