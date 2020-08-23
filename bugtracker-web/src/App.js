import React, {Component} from 'react';
import './App.css';
import ProjectPage from "./project_page/ProjectPage";

class App extends Component {

    constructor(props) {
        super(props);
        this.state = {projects:[]};
    }

    componentDidMount(){
        let url = 'http://localhost:8080/projects';
        fetch(url)
            .then(response => response.json())
            .then(data => this.setState({projects: data}))
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <p>BugTracker</p>
                </header>
                <ProjectPage projects={this.state.projects}/>
            </div>
        );
    }

}

export default App;
