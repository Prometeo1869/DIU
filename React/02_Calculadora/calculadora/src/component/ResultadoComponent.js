import React, { Component } from 'react'

export default class ResultadoComponent extends Component {
    render() {
        return (
            <div>
                <h1>{this.props.resultado}</h1>
            </div>
        )
    }
}