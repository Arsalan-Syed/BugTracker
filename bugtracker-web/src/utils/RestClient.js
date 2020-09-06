import axios from "axios"
import {utils} from "./Utils"

const API_URL = "http://localhost:8080/";

function get(path){
    const options = {
        headers: {
            'Authorization': utils.authHeader()
        }
    };

    return axios.get(API_URL+path, options)
        .then(response => response.data)
        .catch(error => {
            //alert(error);
        });
}

function post(path, data){
    const options = {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': utils.authHeader()
        }
    };

    return axios.post(API_URL+path, data, options)
        .then(response => response.data)
        .catch(error => {
           // alert(error);
        });
}


function postUnAuthenticated(path, data){
    const options = {
        headers: {
            'Content-Type': 'application/json',
        }
    };

    return axios.post(API_URL+path, data, options)
        .then(response => response.data)
        .catch(error => {
           // alert(error);
        });
}


export const restClient = {
    get,
    post,
    postUnAuthenticated
}