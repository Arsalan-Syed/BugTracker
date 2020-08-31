import axios from "axios";
import {authenticationService} from "./AuthenticationService";

const API_URL = "http://localhost:8080/";


function getAllProjects(){
    const options = {
        headers: {
            'Authorization': authenticationService.authHeader()
        }
    };

    axios.get(API_URL+"projects", options).then(response => {
        return response.data;
    })
    .catch(error => {
        console.log(error)
    });
}

export const projectService = {
    getAllProjects
}