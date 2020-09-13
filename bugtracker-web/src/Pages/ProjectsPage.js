import React, {Component} from 'react';
import ProjectCard from "../Components/ProjectCard";
import Modal from "react-bootstrap/Modal";
import {modelInstance} from "../Model";

export default class ProjectsPage extends Component {

    constructor(props){
        super(props);
        this.state = {
            modalOpen: false,
            projects: [
                {"name":"Project #1"},
                {"name":"Project #2"},
                {"name":"Project #3"},
                {"name":"Project #4"}
            ],
            queryText: null
        };
    }

    componentDidMount() {
        modelInstance.addObserver(this);
    }

    update = (obj) =>{
        let queryText = obj["queryText"];

        if(!this.textIsEmpty(queryText)){
            this.setState({
                "queryText": queryText
            })
        }
    }

    showModal = () => {
        this.setState({
            modalOpen: true
        })
    }

    hideModal = () => {
        this.setState({
            modalOpen: false
        })
    }

    filterProjectsByQueryText = (projectName, queryText) => {
        if(this.textIsEmpty(queryText)){
            return true;
        }

        return projectName.includes(queryText);
    }

    textIsEmpty = (text) => {
        return text == null || text.trim() === '';
    }

    render(){
        let visibleProjects = this.state.projects
            .filter(proj => this.filterProjectsByQueryText(proj.name, this.state.queryText))
            .map(proj =>
                <div className="col-xl-3 col-md-6 mb-4">
                    <ProjectCard project={proj}/>
                </div>
            );

        return (
            <div className="container-fluid">
                <Modal show={this.state.modalOpen}>
                    <Modal.Header>
                        <Modal.Title>
                            Create an new project
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>Body</Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={this.hideModal}>Cancel</button>
                        <button className="btn btn-primary" onClick={this.hideModal}>Save</button>
                    </Modal.Footer>
                </Modal>

                <div className="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 className="h3 mb-0 text-gray-800">Projects</h1>
                    <button className="btn-primary btn-circle" onClick={this.showModal}>
                        <i className="fab">+</i>
                    </button>
                </div>

                <div className="row">
                    {visibleProjects}
                </div>
            </div>
    );
    }
}