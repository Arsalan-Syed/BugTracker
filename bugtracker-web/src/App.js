import React from 'react';
import './App.css';
import LoginPage from "./login/LoginPage";
import {Route, Switch} from "react-router";
import {BrowserRouter} from "react-router-dom";
import ProjectPage from "./project/ProjectPage";


const App = () => {
    return (
        <div className="App">
            <header className="App-header">
                <p>BugTracker</p>
            </header>

            <BrowserRouter>
                <Switch>
                    <Route path="/login" component={LoginPage} />
                    <Route path="/projects" component={ProjectPage} />
                </Switch>
            </BrowserRouter>
        </div>
    );
}

export default App;
