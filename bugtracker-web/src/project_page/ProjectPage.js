import React, {Component} from 'react';
import './ProjectPage.css'
import ProjectCard from "../project_card/ProjectCard";

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

        return <div className="ProjectPage">
            {projects}
        </div>;
    }
}

export default ProjectPage;