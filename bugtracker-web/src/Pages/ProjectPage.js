import React, {Component} from 'react';
import IssueStatusColumn from "../Components/IssueStatusColumn";
import Modal from "react-bootstrap/Modal";
import {modelInstance} from "../Model";

export default class ProjectPage extends Component {

    constructor(props){
        super(props);
        this.state = {
            modalOpen: false,
            issues: [
                {"name": "Issue #1","status":"TODO", "description": "Set up a database"},
                {"name": "Issue #2","status":"IN_PROGRESS", "description": "Refactor authentication code"},
                {"name": "Issue #3","status":"TESTING", "description":  "Login to home page"},
                {"name": "Issue #4","status":"DONE", "description": "Set up theme"}
            ],
            queryText: null
        };
    }

    componentDidMount() {
        modelInstance.addObserver(this);
    }

    update = (obj) =>{
        let queryText = obj["queryText"];

        this.setState({
            "queryText": queryText
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

    render(){
        let statuses = ['TODO', 'IN_PROGRESS', 'TESTING', 'DONE'];

        let columns = statuses.map(status =>
            <div className="col-xl-3 col-md-6 mb-4">
                <IssueStatusColumn issueStatus={this.prettyName(status)} issues={this.state.issues.filter(issue => this.filterIssues(issue, status))}/>
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
                    <Modal.Body>Body</Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger" onClick={this.hideModal}>Cancel</button>
                        <button className="btn btn-primary" onClick={this.hideModal}>Save</button>
                    </Modal.Footer>
                </Modal>

                <div className="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 className="h3 mb-0 text-gray-800">{this.props.projectName}</h1>

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