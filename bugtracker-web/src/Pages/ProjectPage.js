import React, {Component} from 'react';
import IssueStatusColumn from "../Components/IssueStatusColumn";
import Modal from "react-bootstrap/Modal";

export default class ProjectPage extends Component {

    constructor(props){
        super(props);
        this.state = {
            modalOpen: false
        };
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

    render(){
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
                    <div className="col-xl-3 col-md-6 mb-4">
                       <IssueStatusColumn issueStatus="TODO"/>
                    </div>

                    <div className="col-xl-3 col-md-6 mb-4">
                        <IssueStatusColumn issueStatus="In Progress"/>
                    </div>

                    <div className="col-xl-3 col-md-6 mb-4">
                        <IssueStatusColumn issueStatus="Testing"/>
                    </div>

                    <div className="col-xl-3 col-md-6 mb-4">
                        <IssueStatusColumn issueStatus="Done"/>
                    </div>
                </div>
            </div>
    );
    }
}