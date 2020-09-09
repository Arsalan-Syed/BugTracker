import React, {Component} from "react";
import {authenticationService} from "./AuthenticationService";
import {utils} from "../utils/Utils";
import {Link} from "react-router-dom";
import {notificationUtils} from "../utils/NotificationUtils";

export default class RegisterPage extends Component{

    constructor(props) {
        super(props);
        this.state = {
            firstName: "",
            lastName: "",
            username: "",
            password: "",
            matchPassword: "",
            email: "",
            redirect: utils.userIsLoggedIn()
        };

        this.updateFirstName = this.updateFirstName.bind(this);
        this.updateLastName = this.updateLastName.bind(this);
        this.updateUsername = this.updateUsername.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
        this.updateMatchPassword = this.updateMatchPassword.bind(this);
        this.updateEmail = this.updateEmail.bind(this);
    }

    async handleRegister(firstName, lastName, username,
                         password, matchPassword, email) {
        await authenticationService.register(
            firstName, lastName, username,
            password, matchPassword, email
            );
        if(utils.userIsLoggedIn()){
            notificationUtils.addSuccessNotification(this.props.notificationSys, "Successfully created account and logged in");
            this.props.history.push("/dashboard");
        } else{
            notificationUtils.addErrorNotification(this.props.notificationSys, "Failed to create an account");
        }
    }

    updateFirstName(event) {
        this.setState({
            firstName: event.target.value
        });
    }

    updateLastName(event) {
        this.setState({
            lastName: event.target.value
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

    updateMatchPassword(event) {
        this.setState({
            matchPassword: event.target.value
        });
    }

    updateEmail(event) {
        this.setState({
            email: event.target.value
        });
    }

    render(){
        return <div className="Register">
                    <h1>Create a new account</h1>
                    <p>Please enter your username and password</p>
                    <input placeholder="First Name" onChange={this.updateFirstName}/>
                    <input placeholder="Last Name" onChange={this.updateLastName}/>
                    <input placeholder="Username" onChange={this.updateUsername}/>
                    <input placeholder="Password" onChange={this.updatePassword} type="password" />
                    <input placeholder="Confirm Password" onChange={this.updateMatchPassword} type="password" />
                    <input placeholder="Email" onChange={this.updateEmail}/>
                    <div>
                        <button onClick={() => this.handleRegister(this.state.firstName, this.state.lastName, this.state.username, this.state.password, this.state.matchPassword, this.state.email)}>Register</button>
                    </div>

                    <div>
                        <Link to="/login">
                            <a>Already have an account? Sign in here</a>
                        </Link>
                    </div>
            </div>
    }

}