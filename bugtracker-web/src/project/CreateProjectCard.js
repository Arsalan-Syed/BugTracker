import React from 'react';
import './CreateProjectCard.css'


const CreateProjectCard = props => (
    <div className="CreateProjectCard" onClick={props.onClick}>
        <b className="CreateProjectCardTitle">Create a project</b>
    </div>
);

export default CreateProjectCard;