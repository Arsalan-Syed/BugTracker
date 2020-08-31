import React, {Component} from 'react';
import './ProjectPage.css'
import ProjectCard from "../project/ProjectCard";
import CreateProjectCard from "./CreateProjectCard";
import Popup from "./Popup";
import CreateProjectPopupContent from "./CreateProjectPopupContent";

class ProjectPage extends Component{

    constructor(props) {
        super(props);
        this.state = {
            projects:[],
            popupOpen: false
        };
    }

    componentDidMount(){
        let url = 'http://localhost:8080/projects';
        fetch(url)
            .then(response => response.json())
            .then(data => this.setState({projects: data}))
    }

    togglePopup = () => {
        let isOpen = this.state.popupOpen;
        this.setState({popupOpen: !isOpen})
    }

    createProject = () => {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name: 'React POST Request Example', color:'#0ea06f' })
        };
        fetch('http://localhost:8080/project', requestOptions)
            .then(this.togglePopup)
    }

    render() {
        const projects = this.state.projects.map(p => <ProjectCard project={p}/>)

        return <div className="ProjectPage">
            {projects.length === 0 && <p>You have no projects, try creating one!</p>}
            {projects}
            <CreateProjectCard onClick={this.togglePopup}/>
            {this.state.popupOpen && <Popup content={<CreateProjectPopupContent onClick={this.createProject}/>} handleClose={this.togglePopup}/>}
        </div>
    }

}

export default ProjectPage;