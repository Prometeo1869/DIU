import React, { Component } from "react";
import ProductDataService from "../services/product.service"; //importa axios
import { Link } from "react-router-dom";

export default class ProductList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
    this.retrieveProducts = this.retrieveProducts.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveTutorial = this.setActiveTutorial.bind(this);
    this.removeAllTutorials = this.removeAllTutorials.bind(this);
    

    //Hacemos el bind de los métodos porque al usar estos métodos en gestores de eventos los componentes basados
    //en clases pierden el ámbito.
    this.state = {
      tutorials: [], //lista de tutoriales
      currentTutorial: null, //tutorial seleccionado de la lista
      currentIndex: -1,
      searchTitle: ""
    };
  }
  //Cuando se carga el componente, se realiza la petición de tutoriales a la API
  //El método retrieveTutorials provoca la actualización del estado, y por tanto la re-renderización del componente
  componentDidMount() {
    this.retrieveProducts();
  }

  onChangeSearchTitle(e) {
    const searchTitle = e.target.value;

    this.setState({
      searchTitle: searchTitle
    });
  }

  retrieveProducts() {
    ProductDataService.getAll()
      .then(response => {
        this.setState({
          tutorials: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveTutorials();
    this.setState({
      currentTutorial: null,
      currentIndex: -1
    });
  }

  setActiveTutorial(tutorial, index) {
    this.setState({
      currentTutorial: tutorial,
      currentIndex: index
    });
  }

  removeAllTutorials() {
    ProductDataService.deleteAll()
      .then(response => {
        console.log(response.data);
        this.refreshList();
      })
      .catch(e => {
        console.log(e);
      });
  }


  render() {
    const { tutorials, currentTutorial, currentIndex } = this.state;
    //ponemos los distintos elementos del estado en variables para simplificar su acceso dentro del método
    return (
      <div className="list row">

        <div className="col-md-6">
          <h4>Lista de Productos</h4>

          <ul className="list-group">
            {/*El operedor && lógico. Los dos elementos tienen que ser true, en este caso no vacio, para que se ejecute la sentencia */}
            {/*si tutorials está vacio , no se ejecuta el map*/}

            {tutorials && //Si el array no está vacio
              tutorials.map((tutorial, index) => (
                <li
              /* Cambiamos la clase del elemento de la lista seleccionado. Ponemos fondo azul*/
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setActiveTutorial(tutorial, index)}
                  key={index}
                >
                  {tutorial.title}
                </li>
              ))}
          </ul>

          <button
            className="m-3 btn btn-sm btn-danger"
            onClick={this.removeAllTutorials}
          >
            Borrar Todo
          </button>
        </div>
        <div className="col-md-6">
          {/*Renderizado condicional. Si current tutorial el null se dibuja lo de abajo. Si no,*/}
          {/*se dibuja "Please click on a Tutorial..." ver más abajo.*/}
          {currentTutorial ? (
            <div>
              <h4>Producto</h4>
              <div>
                <label>
                  <strong>Id:</strong>
                </label>{" "}
                {currentTutorial.id}
              </div>
              <div>
                <label>
                  <strong>Stock:</strong>
                </label>{" "}
                {currentTutorial.stock}
              </div>

              <div>
                <label>
                  <strong>Precio:</strong>
                </label>{" "}
                {currentTutorial.price}
              </div>



              <Link
                //Como hemos incluido en el switch esta ruta, /tutorials/+id se renderizará el componente
                // tutorials cuando se pulse el enlace.
                to={"/tutorials/" + currentTutorial.id}
                className="badge badge-warning"
              >
                Edit
              </Link>
              
            </div>
          ) : (
            <div>
              <br />
              <p>Selecciona un Producto...</p>
            </div>
          )}
        </div>
      </div>
    );
  }
}
