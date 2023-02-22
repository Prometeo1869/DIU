import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <nav className="navbar navbar-expand navbar-dark bg-dark text-info">
          {/*<Link to={"/persons"} className="navbar-brand">
            Agenda
  </Link>*/}
  <h1 className="p-auto">AGENDA</h1>
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


        {/*<div className="container mt-3">
          <Switch>
            <Route exact path={["/", "/tutorials"]} component={TutorialsList} /> 
          <Route exact path="/add" component={AddTutorial} />
          <Route path="/tutorials/:id" component={Tutorial} />
          </Switch>
</div>*/}


      </header>
    </div>
  );
}

export default App;
