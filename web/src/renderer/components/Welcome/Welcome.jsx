import React from 'react'

export default class Welcome extends React.Component {
    render() {
        return(
            <h1 className='welcome'>
                Hello from {this.props.compiler} and {this.props.framework}!
            </h1>
        )
    }
}