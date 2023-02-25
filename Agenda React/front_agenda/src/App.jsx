import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';

import { ListaContactos } from "./componentes/tabla_contactos.component";
import { DetalleContacto } from "./componentes/detalle_contacto.component";


export function App() {
  return (
    <Router>
      <header className="App-header">
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          {/*<Link to={"/persons"} className="navbar-brand">
            Agenda
  </Link>*/}
          <h1 className="p-auto text-white">AGENDA</h1>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              {/*<Link to={"/persons"} className="nav-link">
                Personas
</Link>*/}
            </li>
            <li className="nav-item">
              {/*<Link to={"/add"} className="nav-link">
                Add
</Link>*/}
            </li>
          </div>
        </nav>

        <div className="container mt-3">

        </div>

      </header>
      <main>
        <Switch>
          <Route exact path={["/", "/persons"]} component={ListaContactos} />
          {/*<Route path="/persons/:id" component={DetalleContacto}></Route>*/}
        </Switch>
      </main>
    </Router>
  );
}
