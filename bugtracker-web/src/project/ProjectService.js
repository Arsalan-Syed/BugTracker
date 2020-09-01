import {restClient} from "../utils/RestClient";


async function getAllProjects(){
    let response = await restClient.get("projects");
    return response;
}

function createProject (name, color){
    let data = {
        name: name,
        color: color
    }
    restClient.post("project", data);
}

export const projectService = {
    getAllProjects,
    createProject
}