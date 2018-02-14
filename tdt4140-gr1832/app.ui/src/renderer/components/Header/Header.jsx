import React from 'react'
import MenuItem from 'material-ui/MenuItem'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'
import AppBar from 'material-ui/AppBar'
import Drawer from 'material-ui/Drawer'

export default class Header extends React.Component {
    constructor(props) {
        super(props);
        this.state = {open: false};
        this.handleToggle = this.handleToggle.bind(this);
      }
    
    handleToggle() {
        this.setState({open: !this.state.open});
    }

    render() {
        return (
            <MuiThemeProvider>
                <div>
                    <AppBar
                     title="Main bar"
                     onLeftIconButtonClick={this.handleToggle}
                    />
                    <Drawer
                     open={this.state.open}
                     docked={false}
                     onRequestChange={(open) => this.setState({open})}
                     >
                        <MenuItem>Home</MenuItem>
                        <MenuItem>About</MenuItem>
                        <MenuItem>Contact us</MenuItem>
                    </Drawer>
                </div>
            </MuiThemeProvider>
        );
    }
}