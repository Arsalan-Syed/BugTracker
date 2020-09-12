import React, {Component} from 'react';
import Leftside from './Leftside';
import Header from './Header'
import Footer from './Footer'

export default class Layout extends Component {
    loading = () => <div className="animated fadeIn pt-1 text-center">Loading...</div>

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <div id="wrapper">
                    <Leftside/>
                    <div id="content-wrapper" className="d-flex flex-column">
                        <div id="content">
                            <Header />
                            {this.props.content}
                        </div>
                        <Footer />
                    </div>
                </div>
            </div>
        )
    }
}