import React, {Component} from 'react';
import './ProjectCard.css'

class ProjectCard extends Component{

    render() {
        return <div className="ProjectCard" style={{"background-color": this.props.project.color}}>
            <b className="ProjectCardTitle">{this.props.project.name}</b>
        </div>;
    }
}

export default ProjectCard;