import React, {Component} from "react";
import {authenticationService} from "./AuthenticationService";
import {utils} from "../utils/Utils";
import './LoginPage.css'
import {Link} from "react-router-dom";

export default class LoginPage extends Component{

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
                    <h1>Log in</h1>
                    <p>Please enter your username and password</p>
                    <input placeholder="Username" onChange={this.updateUsername}/>
                    <input type="password" onChange={this.updatePassword} placeholder="Password"/>
                    <div>
                        <button onClick={() => this.handleLogin(this.state.username, this.state.password)}>Log in</button>
                    </div>

                    <div>
                        <Link to="/register">
                            <a>Don't have an account? Sign up here</a>
                        </Link>
                    </div>
            </div>
    }

}