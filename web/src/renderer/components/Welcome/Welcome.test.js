import React from 'react'
import { shallow } from 'enzyme'
import Welcome from './Welcome'

test('Welcome component should render as expected', () => {
    const wrapper = shallow(<Welcome compiler="Javascript" framework="React" />);
    expect(wrapper.contains('Hello from Javascript and React!')).toBe(true);
})

/* describe('<Welcome />', () => {
    it('renders 1 <Welcome /> component', () => {
        const component = shallow(<Welcome compiler="Javascript" framework="React" />);
        expect(component).toHaveLength(1);
    })
}) */