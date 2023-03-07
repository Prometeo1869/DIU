import React, { useState } from "react";
import { Link } from "react-router-dom";
import { auth } from "../firebase";


const SignIn = () => {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);

  const signInWithEmailAndPasswordHandler = (event, email, password) => {
    event.preventDefault();
    auth.signInWithEmailAndPassword(email, password).catch(error => {
      setError("Error de identificación!");
      console.error("Error signing in with password and email", error);
    });
  };

  const onChangeHandler = (event) => {
    const { name, value } = event.currentTarget;

    if (name === 'userEmail') {
      setEmail(value);
    }
    else if (name === 'userPassword') {
      setPassword(value);
    }
  };


  return (
<div className="navbar-nav mx-5 col-4 justify-content-end align-self-center">
      <div className="nav-item mx-2">
        <form className="form-group">      
          <div className="input-group m-2">
            <label htmlFor="userEmail" className="input-group-text">
              Email:
            </label>
            <input
              type="email"
              className="form-control p-auto"
              name="userEmail"
              value={email}
              placeholder="E.g: prueba@gmail.com"
              id="userEmail"
              onChange={(event) => onChangeHandler(event)}
            />
          </div>
          <div className="input-group m-2">
            <label htmlFor="userPassword" className="input-group-text">
              Password:
            </label>
            <input
              type="password"
              className="form-control"
              name="userPassword"
              value={password}
              placeholder="Your Password"
              id="userPassword"
              onChange={(event) => onChangeHandler(event)}
            />
          </div>

        </form>
      </div>
      <div className="nav-item mx-2 text-center">
        <button className="btn btn-success m-2" onClick={(event) => { signInWithEmailAndPasswordHandler(event, email, password) }}>
          Identificarse
        </button>
        {error !== null &&
        <div className="bg-danger text-white text-center m-2">
          {error}
        </div>}
      </div>
      </div>
  );
};

export default SignIn;
