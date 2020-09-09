import React, {Component} from 'react';
import './App.css';
import LoginPage from "./auth/LoginPage";
import {Route, Switch} from "react-router";
import {BrowserRouter} from "react-router-dom";
import DashboardPage from "./project/DashboardPage";
import PrivateRoute from "./route/PrivateRoute";
import RegisterPage from "./auth/RegisterPage";
import HomePage from "./HomePage";
import NotificationSystem from 'react-notification-system';
import PageHeader from "./PageHeader";

export default class App extends Component {
    notificationSystem = React.createRef();

    render(){
        return (
            <div className="App">
                <NotificationSystem ref={this.notificationSystem}/>

                <PageHeader/>

                <BrowserRouter>
                    <Switch>
                        <Route path="/login" render={(props) =>
                            <LoginPage {...props} notificationSys={this.notificationSystem}/>} restricted={true}
                        />

                        <Route path="/register" render={(props) =>
                            <RegisterPage {...props} notificationSys={this.notificationSystem}/>} restricted={true}
                        />

                        <PrivateRoute path="/dashboard" component={DashboardPage}/>
                        <Route path="/" component={HomePage} restricted={false}/>
                    </Switch>
                </BrowserRouter>

            </div>
        );
    }

}