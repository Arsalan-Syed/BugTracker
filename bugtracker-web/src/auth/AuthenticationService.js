import {restClient} from "../utils/RestClient";

async function login(username, password) {
    let data = {
        username: username,
        password: password
    }

    try{
        const response = await restClient.post("login", data);
        localStorage.setItem("authToken", response["authToken"]) //TODO switch to cookies instead of localstorage
    } catch(e){
        
    }
}

//TODO redirect
function logout(){
    localStorage.removeItem("authToken");
}

async function register(firstName, lastName, username, password, matchPassword, email) {
    let data = {
        username: username,
        password: password,
        matchPassword: matchPassword,
        email: email,
        name: {
            firstName: firstName,
            middleName: "", //TODO
            lastName: lastName
        }
    }

    try{
        const response = await restClient.postUnAuthenticated("register", data);
        localStorage.setItem("authToken", response["authToken"]) //TODO switch to cookies instead of localstorage
    } catch (err) {
        //alert("Failed to register account");
    }
}

export const authenticationService = {
    login,
    register
    //logout
};