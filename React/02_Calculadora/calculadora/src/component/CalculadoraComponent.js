import React, { Component } from 'react'

export default class CalculadoraComponent extends Component {
    render(){
        return (
            <div>
               <table>
                <tr>
                    <td>
                        <button name="AC">AC</button>
                    </td>
                    <td>
                        <button name="+/-">+/-</button>
                    </td>
                    <td>
                        <button name="%">%</button>
                    </td>
                    <th>
                        <button onClick={this.props.operat}>÷</button>
                    </th>
                </tr>
                <tr>
                    <td>
                        <button onClick={this.props.num1}>7</button>
                    </td>
                    <td>
                        <button>8</button>
                    </td>
                    <td>
                        <button>9</button>
                    </td>
                    <th>
                        <button>X</button>
                    </th>
                </tr>
                <tr>
                    <td>
                        <button>4</button>
                    </td>
                    <td>
                        <button>5</button>
                    </td>
                    <td>
                        <button>6</button>
                    </td>
                    <th>
                        <button>-</button>
                    </th>
                </tr>
                <tr>
                    <td>
                        <button>1</button>
                    </td>
                    <td>
                        <button>2</button>
                    </td>
                    <td>
                        <button>3</button>
                    </td>
                    <th>
                        <button>+</button>
                    </th>
                </tr>
                <tr>
                    <td colspan="2">
                        <button>0</button>
                    </td>
                    <td>
                        <button>.</button>
                    </td>
                    <th>
                        <button>=</button>
                    </th>
                </tr>
               </table>
            </div>
        )
    }
}