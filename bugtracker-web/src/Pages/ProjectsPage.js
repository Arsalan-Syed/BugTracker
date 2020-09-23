import React, {Component} from 'react';
import ProjectCard from "../Components/ProjectCard";
import Modal from "react-bootstrap/Modal";
import Form from 'react-bootstrap/Form'
import {modelInstance} from "../Data/Model";
import {Redirect} from "react-router-dom";
import Layout from "../Layout/Layout";


export default class ProjectsPage extends Component {

    constructor(props){
        super(props);
        this.state = {
            modalOpen: false,
            queryText: null,
            projects: [],
            projectName: null,
            redirect: false
        };

        this.updateProjectName = this.updateProjectName.bind(this);
    }

    async componentDidMount() {
        modelInstance.addObserver(this);
        const response = await modelInstance.getAllProjects();
        if(response != null) {
            this.setState({projects: response});
        }
    }

    update = (obj) =>{
        let queryText = obj["queryText"];
        let projects = obj["projects"];

        this.setState({
            "queryText": queryText,
            "projects": projects
        });
    }

    showModal = () => {
        this.setState({
            modalOpen: true
        })
    }

    createProject = (projectName, projectColor) => {
        modelInstance.createProject(projectName, projectColor);
        this.hideModal();
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

        return projectName.toLowerCase().includes(queryText.toLowerCase());
    }

    textIsEmpty = (text) => {
        return text == null || text.trim() === '';
    }

    updateProjectName = (event) => {
        this.setState({
            projectName: event.target.value
        });
    }

    selectProject = (projectName) => {
        console.log(projectName);
        modelInstance.setProject(projectName);
        this.setState({"redirect":true});
    }

    render(){
        let visibleProjects = [];
        if(this.state.projects != null){
            visibleProjects = this.state.projects
                .filter(proj => this.filterProjectsByQueryText(proj.name, this.state.queryText))
                .map(proj =>
                    <div key={proj.name} className="col-xl-3 col-md-6 mb-4" onClick={() => this.selectProject(proj.name)}>
                        <ProjectCard project={proj}/>
                    </div>
                );
        }

        let createProjectMessage = <div className="container"><h5>You have no projects, let's create one</h5></div>

        return (
            <Layout content={
                <div className="container-fluid">
                {this.state.redirect && <Redirect to="/project"/>}
                <Modal show={this.state.modalOpen}>
                    <Modal.Header>
                        <Modal.Title>
                            Create an new project
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form noValidate>
                            <Form.Row>
                                <Form.Group md="4">
                                    <Form.Label>Project name</Form.Label>
                                    <Form.Control
                                        required
                                        type="text"
                                        onChange={this.updateProjectName}
                                    />
                                    <Form.Control.Feedback type="invalid">Project name cannot be empty</Form.Control.Feedback>
                                </Form.Group>
                            </Form.Row>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={this.hideModal}>Cancel</button>
                        <button className="btn btn-primary" onClick={() => this.createProject(this.state.projectName, "#ffffff")}>Save</button>
                    </Modal.Footer>
                </Modal>

                <div className="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 className="h3 mb-0 text-gray-800">Projects</h1>
                    <button className="btn-primary btn-circle" onClick={this.showModal}>
                        <i className="fab">+</i>
                    </button>
                </div>

                <div className="row">
                    {visibleProjects.length > 0 && visibleProjects}
                    {visibleProjects.length === 0 && createProjectMessage}
                </div>
            </div>
            }/>
    );
    }
}