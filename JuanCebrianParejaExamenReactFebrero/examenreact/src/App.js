import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import ProductList from "./components/products-list.component";

class App extends Component {
  render() {
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-primary">
          <h1 className="navbar-brand">
            <strong>PRODUCTOS</strong> 
          </h1>
        </nav>

        <div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/products"]} component={ProductList} />
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
