import React, {Component} from 'react';
import './App.css';
import {Route, Switch} from "react-router";
import {BrowserRouter} from "react-router-dom";
import Layout from "./Layout/Layout";
import LoginPage from "./Pages/LoginPage";
import RegisterPage from "./Pages/RegisterPage";
import ProjectsPage from "./Pages/ProjectsPage";
import ProjectPage from "./Pages/ProjectPage";
import HomePage from "./Pages/HomePage";
import PrivateRoute from "./Route/PrivateRoute";



export default class App extends Component {
    render(){
        return (
            <div className="App">

                <BrowserRouter>
                    <Switch>
                        <Route path="/login" component={LoginPage}/>
                        <Route path="/register" component={RegisterPage}/>

                        <PrivateRoute path="/project" render={(props) =>
                            <Layout {...props} content={<ProjectPage projectName={"Project #1"}/>}/>}
                        />

                        <PrivateRoute path="/projects" render={(props) =>
                            <Layout {...props} content={<ProjectsPage/>}/>}
                        />

                        <Route path="/" component={HomePage}/>
                    </Switch>
                </BrowserRouter>

            </div>
        );
    }

}