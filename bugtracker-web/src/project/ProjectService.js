import {restClient} from "../utils/RestClient";


async function getAllProjects(){
    return await restClient.get("projects");
}

async function getProject(projectName){
    if(projectName == null){
        throw new Error("Project name cannot be null");
    }
    return await restClient.get("projects/"+projectName);
}

async function createProject(name, color){
    if(name == null){
        throw new Error("Project name cannot be null");
    }

    let data = {
        name: name,
        color: color
    }
    return await restClient.post("project", data);
}

async function createIssue(projectName, issue){
    if(projectName == null){
        throw new Error("Project name cannot be null");
    }
    let url = "project/"+projectName+"/issue";
    return await restClient.post(url, issue);
}

export const projectService = {
    getAllProjects,
    getProject,
    createProject,
    createIssue
}