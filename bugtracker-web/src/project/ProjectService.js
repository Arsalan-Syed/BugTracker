import {restClient} from "../utils/RestClient";


async function getAllProjects(){
    return await restClient.get("projects");
}

async function createProject (name, color){
    let data = {
        name: name,
        color: color
    }
    return await restClient.post("project", data);
}

export const projectService = {
    getAllProjects,
    createProject
}