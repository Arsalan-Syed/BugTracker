import React from 'react';
import { Route, Redirect } from 'react-router-dom';
import {utils} from '../utils/Utils';

const PrivateRoute = ({component: Component, ...rest}) => {
    return (
        <Route {...rest} render={props => (
            utils.userIsLoggedIn() ?
                <Component {...props} />
                : <Redirect to="/login" />
        )} />
    );
};

export default PrivateRoute;