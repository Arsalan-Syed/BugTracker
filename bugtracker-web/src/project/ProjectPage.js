import React, {Component} from 'react';
import './ProjectPage.css'
import ProjectCard from "../project/ProjectCard";
import CreateProjectCard from "./CreateProjectCard";

class ProjectPage extends Component{

    constructor(props) {
        super(props);
        this.state = {projects:[]};
    }

    componentDidMount(){
        let url = 'http://localhost:8080/projects';
        fetch(url)
            .then(response => response.json())
            .then(data => this.setState({projects: data}))
    }

    render() {
        const projects = this.state.projects.map(p => <ProjectCard project={p}/>)

        if(projects.length === 0) {
            return <div className="ProjectPage">
                <p>You have no projects, try creating one!</p>
                <CreateProjectCard/>
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