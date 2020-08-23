import React from 'react';
import './ProjectCard.css'


const ProjectCard = props => (
    <div className="ProjectCard" style={{"background-color": this.props.project.color}}>
        <b className="ProjectCardTitle">{this.props.project.name}</b>
    </div>
);

export default ProjectCard;