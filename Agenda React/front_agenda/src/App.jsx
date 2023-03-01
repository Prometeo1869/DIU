import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import './styles/App.css';

import { ListaContactos } from "./componentes/TablaContactos.component";
import { EditaContacto } from "./componentes/Editar.component";
import { AddContacto } from "./componentes/Add.component";


export function App() {
  return (
    <Router>
      <header className="App-header">
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          {/*<Link to={"/persons"} className="navbar-brand">
            Agenda
  </Link>*/}
          <h1 className="p-auto text-white">AGENDA</h1>
          <div className="navbar-nav mx-5">
            <li className="nav-item mx-2">
              <Link to={"/persons"} className="nav-link">
                <h5 className="text-warning">Contactos</h5>
              </Link>
            </li>
            <li className="nav-item mx-2">
              <Link to={"/add"} className="nav-link">
                <h5 className="text-warning">Añadir</h5>
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">

        </div>

      </header>
      <main>
        <Switch>
          <Route exact path={["/", "/persons"]} component={ListaContactos} />
          <Route path="/persons/:id" component={EditaContacto}></Route>
          <Route path="/add" component={AddContacto}></Route>
        </Switch>
      </main>
    </Router>


  );
}
