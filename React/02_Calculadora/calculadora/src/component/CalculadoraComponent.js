import React, { Component } from 'react'

export default class CalculadoraComponent extends Component {
    render(){
        return (
            <div>
               <table>
                <tr>
                    <td>
                        <button name="AC" onClick={this.props.clickHandler}>AC</button>
                    </td>
                    <td>
                        <button name="+/-" onClick={this.props.clickHandler}>+/-</button>
                    </td>
                    <td>
                        <button name="%" onClick={this.props.clickHandler}>%</button>
                    </td>
                    <th>
                        <button onClick={this.props.clickHandler}>÷</button>
                    </th>
                </tr>
                <tr>
                    <td>
                        <button onClick={this.props.clickHandler}>7</button>
                    </td>
                    <td>
                        <button onClick={this.props.clickHandler}>8</button>
                    </td>
                    <td>
                        <button onClick={this.props.clickHandler}>9</button>
                    </td>
                    <th>
                        <button onClick={this.props.clickHandler}>X</button>
                    </th>
                </tr>
                <tr>
                    <td>
                        <button onClick={this.props.clickHandler}>4</button>
                    </td>
                    <td>
                        <button onClick={this.props.clickHandler}>5</button>
                    </td>
                    <td>
                        <button onClick={this.props.clickHandler}>6</button>
                    </td>
                    <th>
                        <button onClick={this.props.clickHandler}>-</button>
                    </th>
                </tr>
                <tr>
                    <td>
                        <button onClick={this.props.clickHandler}>1</button>
                    </td>
                    <td>
                        <button onClick={this.props.clickHandler}>2</button>
                    </td>
                    <td>
                        <button onClick={this.props.clickHandler}>3</button>
                    </td>
                    <th>
                        <button onClick={this.props.clickHandler}>+</button>
                    </th>
                </tr>
                <tr>
                    <td colspan="2">
                        <button onClick={this.props.clickHandler}>0</button>
                    </td>
                    <td>
                        <button onClick={this.props.clickHandler}>.</button>
                    </td>
                    <th>
                        <button onClick={this.props.clickHandler}>=</button>
                    </th>
                </tr>
               </table>
            </div>
        )
    }
}