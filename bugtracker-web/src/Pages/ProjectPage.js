import React, {Component} from 'react';
import IssueStatusColumn from "../Components/IssueStatusColumn";
import Modal from "react-bootstrap/Modal";
import {modelInstance} from "../Data/Model";
import Form from "react-bootstrap/Form";

export default class ProjectPage extends Component {

    constructor(props){
        super(props);
        this.state = {
            modalOpen: false,
            queryText: null,
            project: null,
            issueName: null
        };

        this.updateIssueName = this.updateIssueName.bind(this);
    }

    async componentDidMount() {
        modelInstance.addObserver(this);

        const project = await modelInstance.getProject();
        this.setState({
            "project": project
        })
    }

    update = (obj) =>{
        let project = obj["project"];

        this.setState({
            "project": project
        });
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

    updateIssueName = (event) => {
        this.setState({
            issueName: event.target.value
        });
    }

    filterIssues = (issue, status) => {
        return this.filterByStatus(issue, status)
           && this.filterProjectsByQueryText([issue.name, issue.description], this.state.queryText);
    }

    filterByStatus = (issue, status) => {
        return issue.status === status;
    }

    filterProjectsByQueryText = (fields, queryText) => {
        if(queryText == null){
            return true;
        }

        let result = false;
        for(let field of fields){
            result = result || field.toLowerCase().includes(queryText.toLowerCase());
        }

        return result;
    }

    prettyName = (text) => {
        let prettyText = text.toLowerCase()
            .replace("_", " ");

        prettyText = prettyText[0].toUpperCase() + prettyText.slice(1);
        return prettyText;
    }

    getProjectName = () => {
        if(this.state.project != null){
            let name = this.state.project.name;
            return name == null ? "" : name;
        }
        return "";
    }

    createIssue = (project, issueName) => {
        let issue = {
            "name":issueName,
            "priority":"LOW" //TODO pass in param
        }
        console.log(issue);
        modelInstance.createIssue(issue);
        this.hideModal();
    }

    extractIssues = () => {
        if(this.state.project == null){
            return [];
        }
        if(this.state.project.issues == null){
            return [];
        }
        return this.state.project.issues;
    }

    render(){
        let statuses = ['TODO', 'IN_PROGRESS', 'TESTING', 'DONE'];

        let issues = this.extractIssues();
        console.log(issues);

        let columns = statuses.map(status =>
            <div className="col-xl-3 col-md-6 mb-4">
                <IssueStatusColumn key={this.prettyName(status)} issueStatus={this.prettyName(status)}
                                   issues={issues.filter(issue => this.filterIssues(issue, status))}/>
            </div>
        );

        return (
            <div className="container-fluid">
                <Modal show={this.state.modalOpen}>
                    <Modal.Header>
                        <Modal.Title>
                            Create an issue
                        </Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <Form noValidate>
                            <Form.Row>
                                <Form.Group md="4">
                                    <Form.Label>Issue name</Form.Label>
                                    <Form.Control
                                        required
                                        type="text"
                                        onChange={this.updateIssueName}
                                    />
                                    <Form.Control.Feedback type="invalid">Issue name cannot be empty</Form.Control.Feedback>
                                </Form.Group>
                            </Form.Row>
                        </Form>
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={this.hideModal}>Cancel</button>
                        <button className="btn btn-primary" onClick={() => this.createIssue(this.state.project, this.state.issueName)}>Save</button>
                    </Modal.Footer>
                </Modal>

                <div className="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 className="h3 mb-0 text-gray-800">{this.getProjectName()}</h1>

                    <button className="btn-primary btn-circle" onClick={this.showModal}>
                        <i className="fab">+</i>
                    </button>
                </div>

                <div className="row">
                    {columns}
                </div>
            </div>
    );
    }
}