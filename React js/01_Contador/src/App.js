import { Component, useState } from 'react';
import './App.css';
import ContadorComponent from './components/ContadorComponent';
import ButtonComponent from './components/ButtonComponent';
 
//Como clase
/*
class App extends Component {

  constructor() {
    super()
    this.state = {
      contador: 0
    }
  }

  incremento = (event) => {
    this.setState({
      contador: ++this.state.contador
    })
  }

  decremento =(event) => {
    this.setState({
      contador: --this.state.contador
    })
  }

  reset = (event) => {
    this.setState({
      contador: this.contador = 0
    })
  }

  render() {
    return (
    <div className="App">
      <header className="App-header">
        <ContadorComponent cont={this.state.contador}></ContadorComponent>
        <ButtonComponent cont={this.state.contador} 
                        incremento={this.incremento} 
                        decremento={this.decremento} 
                        reset={this.reset}>
    </ButtonComponent>

      </header>
    </div>
    );
  }
}*/

//Como funcion usando hooks
function App() {

  const [contador, setCount] = useState(0);

  const incremento = () => setCount(contador + 1);
  const decremento = () => setCount(contador - 1);
  const reset = () => setCount(0);

  return (
    <div className="App">
      <header className="App-header">
        <ContadorComponent cont={contador}></ContadorComponent>
        <ButtonComponent 
                        incremento={incremento} 
                        decremento={decremento} 
                        reset={reset}
                        clase={'buttonsRed'}>
        </ButtonComponent>
      </header>
    </div>
    );
} 

export default App;
