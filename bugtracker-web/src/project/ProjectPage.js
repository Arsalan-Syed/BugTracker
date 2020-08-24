import React, {Component} from 'react';
import './ProjectPage.css'
import ProjectCard from "../project/ProjectCard";
import CreateProjectCard from "./CreateProjectCard";
import Popup from "./Popup";

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

    //TODO both cases will need the create project popup
    render() {
        const projects = this.state.projects.map(p => <ProjectCard project={p}/>)

        if(projects.length === 0) {
            return <div className="ProjectPage">
                <p>You have no projects, try creating one!</p>
                <CreateProjectCard onClick={this.togglePopup}/>

                {this.state.popupOpen && <Popup content={
                    <div>
                        <input placeholder={"Enter project name"}/>
                        <button>Create Project</button>
                    </div>
                    } handleClose={this.togglePopup}/>
                }

            </div>
        } else{
            return <div className="ProjectPage">
                {projects}
                <CreateProjectCard/>
            </div>
        }
    }

}


export default ProjectPage;