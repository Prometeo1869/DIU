import React, { Component } from "react";
import ProductDataService from "../services/product.service";

export default class Product extends Component {
  constructor(props) {
    super(props);
    this.onChangeStock = this.onChangeStock.bind(this);
    this.onChangePrice = this.onChangePrice.bind(this);
    this.getProduct = this.getProduct.bind(this);
    this.updatePrice = this.updatePrice.bind(this);
    this.updateProduct = this.updateProduct.bind(this);
    this.deleteProduct = this.deleteProduct.bind(this);

    this.state = {
      currentProduct: {
        id: null,
        stock: 0,
        price: 0
      },
      message: ""
    };
  }

  componentDidMount() {
    this.getProduct(this.props.match.params.id);
  }

  onChangeStock(e) {
    const stock = e.target.value;

    this.setState(function(prevState) {
      return {
        currentProduct: {
          ...prevState.currentProduct,
          stock: stock
        }
      };
    });
  }

  onChangePrice(e) {
    const price = e.target.value;
    
    this.setState(prevState => ({
      currentProduct: {
        ...prevState.currentProduct,
        price: price
      }
    }));
  }

  getProduct(id) {
    ProductDataService.get(id)
      .then(response => {
        this.setState({
          currentTutorial: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updatePrice(status) {
    var data = {
      id: this.state.currentProduct.id,
      stock: this.state.currentProduct.stock,
      price: status
    };

    ProductDataService.update(this.state.currentProduct.id, data)
      .then(response => {
        this.setState(prevState => ({
          currentProduct: {
            ...prevState.currentProduct,
            price: status
          }
        }));
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  updateProduct() {
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

  deleteProduct() {    
    ProductDataService.delete(this.state.currentProduct.id)
      .then(response => {
        console.log(response.data);
        this.props.history.push('/products')
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { currentProduct } = this.state;

    return (
      <div>
        {currentProduct ? (
          <div className="edit-form">
            <h4>Producto</h4>
            <form>
              <div className="form-group">
                <label htmlFor="title">Id</label>
                <input
                  type="text"
                  className="form-control"
                  id="id"
                  value={currentProduct.id}
                  onChange={this.onChangeId}
                />
              </div>
              <div className="form-group">
                <label htmlFor="description">Stock</label>
                <input
                  type="text"
                  className="form-control"
                  id="stock"
                  value={currentProduct.stock}
                  onChange={this.onChangeStock}
                />
              </div>

              <div className="form-group">
                <label htmlFor="description">Price</label>
                <input
                  type="text"
                  className="form-control"
                  id="price"
                  value={currentProduct.price}
                  onChange={this.onChangePrice}
                />
              </div>
            </form>

            <button
              className="badge badge-danger mr-2"
              onClick={this.deleteProduct}
            >
              Borrar
            </button>

            <button
              type="submit"
              className="badge badge-success"
              onClick={this.updateProduct}
            >
              Modificar
            </button>
            <p>{this.state.message}</p>
          </div>
        ) : (
          <div>
            <br />
            <p>Por favor selecciona un Producto...</p>
          </div>
        )}
      </div>
    );
  }
}
