import React, {Component} from "react";
import { SketchPicker } from 'react-color';

class CreateProjectPopupContent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: "",
            color: ""
        };

        this.updateName = this.updateName.bind(this);
        this.updateColor = this.updateColor.bind(this);
    }

    updateName(event) {
        this.setState({
            name: event.target.value
        });
    }

    updateColor = (c) => {
        this.setState({ color: c.hex });
    };

    createProject = props => {
        props.onClick(this.state.name, this.state.color);
        props.onCreateProject();
    }

    render(){
        return (
            <div className="CreateProjectPopupContent">
                <input placeholder={"Enter project name"} onChange={this.updateName}/>
                <SketchPicker
                    color={ this.state.color }
                    onChangeComplete={this.updateColor}
                />
                <button onClick={() => this.createProject(this.props)}>Create Project</button>
            </div>
        );
    }

}

export default CreateProjectPopupContent;