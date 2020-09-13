import {projectService} from "../project/ProjectService";

const Model = function () {

    let queryText = null;
    let observers = [];
    let projects = [];


    this.setQueryText = function(text){
        queryText = text;
        this.notifyObservers({"queryText":text});
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
            projects.push({"name":projectName});
            this.notifyObservers({"projects":projects});
        }
    }

    this.getAllProjects = async function () {
        projects = await projectService.getAllProjects();
        return projects;
    }
}

export const modelInstance = new Model();