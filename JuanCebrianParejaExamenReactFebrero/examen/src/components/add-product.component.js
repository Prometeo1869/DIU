import React, { Component } from "react";
import ProductDataService from "../services/product.service";

export default class AddProduct extends Component {
    
    constructor(props) {
        super(props);
        this.onChangeId = this.onChangeId.bind(this);
        this.onChangeStock = this.onChangeStock.bind(this);
        this.onChangePrice = this.onChangePrice.bind(this);
        this.addProduct = this.addProduct.bind(this);

        this.state = {
            currentProduct: {
                id: null,
                stock: 0,
                price: 0
            },
            message: ""
        };
    }

    onChangeId(e) {
        const id = e.target.value;

        this.setState(function (prevState) {
            return {
                currentProduct: {
                    ...prevState.currentProduct,
                    id: id
                }
            };
        });
    }

    onChangeStock(e) {
        const stock = e.target.value;

        this.setState(prevState => ({
            currentProduct: {
                ...prevState.currentTutorial,
                stock: stock
            }
        }));
    }

    onChangePrice(e) {
            const price = e.target.checked;

            this.setState(function (prevState) {
                return {
                    currentTutorial: {
                        ...prevState.currentTutorial,
                        price: price
                    }
                };
            });
    }

    addProduct() {
        ProductDataService.create(
            this.state.currentProduct
        )
            .then(response => {
                console.log(response.data);
                this.setState({
                    message: "El producto fue añadido!"
                });
            })
            .catch(e => {
                console.log(e);
            });
    }

    render() {
        return (
            <div>
                    <div className="edit-form">
                        <h4>Producto</h4>
                        <form>
                            <div className="form-group">
                                <label htmlFor="title">Id</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="id"
                                    value={this.state.id}
                                    onChange={this.onChangeId}
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="description">Stock</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="stock"
                                    value={this.state.stock}
                                    onChange={this.onChangeStock}
                                />
                            </div>

                            <div className="form-group">
                                <label htmlFor="description">Price</label>
                                <input
                                    type="text"
                                    className="form-control"
                                    id="price"
                                    value={this.state.price}
                                    onChange={this.onChangePrice}
                                />
                            </div>
                        </form><br></br>

                        <button
                            type="submit"
                            className="badge badge-success"
                            onClick={this.addProduct}
                        >
                            Add
                        </button>
                        <p>{this.state.message}</p>
                    </div>

            </div>
        );
    }
}