import React, {Component} from "react";
import LoginForm from "./auth/LoginForm";
import './HomePage.css'
import RegisterPage from "./auth/RegisterPage";

export default class HomePage extends Component{

    constructor(props) {
        super(props);
        this.state = {
        };
    }

    render(){
        return <div className="HomePage">
                    <div>
                        <h1>Welcome</h1>
                    </div>
                    <div>
                        <LoginForm {...this.props} props/>
                    </div>
               </div>
    }

}