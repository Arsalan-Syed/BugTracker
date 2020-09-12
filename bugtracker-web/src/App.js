import React, {Component} from 'react';
import './App.css';
import {Route, Switch} from "react-router";
import {BrowserRouter} from "react-router-dom";
import Layout from "./Layout/Layout";


export default class App extends Component {
    render(){
        return (
            <div className="App">

                <BrowserRouter>
                    <Switch>
                        <Route path="/" component={Layout}/>
                    </Switch>
                </BrowserRouter>

            </div>
        );
    }

}