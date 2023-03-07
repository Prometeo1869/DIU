import React, { useContext } from "react";
import { BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import Application from "./componentes/Application";
import "bootstrap/dist/css/bootstrap.min.css";
import './styles/App.css';

import { ListaContactos } from "./componentes/TablaContactos.component";
import { EditaContacto } from "./componentes/Editar.component";
import { AddContacto } from "./componentes/Add.component";
import { UserContext } from "./providers/UserProvider";

export function App() {
  const user = useContext(UserContext);
  return (

    <Router>
      <header className="App-header">
        <nav className="navbar navbar-expand navbar-dark bg-dark row">
          <Link to={"/"} className="navbar-brand col-2">
            <h1 className="p-auto text-white">AGENDA</h1>
          </Link>
          <div className="navbar-nav mx-5 col-4">
            <li className="nav-item mx-2">
              <Link to={"/persons"} className="nav-link">
                <h5 className="text-warning">Contactos</h5>
              </Link>
            </li>
            {user ?
              <li className="nav-item mx-2">
                <Link to={"/add"} className="nav-link">
                  <h5 className="text-warning">Añadir</h5>
                </Link>
              </li>
              :
              <li></li>
            }

          </div>
          
              <Application />
            
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
