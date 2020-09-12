import React, {Component} from 'react';
import Card from "../Components/Card";


export default class ProjectPage extends Component {
    render(){
        return (
            <div className="container-fluid">
                <div className="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 className="h3 mb-0 text-gray-800">Projects</h1>
                </div>

                <div className="row">
                    <div className="col-xl-3 col-md-6 mb-4">
                        <Card/>
                    </div>

                    <div className="col-xl-3 col-md-6 mb-4">
                        <Card/>
                    </div>

                    <div className="col-xl-3 col-md-6 mb-4">
                        <Card/>
                    </div>

                    <div className="col-xl-3 col-md-6 mb-4">
                        <Card/>
                    </div>
                </div>
            </div>
    );
    }
}