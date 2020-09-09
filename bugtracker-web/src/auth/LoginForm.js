import React, {Component} from "react";
import {authenticationService} from "./AuthenticationService";
import {utils} from "../utils/Utils";
import './LoginForm.css'
import {Link} from "react-router-dom";

export default class LoginForm extends Component{

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            redirect: utils.userIsLoggedIn()
        };

        this.updateUsername = this.updateUsername.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
    }

    async handleLogin(username, password) {
        await authenticationService.login(username, password);
        if(utils.userIsLoggedIn()){
            this.props.history.push("/dashboard");
        }
    }

    updateUsername(event) {
        this.setState({
            username: event.target.value
        });
    }

    updatePassword(event) {
        this.setState({
            password: event.target.value
        });
    }

    render(){
        return <div className="Login">
                    <input placeholder="Username" onChange={this.updateUsername}/>
                    <input type="password" onChange={this.updatePassword} placeholder="Password"/>
                    <div>
                        <button className="loginButton" onClick={() => this.handleLogin(this.state.username, this.state.password)}>
                            <b className="loginButtonText">Log in</b>
                        </button>
                    </div>

                    <div>
                        <Link to="/register">
                            <p>Don't have an account? Sign up here</p>
                        </Link>
                    </div>
            </div>
    }

}