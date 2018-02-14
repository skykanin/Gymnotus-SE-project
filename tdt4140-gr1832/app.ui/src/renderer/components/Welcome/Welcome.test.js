import React from 'react'
import { shallow } from 'enzyme'
import toJson from 'enzyme-to-json'
import Welcome from './Welcome'

test('Welcome component should render as expected', () => {
    const wrapper = shallow(<Welcome compiler="Javascript" framework="React" />);
    const tree = toJson(wrapper);
    expect(tree).toMatchSnapshot();
})