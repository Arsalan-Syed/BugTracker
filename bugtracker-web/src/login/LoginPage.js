import React, {Component} from "react";
import {authenticationService} from "./AuthenticationService";
import {utils} from "../utils/Utils";

class LoginPage extends Component{

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            redirect: utils.isLogin()
        };

        this.updateUsername = this.updateUsername.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
    }

    async handleLogin(username, password) {
        await authenticationService.login(username, password);
        if(utils.isLogin()){
            this.props.history.push("/projects");
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
        return <div className="LoginPage">
            <p>Log in</p>
            <input placeholder="Username" value={this.state.username} onChange={this.updateUsername}/>
            <input type="password" value={this.state.password} onChange={this.updatePassword} placeholder="Password"/>
            <button onClick={() => this.handleLogin(this.state.username, this.state.password)}>Log in</button>
        </div>
    }

}

export default LoginPage;