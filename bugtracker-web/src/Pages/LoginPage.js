import React, {Component} from 'react';
import {Link} from "react-router-dom";
import {utils} from "../utils/Utils";
import {authenticationService} from "../auth/AuthenticationService";

export default class LoginPage extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
        };

        this.updateUsername = this.updateUsername.bind(this);
        this.updatePassword = this.updatePassword.bind(this);
    }

    async handleLogin(username, password) {
        await authenticationService.login(username, password);
        if(utils.userIsLoggedIn()){
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

    afterSubmission(event){
        event.preventDefault();
    }

    render() {
        return (
            <div className="jumbotron bg-gradient-primary">
                <div className="container">
                    <div className="row justify-content-center">
                        <div className="col-xl-10 col-lg-12 col-md-9">
                            <div className="card o-hidden border-0 shadow-lg my-5">
                                <div className="card-body p-0">
                                    <div className="row">
                                        <div className="col-lg-6 d-none d-lg-block bg-login-image"></div>
                                        <div className="col-lg-6">
                                            <div className="p-5">
                                                <div className="text-center">
                                                    <h1 className="h4 text-gray-900 mb-4">Welcome Back!</h1>
                                                </div>
                                                <form className="user" onSubmit={this.afterSubmission}>
                                                    <div className="form-group">
                                                        <input type="text" className="form-control form-control-user"
                                                               aria-describedby="emailHelp"
                                                               placeholder="Username"
                                                               onChange={this.updateUsername}
                                                        />
                                                    </div>

                                                    <div className="form-group">
                                                        <input type="password" className="form-control form-control-user"
                                                               placeholder="Password"
                                                               onChange={this.updatePassword}
                                                        />
                                                    </div>

                                                    <div className="form-group">
                                                        <div className="custom-control custom-checkbox small">
                                                            <input type="checkbox" className="custom-control-input"
                                                                   id="customCheck"/>
                                                            <label className="custom-control-label"
                                                                   htmlFor="customCheck">Remember Me</label>
                                                        </div>
                                                    </div>
                                                    <button className="btn btn-primary btn-user btn-block" onClick={() => this.handleLogin(this.state.username, this.state.password)}>
                                                        Login
                                                    </button>
                                                </form>
                                                <hr/>
                                                <div className="text-center">
                                                    <a className="small" href="forgot-password.html">Forgot
                                                        Password?</a>
                                                </div>
                                                <Link to="/register">
                                                    <div className="text-center">
                                                        <a className="small">Create an Account!</a>
                                                    </div>
                                                </Link>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}