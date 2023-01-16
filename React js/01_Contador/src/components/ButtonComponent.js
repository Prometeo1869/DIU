import React, { Component } from 'react'

export default class ButtonComponent extends Component {
    render(){
        return (
            <div>
                <button class={this.props.clase} onClick={this.props.incremento}>Incrementar</button>
                <button class={this.props.clase} onClick={this.props.decremento}>Decrementar</button>
                <button class={this.props.clase} onClick={this.props.reset}>Reset</button>
            </div>
        )
    }
}