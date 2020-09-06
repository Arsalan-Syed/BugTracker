import React, {Component} from "react";
import './PageHeader.css'

export default class PageHeader extends Component {

    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render(){
        return <header className="App-header">
            <p>Logged in as: Arsalan Syed</p>
        </header>
    }
}
