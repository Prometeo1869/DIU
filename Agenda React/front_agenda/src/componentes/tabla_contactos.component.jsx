import React, { Component } from "react";
import AgendaDataService from "../services/agenda.service"; //importa axios

import { DetalleContacto } from "./detalle_contacto.component";

export class ListaContactos extends Component {
  constructor(props) {
    super(props);
    //this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
    this.retrieveAgenda = this.retrieveAgenda.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActivePerson = this.setActivePerson.bind(this);
    //this.removeAllTutorials = this.removeAllTutorials.bind(this);
    //this.searchTitle = this.searchTitle.bind(this);
    //Hacemos el bind de los métodos porque al usar estos métodos en gestores de eventos los componentes basados
    //en clases pierden el ámbito.
    this.state = {
      agenda: [], //lista de tutoriales
      currentPerson: null, //tutorial seleccionado de la lista
      currentIndex: -1
    };
  }

  //Cuando se carga el componente, se realiza la petición de productos a la API
  //El método retrieveProducts provoca la actualización del estado, y por tanto la re-renderización del componente
  componentDidMount() {
    this.retrieveAgenda();
  }

  retrieveAgenda() {
    AgendaDataService.getAll()
      .then(response => {
        this.setState({
          agenda: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }
  setActivePerson(person, index) {
    this.setState({
      currentPerson: person,
      currentIndex: index
    });
  }

  refreshList() {
    this.retrieveAgenda();
    this.setState({
      currentPerson: null,
      currentIndex: -1
    });
  }

  render() {
    //Convertir en variables los distintos elementos del state 
    const { agenda, currentPerson, currentIndex } = this.state;

    return (
      <div className="container mt-5">
        <div className="list row">
          <div className="col-5 m-1">
            <DetalleContacto person={currentPerson}></DetalleContacto>
          </div>
          <div className="col-5 m-1">
            <table className="table">
              {/*El operedor && lógico. Los dos elementos tienen que ser true, en este caso no vacio, para que se ejecute la sentencia */}
              {/*si tutorials está vacio , no se ejecuta el map*/}
              <thead className="bg-danger text-white">
                <tr>
                  <th scope="col">NOMBRE</th>
                  <th scope="col">APELLIDO</th>
                </tr>
              </thead>
              <tbody>
                {agenda && //Si el array no está vacio
                  agenda.map((persona, index) => (
                    <tr
                      // Cambiamos la clase del elemento de la lista seleccionado. Ponemos fondo azul
                      className={
                        "row-group-item " +
                        (index === currentIndex ? "bg-success text-white" : "")
                      }
                      onClick={() => this.setActivePerson(persona, index)}
                      key={index}
                    >
                      <td scope="row">{persona.firstName} </td>
                      <td>{persona.lastName}</td>
                    </tr>
                  ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    );
  }
}