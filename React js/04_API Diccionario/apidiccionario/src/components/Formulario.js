import React, { Component } from 'react';
import { Form, Button, Row, Col, FormGroup } from 'react-bootstrap';

class Formulario extends Component {

    constructor() {
        super()
        this.state = {
            word: ''
        }
    }
    //Método que gestiona cuando se produce un cambio en algún campo del formualario
    //Obtiene la info del evento y actualiza el estado
    //La actualización del estado, implica la llamada al método render()
    handleChange = event => {
        const word = event.target.name;
        const value = event.target.value;
        this.setState({ [word]: value });
    }

    //Método que gestiona la pulsación del botón
    handleSubmit = event => {
        let loading = document.getElementById('loading');
        loading.style.display = 'block';
        document.querySelector('#bt').disabled = true;
        document.querySelector('#bt').innerHTML = 'Sending...';
        event.preventDefault();

        fetch('https://api.dictionaryapi.dev/api/v2/entries/en/' + this.state.word, {

            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        })
            .then((response) => { //Primera "Promise"

                if (response.ok) {
                    return response.json();
                } else {
                    alert("The word doesn't exist");
                    loading.style.display = 'none';
                    document.querySelector('#bt').disabled = false;
                    document.querySelector('#bt').innerHTML = 'Send';
                    
                    throw new Error(response.statusText);
                }
            })
            .then(data => { //Segunda "Promise"
                loading.style.display = 'none';
                document.querySelector('#bt').disabled = false;
                document.querySelector('#bt').innerHTML = 'Send';
                this.props.passParams(data);
            })
    }

    render() {
        return (
            <Form onSubmit={this.handleSubmit}>
                <Row>
                    <Col>
                        <FormGroup>
                            <Form.Label>Word:</Form.Label>
                            <Form.Control required placeholder="Enter a word" name="word" value={this.state.word} onChange={this.handleChange} />
                        </FormGroup>
                    </Col>
                </Row>
                <Row>
                    <FormGroup>
                        <Button id="bt" className='boton' type="submit" >Send</Button>
                        <div id="loading">loading...</div>
                    </FormGroup>
                </Row>
            </Form>)
    }

}

export default Formulario;
