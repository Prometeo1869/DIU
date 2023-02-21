import React, { Component } from "react";
import ProductDataService from "../services/product.service"; //importa axios
import { Link } from "react-router-dom";

export default class ProductsList extends Component {
  constructor(props) {
    super(props);
    //this.onChangeSearchTitle = this.onChangeSearchTitle.bind(this);
    this.retrieveProducts = this.retrieveProducts.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveProduct = this.setActiveProduct.bind(this);
    //this.removeAllTutorials = this.removeAllTutorials.bind(this);
    //this.searchTitle = this.searchTitle.bind(this);
    //Hacemos el bind de los métodos porque al usar estos métodos en gestores de eventos los componentes basados
    //en clases pierden el ámbito.
    this.state = {
      products: [],             //lista de productos
      currentProduct: null,    //producto seleccionado de la lista
      currentIndex: -1
    };
  }

  componentDidMount() {
    this.retrieveProducts();
  }

  retrieveProducts() {
    ProductDataService.getAll()
      .then(response => {
        this.setState({
          products: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  refreshList() {
    this.retrieveProducts();
    this.setState({
      currentProduct: null,
      currentIndex: -1
    });
  }

  setActiveProduct(product, index) {
    this.setState({
      currentProduct: product,
      currentIndex: index
    });
  }

  render() {
    const { products, currentProduct, currentIndex } = this.state;
    //ponemos los distintos elementos del estado en variables para simplificar su acceso dentro del método
    return (
      <div className="list row">

        <div className="col-md-6">
          <h4>Lista de Productos</h4>

          <ul className="list-group">
            {/*El operedor && lógico. Los dos elementos tienen que ser true, en este caso no vacio, para que se ejecute la sentencia */}
            {/*si producto está vacio , no se ejecuta el map*/}

            {products && //Si el array no está vacio
              products.map((product, index) => (
                <li
              /* Cambiamos la clase del elemento de la lista seleccionado. Ponemos fondo azul*/
                  className={
                    "list-group-item " +
                    (index === currentIndex ? "active" : "")
                  }
                  onClick={() => this.setActiveProduct(product, index)}
                  key={index}
                >
                  {product.id}
                </li>
              ))}
          </ul>

        </div>
        <div className="col-md-6">
          {/*Renderizado condicional. Si current tutorial el null se dibuja lo de abajo. Si no,*/}
          {/*se dibuja "Please click on a Tutorial..." ver más abajo.*/}
          {currentProduct ? (
            <div>
              <h4>Producto</h4>
              <div>
                <label>
                  <strong>Id:</strong>
                </label>{" "}
                {currentProduct.id}
              </div>
              <div>
                <label>
                  <strong>Stock:</strong>
                </label>{" "}
                {currentProduct.stock}
              </div>
              <div>
                <label>
                  <strong>Price:</strong>
                </label>{" "}
                {currentProduct.price}
              </div>

              <Link
                //Como hemos incluido en el switch esta ruta, /tutorials/+id se renderizará el componente
                // tutorials cuando se pulse el enlace.
                to={"/products/" + currentProduct.id}
                className="badge badge-warning"
              >
                Edit
          </Link>
              
            </div>
          ) : (
            <div>
              <br />
              <p>Selecciona un producto...</p>
            </div>
          )}
        </div>
      </div>
    );
  }
}