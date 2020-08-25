import React, {Component} from "react";

class LoginPage extends Component{

    render(){
        return <div className="LoginPage">
            <p>Log in</p>
            <input placeholder="Username"/>
            <input type="password" placeholder="Password"/>
            <button>Log in</button>
        </div>
    }

}

export default LoginPage;