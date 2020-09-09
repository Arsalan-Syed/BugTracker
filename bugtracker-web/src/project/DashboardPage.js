import React, {Component} from 'react';
import './ProjectPage.css'
import ProjectCard from "../project/ProjectCard";
import CreateProjectCard from "./CreateProjectCard";
import Popup from "./Popup";
import CreateProjectPopupContent from "./CreateProjectPopupContent";
import {projectService} from "./ProjectService";

export default class DashboardPage extends Component{

    constructor(props) {
        super(props);
        this.state = {
            projects:[],
            popupOpen: false
        };
    }

    async componentDidMount(){
        const response = await projectService.getAllProjects();
        if(response != null) {
            this.setState({projects: response});
        }
    }

    togglePopup = () => {
        let isOpen = this.state.popupOpen;
        this.setState({popupOpen: !isOpen})
    }

    render() {
        const projects = this.state.projects.map(p => <ProjectCard project={p}/>)

        return <div className="ProjectPage">
            {projects.length === 0 && <p>You have no projects, try creating one!</p>}
            {projects}
            <CreateProjectCard onClick={this.togglePopup}/>
            {this.state.popupOpen && <Popup content={<CreateProjectPopupContent onClick={projectService.createProject} onCreateProject={this.togglePopup}/>} handleClose={this.togglePopup}/>}
        </div>
    }

}