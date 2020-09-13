import React, {Component} from 'react';
import {modelInstance} from "../Model";

export default class SearchBar extends Component {

    constructor(props) {
        super(props);
        this.setSearchQueryParameter = this.setSearchQueryParameter.bind(this);
    }

    setSearchQueryParameter(e) {
        modelInstance.setQueryText(e.target.value);
    }

    render(){
        return (
            <div className="input-group">
                <input type="text" className="form-control bg-light border-0 small" placeholder="Search for..."
                       aria-label="Search" aria-describedby="basic-addon2"
                       onChange={this.setSearchQueryParameter}
                />
                <div className="input-group-append">
                    <button className="btn btn-primary" type="button">
                        <i className="fas fa-search fa-sm"></i>
                    </button>
                </div>
            </div>
        );
    }

};