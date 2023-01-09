import React, { Component } from 'react'

export default class ResultadoComponent extends Component {
    render() {
        return (
            <div className='resultado'>
                <h1>{this.props.value}</h1>
            </div>
        )
    }
}