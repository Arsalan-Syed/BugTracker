import {projectService} from "../project/ProjectService";

const Model = function () {

    let queryText = null;
    let observers = [];
    let projects = [];
    let projectName = null;


    this.setQueryText = function(text){
        queryText = text;
        this.notifyObservers({"queryText":text});
    }

    this.setProject = function(name){
        projectName = name;
        this.notifyObservers({"projectName":name});
    }

    this.getProject = async function(){
        let response = projectService.getProject(projectName);
        if(response != null && response["message"] == null){
            this.notifyObservers({"project":response});
        }
        return response;
    }

    this.addObserver = function (observer) {
        observers.push(observer);
    };

    this.notifyObservers = function (param) {
        observers.forEach(o => o.update(param));
    }

    this.createProject = async function (projectName, projectColor) {
        let response = await projectService.createProject(projectName, projectColor);
        if(response["message"] == null){
            projects.push({"name":projectName, "issues":[]});
            this.notifyObservers({"projects":projects});
        }
    }

    this.createIssue = async function (issue) {
        let response = await projectService.createIssue(projectName, issue);

        if(response["message"] == null){

        }
    }

    this.getAllProjects = async function () {
        projects = await projectService.getAllProjects();
        return projects;
    }
}

export const modelInstance = new Model();