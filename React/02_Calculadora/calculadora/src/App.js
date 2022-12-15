import './App.css';
import ResultadoComponent from './component/ResultadoComponent';
import CalculadoraComponent from './component/CalculadoraComponent';
import calculate from "./logic/calculate"; 

function App() {

const [resultado, calculate] = useState(0);

  return (
    <div className="App">
      <header className="App-header">
        <ResultadoComponent resultado={resultado}></ResultadoComponent>
        <CalculadoraComponent></CalculadoraComponent>
        
      </header>
    </div>
  );
}

export default App;
