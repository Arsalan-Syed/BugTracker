import React from "react";

const CreateProjectPopupContent = props => {
    return (
        <div className="CreateProjectPopupContent">
            <input placeholder={"Enter project name"}/>
            <button onClick={props.onClick}>Create Project</button>
        </div>
    );
};

export default CreateProjectPopupContent;