import React, { Component } from 'react';
import { Table } from 'react-bootstrap';

class Table_Example extends Component {
    constructor() {
        super()
    }

    renderData(data, index) {
        return (
            <tr key={index}>
                <td>{data.userId}</td>
                <td>{data.id}</td>
                <td>{data.title}</td>
                <td>{data.body}</td>
            </tr>
        )
    }

    render() {
        return (
            <Table responsive striped bordered hover size="sm">
                <thead>
                    <tr>
                        <th>User ID</th>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Body</th>
                    </tr>
                </thead>
                <tbody>
                    {this.props.data.map(this.renderData)}
                </tbody>
            </Table>)
    }
}

export default Table_Example;

//La función map () se usa para iterar sobre una matriz (Array) y manipular o cambiar elementos de datos. 
//En React, la función map () se usa más comúnmente para representar una lista de datos en el DOM.
//Ten en cuenta que la función map () no muta la matriz original; en su lugar, crea una nueva matriz con los elementos modificados de la matriz original.

//const num = [3, 8, 11, 7, 5];
//const num2x = num.map((n) => n * 2);
//console.log(num2x); // [6, 16, 22, 14, 10]
//Aquí, cada elemento de la matriz num se multiplica por dos y se almacena en una nueva matriz.