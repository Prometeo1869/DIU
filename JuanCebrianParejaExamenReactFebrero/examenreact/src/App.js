import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AddProduct from "./components/add-product.component";
import Product from "./components/product.component";
import ProductList from "./components/products-list.component";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <Link to={"/products"} className="navbar-brand">
            PRODUCTOS
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/products"} className="nav-link">
                Productos
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/add"} className="nav-link">
                Añadir
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/products"]} component={ProductList} />
            <Route exact path="/add" component={AddProduct} />
            <Route path="/products/:id" component={Product} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
