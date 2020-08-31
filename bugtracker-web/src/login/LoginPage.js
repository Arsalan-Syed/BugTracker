import React, {Component} from "react";
import {Redirect} from 'react-router-dom'
import {authenticationService} from "../AuthenticationService";

class LoginPage extends Component{

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            redirect: false
        };

        this.updateUsername = this.updateUsername.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
    }

    handleLogin(username, password) {
        authenticationService.login(username, password);
        this.setState({
            redirect: true //change to loggedIn
        });
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
        if (this.state.redirect) {
            return <Redirect to={"/projects"} />
        }

        return <div className="LoginPage">
            <p>Log in</p>
            <input placeholder="Username" value={this.state.username} onChange={this.updateUsername}/>
            <input type="password" value={this.state.password} onChange={this.updatePassword} placeholder="Password"/>
            <button onClick={() => this.handleLogin(this.state.username, this.state.password)}>Log in</button>
        </div>
    }

}

export default LoginPage;