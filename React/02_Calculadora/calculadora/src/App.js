import './App.css';
import ResultadoComponent from './component/ResultadoComponent';
import CalculadoraComponent from './component/CalculadoraComponent';
import calculate from "./logic/calculate"; 
import { useState } from 'react';

function App() {

const [total, setTotal] = useState(0);
const [next, setNext] = useState(0);
const [operation, setOperation] = useState(0);

const numero1 = () => setTotal(calculate.calculate(this, this.attr('id')));
const numero2 = () => setNext(calculate.calculate(this, this.attr('id')));
const operat = () => setOperation(calculate.calculate(this, this.attr('id')));



  return (
    <div className="App">
      <header className="App-header">
        <ResultadoComponent result={total}></ResultadoComponent>
        <CalculadoraComponent num1={numero1} num2={numero2} operat={operation}></CalculadoraComponent>
        
      </header>
    </div>
  );
}

export default App;
