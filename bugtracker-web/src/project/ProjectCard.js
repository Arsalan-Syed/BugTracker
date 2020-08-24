import React from 'react';
import './ProjectCard.css'


const ProjectCard = props => (
    <div className="ProjectCard" style={{"background-color": props.project.color}}>
        <b className="ProjectCardTitle">{props.project.name}</b>
    </div>
);

export default ProjectCard;