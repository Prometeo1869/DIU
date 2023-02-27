import React, { Component } from "react";
import AgendaDataService from "../services/agenda.service"; //importa axios
import { Link } from "react-router-dom";
import { useState } from "react";



export class DetalleContacto extends Component {

  constructor(props) {
    super(props);
    this.deletePerson = this.deletePerson.bind(this);

    this.state = {
      currentPerson: null
    };
  }

  componentDidMount() { }

  deletePerson() {
    if (window.confirm("¿Seguro que quieres eliminar el contacto?")) {
      AgendaDataService.delete(this.props.person.id)
        .then(response => {
          console.log(response.data);
          this.props.refresh();
          alert("Contacto borrado correctamente");
        })
        .catch(e => {
          console.log(e);
        });
    }
  }

  render() {
    const currentPerson = this.props.person;
    const f = i => currentPerson.birthday.charAt(i);

    return (<div>

      {/*Renderizado condicional. La opción depende de si person es null */}
      {currentPerson ? (
        <div className="ml-2">
          <h4 className="mb-5 text-danger">Contacto</h4>
          <div className="mb-2">
            <label>
              <strong>Nombre:</strong>
            </label>{" "}
            {currentPerson.firstName}
          </div>
          <div className="mb-2">
            <label>
              <strong>Apellido:</strong>
            </label>{" "}
            {currentPerson.lastName}
          </div>
          <div className="mb-2">
            <label>
              <strong>Calle:</strong>
            </label>{" "}
            {currentPerson.street}
          </div>
          <div className="mb-2">
            <label>
              <strong>C.P:</strong>
            </label>{" "}
            {currentPerson.postalCode}
          </div>
          <div className="mb-2">
            <label>
              <strong>Ciudad:</strong>
            </label>{" "}
            {currentPerson.city}
          </div>
          <div className="mb-5">
            <label>
              <strong>Fecha de nacimiento:</strong>
            </label>{" "}
            {f(8) + f(9) + "/" + f(5) + f(6) + "/" + f(0) + f(1) + f(2) + f(3)}
          </div>

          <div className="mt-5">
            <button type="button" className="btn btn-danger m-2"
              onClick={this.deletePerson}>
              Borrar
            </button>

            <button type="button" className="btn btn-success m-2">
              Editar
            </button>

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


