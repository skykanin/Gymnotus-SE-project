import React from 'react'
import RaisedButton from 'material-ui/RaisedButton'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider'

const ButtonStyle = {
    margin: 12,
  };

export default class Button extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            title: 'Press Me'
        }
        
        this.changeTitle = this.changeTitle.bind(this);
    }

    changeTitle() {
        if(this.state.title == 'Press Me') {
            this.setState({
                title: 'And Again'
            });
        } else {
            this.setState({
                title: 'Press Me'
            });
        }
    }

    render() {
        return(
            <MuiThemeProvider>
                <RaisedButton
                 label={this.state.title}
                 primary={true}
                 style={ButtonStyle}
                 onClick={this.changeTitle}
                   />
            </MuiThemeProvider>
        )
    }
}