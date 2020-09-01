import React from 'react';
import './App.css';
import LoginPage from "./login/LoginPage";
import {Route} from "react-router";
import {BrowserRouter} from "react-router-dom";
import ProjectPage from "./project/ProjectPage";
import PrivateRoute from "./route/PrivateRoute";


const App = () => {
    return (
        <div className="App">
            <header className="App-header">
                <p>BugTracker</p>
            </header>

            <BrowserRouter>
                <Route path="/login" component={LoginPage} restricted={true}/>
                <PrivateRoute path="/projects" component={ProjectPage}/>
            </BrowserRouter>
        </div>
    );
}

export default App;
