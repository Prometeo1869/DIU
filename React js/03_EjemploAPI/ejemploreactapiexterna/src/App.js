import React, { Component } from 'react';
import { Row, Col, Container } from 'react-bootstrap';
import Table_Example from './components/TableExample';
import Form_Example from './components/FormExample';

class App extends Component {
  constructor() {
    super()
    this.state = {
      data: [], //Contiene todas las filas de la tabla
      userId: '', //Contenido caja de texto userID
      title: '', //Contenido caja de texto Title
      body: ''//Contenido caja de texto Body
    }
  }

  passParams= (data) => {
    let dataNew = this.state.data; //let declara variables de ámbito local
    dataNew.push(data) //El método push anexa nuevos elementos a un array
    this.setState({
      data: dataNew
    });
  }
  //Called immediately after a component is mounted. Setting state here will trigger re-rendering.
  //Consulatar diagrama ciclo de vida del componente
  componentDidMount() {
    fetch('https://jsonplaceholder.typicode.com/posts')//Hacemos la llamada a la API externa
       //Si la Consulta es exitosa se devuelve el json obtenido, si no, mensaje error
       //Después se actualiza el estado de componente.
       //.then es como se consumen las "promise"  que básicamente son un proxy para un valor
       //que estará disponible próximamente. Una manera de gestionar código asíncrono
       .then((response) => { //primera "promise"
            if (response.ok) {
                return response.json(); //Lo que devuelve esta función será la entrada de la segunda "promise"
            } else {
                throw new Error(response.statusText);
            }
        })
        .then((data) => { //segunda "promise"
            this.setState({
                data: data
            });
        })
  }

  render() {
    return (
      <Container>
        <Row>
          <Col>
            <Form_Example passParams={this.passParams} />
          </Col>
        </Row>
        <Row>
          <Col>
            <Table_Example data={this.state.data}/>
          </Col>
        </Row>
      </Container>
    );
  }
}

export default App;
