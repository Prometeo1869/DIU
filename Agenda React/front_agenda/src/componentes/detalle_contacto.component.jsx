import React, { Component } from "react";
import AgendaDataService from "../services/agenda.service"; //importa axios
import { Link } from "react-router-dom";



export class DetalleContacto extends Component {

    constructor(props) {
        super(props);

    }


    render() {
        const person = this.props.person;
        return (<div>

            {/*Renderizado condicional. Si current tutorial el null se dibuja lo de abajo. Si no,*/}
          {/*se dibuja "Please click on a Tutorial..." ver más abajo.*/}
          {person ? (
            <div>
              <h4>Contacto</h4>
              <div>
                <label>
                  <strong>Nombre:</strong>
                </label>{" "}
                {person.firstName}
              </div>
              <div>
                <label>
                  <strong>Apellido:</strong>
                </label>{" "}
                {person.lastName}
              </div>
              
            </div>
          ) : (
            <div>
              <br />
              <p>Selecciona un contacto para ver sus Detalles...</p>
            </div>
          )}

        </div>
        );
    }
}


