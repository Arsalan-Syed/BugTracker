import React, {Component} from 'react';
import './App.css';
import ProjectPage from "./project_page/ProjectPage";

class App extends Component {

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <p>BugTracker</p>
                </header>
                <ProjectPage/>
            </div>
        );
    }

}

export default App;
