import React, { Component } from "react";
import AgendaDataService from "../services/agenda.service"; //importa axios
import { Link } from "react-router-dom";

export default class TutorialsList extends Component {
  constructor(props) {
    super(props);
    //this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
    this.retrieveAgenda = this.retrieveAgenda.bind(this);
    //this.refreshList = this.refreshList.bind(this);
    this.setActivePerson = this.setActivePerson.bind(this);
    //this.removeAllTutorials = this.removeAllTutorials.bind(this);
    //this.searchTitle = this.searchTitle.bind(this);
    //Hacemos el bind de los métodos porque al usar estos métodos en gestores de eventos los componentes basados
    //en clases pierden el ámbito.
    this.state = {
      agenda: [], //lista de tutoriales
      currentPerson: null, //tutorial seleccionado de la lista
      currentIndex: -1,
      searchLastName: ""
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

  render() {
    //Convertir en variables los distintos elementos del state 
    const {agenda, currentPerson, currentIndex, searchLastName} = this.state;

    return (
        <table className="table">
        {/*El operedor && lógico. Los dos elementos tienen que ser true, en este caso no vacio, para que se ejecute la sentencia */}
        {/*si tutorials está vacio , no se ejecuta el map*/}
        <thead>
          <tr>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
          </tr>
        </thead>
        <tbody>
          {/*<tr><td>Hola</td><td>Adios</td></tr>*/}
        {agenda && //Si el array no está vacio
          agenda.map((persona, index) => (
            <tr
          // Cambiamos la clase del elemento de la lista seleccionado. Ponemos fondo azul
              className={
                "row-group-item " +
                (index === currentIndex ? "bg-primary text-white" : "")
              }
              onClick={() => this.setActivePerson(persona, index)}
              key={index}
            ><td scope="row">{persona.firstName} </td><td>{persona.lastName}</td>
            </tr>
          ))}
          
      </tbody>
      </table>
    );
  }
}