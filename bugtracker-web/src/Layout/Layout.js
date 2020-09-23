import React, {Component} from 'react';
import Leftside from './Leftside';
import Header from './Header'
import Footer from './Footer'

const Layout = ({content}) => {

    return (
        <div>
            <div id="wrapper">
                <Leftside/>
                <div id="content-wrapper" className="d-flex flex-column">
                    <div id="content">
                        <Header />
                        {content}
                    </div>
                    <Footer />
                </div>
            </div>
        </div>
    )

}

export default Layout;