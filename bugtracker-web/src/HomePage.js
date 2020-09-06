import React, {Component} from "react";
import {Link} from "react-router-dom";

export default class HomePage extends Component{

    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render(){
        return <div className="HomePage">
                   <h1>Welcome</h1>

                   <Link to="/login">
                       <button>Log in</button>
                   </Link>
               </div>
    }

}