import React, {Component} from 'react';
import './ProjectPage.css'
import ProjectCard from "../project_card/ProjectCard";

class ProjectPage extends Component{

    render() {
        const projects = this.props.projects.map(p => <ProjectCard project={p}/>)

        return <div className="ProjectPage">
            {projects}
        </div>;
    }
}

export default ProjectPage;