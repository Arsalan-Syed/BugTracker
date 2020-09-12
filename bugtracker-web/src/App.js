import React, {Component} from 'react';
import './App.css';
import {Route, Switch} from "react-router";
import {BrowserRouter} from "react-router-dom";
import Layout from "./Layout/Layout";
import LoginPage from "./Pages/LoginPage";
import RegisterPage from "./Pages/RegisterPage";
import ProjectsPage from "./Pages/ProjectsPage";


export default class App extends Component {
    render(){
        return (
            <div className="App">

                <BrowserRouter>
                    <Switch>
                        <Route path="/login" component={LoginPage}/>
                        <Route path="/register" component={RegisterPage}/>

                        <Route path="/projects" render={(props) =>
                            <Layout {...props} content={<ProjectsPage/>}/>}
                        />

                        <Route path="/" component={Layout}/>
                    </Switch>
                </BrowserRouter>

            </div>
        );
    }

}