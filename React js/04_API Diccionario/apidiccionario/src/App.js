import React, { Component } from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import Tabla from './components/Tabla';
import Formulario from './components/Formulario';
import './App.css';


class App extends Component {
  constructor() {
    super()
    this.state = {
      data: [], //Contiene todas las filas de la tabla
      word: ''
    }
  }

  passParams= (data) => {
    let dataNew = this.state.data; //let declara variables de ámbito local
    console.log(data);
    dataNew.push(data) //El método push anexa nuevos elementos a un array
    this.setState({
      data: dataNew
    });
  }

  render() {
    return (
      <Container className='container'>
          <Row>
            <Col>
              <Formulario passParams={this.passParams} />
            </Col>
          </Row>
          <Row>
            <Col>
              <Tabla data={this.state.data} />
            </Col>
          </Row>
      </Container>
    );
  }
}

export default App;
