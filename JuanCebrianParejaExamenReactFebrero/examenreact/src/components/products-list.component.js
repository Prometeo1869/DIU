import React, { Component } from "react";
import ProductDataService from "../services/product.service"; //importa axios
import { Link } from "react-router-dom";

export default class ProductList extends Component {
  constructor(props) {
    super(props);
    this.retrieveProducts = this.retrieveProducts.bind(this);
    this.refreshList = this.refreshList.bind(this);
    this.setActiveProduct = this.setActiveProduct.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.suma10 = this.suma10.bind(this);
    

    //Hacemos el bind de los métodos porque al usar estos métodos en gestores de eventos los componentes basados
    //en clases pierden el ámbito.
    this.state = {
      products: [],         //lista de productos
      currentProduct: null, //producto seleccionado de la lista
      currentIndex: -1,
      searchTitle: "",
      value: 0
    };
  }
  //Cuando se carga el componente, se realiza la petición de productos a la API
  //El método retrieveProducts provoca la actualización del estado, y por tanto la re-renderización del componente
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
      currentIndex: -1,
      value: 0
    });
  }

  setActiveProduct(producto, index) {
    this.setState({
      currentProduct: producto,
      currentIndex: index
    });
  }

  handleChange(e) {
    this.setState({value: e.target.value});
    
  }

  suma10() {

    this.state.currentProduct.stock += 10;

      ProductDataService.update(
        this.state.currentProduct.id,
        this.state.currentProduct
      )
        .then(response => {
          console.log(response.data);
          this.setState({
            message: "El producto fue modificado!"
          });
        })
        .catch(e => {
          console.log(e);
        });
    
  }


  render() {
    const { products, currentProduct, currentIndex } = this.state;
    //ponemos los distintos elementos del estado en variables para simplificar su acceso dentro del método
    return (
      <div className="list row">

        <div className="col-md-6">
          <h4>Lista de Productos</h4>

          <table className="table">
            {/*El operedor && lógico. Los dos elementos tienen que ser true, en este caso no vacio, para que se ejecute la sentencia */}
            {/*si tutorials está vacio , no se ejecuta el map*/}
            <thead>
              <tr>
                <th scope="col">Nombre</th>
                <th scope="col">Precio</th>
              </tr>
            </thead>
            <tbody>
              
            {products && //Si el array no está vacio
              products.map((producto, index) => (
                <tr
              /* Cambiamos la clase del elemento de la lista seleccionado. Ponemos fondo azul*/
                  className={
                    "row-group-item " +
                    (index === currentIndex ? "bg-primary text-white" : "")
                  }
                  onClick={() => this.setActiveProduct(producto, index)}
                  key={index}
                ><td scope="row">{producto.name} </td><td>{producto.price} €</td>
                </tr>
              ))}
              
          </tbody>
          </table>

          {/*<button
            className="m-3 btn btn-sm btn-danger"
            onClick={this.removeAllTutorials}
          >
            Borrar Todo
                </button>*/}
        </div>
        
        <div className="col-md-6 mt-5">
          {/*Renderizado condicional. Si current producto el null se dibuja lo de abajo. Si no,*/}
          {/*se dibuja "Please click on a Producto..." ver más abajo.*/}
          {currentProduct ? (
            <div>
              <h4>{currentProduct.name}</h4>

              <div>
              <label>
                  <strong>Unidades:</strong>
                </label>{" "}
              <input 
                id="unidades"
                type="number" 
                class="form-control" 
                placeholder="Introduce una cantidad" 
                max={currentProduct.stock}
                min="0"
                value={this.state.value} 
                onChange={this.handleChange}
              />
              </div>
              
              <div className="mt-3">
                <label>
                  <strong>Total:</strong>
                </label>{" "}
                {this.state.value * currentProduct.price}€
                
              </div>
              
              <div>
                <label>
                  <strong>Unidades en Stock:</strong>
                </label>{" "}
                {currentProduct.stock}
              </div>

              <div>
                {(currentProduct.stock >= 10) ? 
                <label className="text-success">
                  Unidades ok                  
                </label> 
                :
                <label className="text-danger">
                Unidades bajas                  
              </label> }   
                
              </div>
              <button
                type="button" 
                className="btn btn-warning mt-5"
                onClick={this.suma10}
              >
                SUMAR 10 UNIDADES
              </button>
              
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
