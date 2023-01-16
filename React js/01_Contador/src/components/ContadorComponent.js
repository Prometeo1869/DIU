import React, { Component } from 'react'

export default class ContadorComponent extends Component {
    render() {
        return (
            <div>
                <h1>{this.props.cont}</h1>
            </div>
        )
    }
}