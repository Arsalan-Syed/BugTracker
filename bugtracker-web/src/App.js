import React, {Component} from 'react';
import './App.css';
import LoginPage from "./login/LoginPage";

class App extends Component {

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <p>BugTracker</p>
                </header>
                <LoginPage/>
            </div>
        );
    }

}

export default App;
