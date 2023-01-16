import {Component} from "react";

import ResultadoComponent from './component/ResultadoComponent';
import CalculadoraComponent from './component/CalculadoraComponent';

import calculate from "./logic/calculate";

import "./App.css";

export default class App extends Component {

  state = {
    total: null,
    next: null,
    operation: null
  }

  handleClick = buttonName => this.setState(calculate(this.state, buttonName))

  render() {
    return (
      <div className="component-app">
        <ResultadoComponent value={this.state.next || this.state.total || "0"} />
        <CalculadoraComponent clickHandle={this.handleClick} />
      </div>
    );
  }
}
