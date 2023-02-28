import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
//import styles from '../styles/BarraProgreso.module.css';

function ProgressBar(props) {
    const progreso = props.total * 100 / 50;
    return (
            <div className="progress">
                <div className="progress-bar progress-bar-striped bg-warning text-dark progress-bar-animated"
                role="progressbar"
                style={{width: progreso ? progreso +"%": "100%"}}>{progreso ? progreso +"%": "100%"}
                </div>
            </div>            
    );
}


export default ProgressBar;