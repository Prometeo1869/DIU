import React, { Component } from "react";
import AgendaDataService from "../services/agenda.service"; //importa axios

import { DetalleContacto } from "./DetalleContacto.component";
import styles from "../styles/TablaContactos.module.css";
import ProgressBar from "./BarraProgreso.component";

export class ListaContactos extends Component {
  constructor(props) {
    super(props);
    this.retrieveAgenda = this.retrieveAgenda.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActivePerson = this.setActivePerson.bind(this);
    this.nameOrder = this.nameOrder.bind(this);
    this.lastnameOrder = this.lastnameOrder.bind(this);

    this.state = {
      agenda: [],           //lista de tutoriales
      currentPerson: null,  //tutorial seleccionado de la lista
      currentIndex: -1
    };
  }

  //Cuando se carga el componente, se realiza la petición de contactos a la API
  //El método retrieveAgenda provoca la actualización del estado, y por tanto la re-renderización del componente
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

  nameOrder() {
    this.refreshList();
    AgendaDataService.getSortFirstName()
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

  lastnameOrder() {
    this.refreshList();
    AgendaDataService.getSortLastName()
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

  render() {
    //Convertir en variables los distintos elementos del state 
    const { agenda, currentPerson, currentIndex } = this.state;

    return (
      <div className="container mt-5">
        <div className="list row justify-content-center">

          <div className="col-5 m-ml-5">
            <table className="table table-hover">
              {/*El operedor && lógico. Los dos elementos tienen que ser true, en este caso no vacio, para que se ejecute la sentencia */}
              {/*si tutorials está vacio , no se ejecuta el map*/}
              <thead className="bg-danger text-white">
                <tr className={styles.puntero_mano} >
                  <th scope="col" onClick={this.nameOrder} title="Ordena por nombre">NOMBRE &nbsp;&nbsp;🔻</th>
                  <th scope="col" onClick={this.lastnameOrder} title="Ordena por apellido">APELLIDO &nbsp;&nbsp; 🔻</th>
                </tr>
              </thead>
              <tbody className={styles.puntero_mano}>
                {agenda && //Si el array no está vacio
                  agenda.map((persona, index) => (
                    <tr
                      // Cambiamos la clase del elemento de la lista seleccionado. Ponemos fondo azul
                      className={
                        "row-group-item " +
                        (index === currentIndex ? "bg-info text-dark" : "")
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
            <ProgressBar total={agenda.length}></ProgressBar>
          </div>
          <div className="col-5 m-md-5">
            <DetalleContacto person={currentPerson} refresh={this.refreshList}></DetalleContacto>
          </div>
        </div>
        <div className="col-5 m-ml-5">
            
        </div>
      </div>
    );
  }
}