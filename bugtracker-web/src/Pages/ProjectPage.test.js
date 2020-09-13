import React from 'react';
import { shallow } from 'enzyme';
import ProjectPage from "./ProjectPage";


it('ProjectPage renders without crashing', () => {
    shallow(<ProjectPage />);
});