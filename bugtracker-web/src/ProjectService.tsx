import {Component} from 'react';

class ProjectService extends Component{

      constructor(props: any) {
        super(props);

        this.state = {
            data: null,
        };
    }

    componentDidMount() {
        let url = 'http://localhost:8080/projects';
        fetch(url)
            .then(response => response.json())
            .then(data => this.setState({ data }))
    }
}
export default ProjectService;
