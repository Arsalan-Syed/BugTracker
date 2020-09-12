import React, {Component} from 'react';
import IssueStatusColumn from "../Components/IssueStatusColumn";


export default class ProjectPage extends Component {

    constructor(props){
        super(props);
    }

    render(){
        return (
            <div className="container-fluid">
                <div className="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 className="h3 mb-0 text-gray-800">{this.props.projectName}</h1>
                    <a href="#" className="btn-primary btn-circle">
                        <i className="fab">+</i>
                    </a>
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