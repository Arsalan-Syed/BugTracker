import React, {Component} from 'react';
import ProjectCard from "../Components/ProjectCard";


export default class ProjectsPage extends Component {
    render(){
        return (
            <div className="container-fluid">
                <div className="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 className="h3 mb-0 text-gray-800">Projects</h1>
                    <a href="#" className="btn-primary btn-circle">
                        <i className="fab">+</i>
                    </a>
                </div>

                <div className="row">
                    <div className="col-xl-3 col-md-6 mb-4">
                        <ProjectCard projectName={"Project #1"}/>
                    </div>

                    <div className="col-xl-3 col-md-6 mb-4">
                        <ProjectCard projectName={"Project #2"}/>
                    </div>

                    <div className="col-xl-3 col-md-6 mb-4">
                        <ProjectCard projectName={"Project #3"}/>
                    </div>

                    <div className="col-xl-3 col-md-6 mb-4">
                        <ProjectCard projectName={"Project #4"}/>
                    </div>
                </div>
            </div>
    );
    }
}