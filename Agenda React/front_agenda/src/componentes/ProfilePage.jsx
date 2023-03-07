import React, { useContext } from "react";
import { UserContext } from "../providers/UserProvider";
import { navigate } from "react-router-dom";
import {auth} from "../firebase";

const ProfilePage = () => {
  const user = useContext(UserContext);
  const {photoURL, displayName, email} = user;
  console.log(user);
  

  return (
    <div className="navbar-nav mx-5 col-4 justify-content-end align-self-center">
      <ul className="list-group list-group-horizontal d-flex align-items-center">
        <li
          style={{
            background: `url(${photoURL || 'https://d500.epimg.net/cincodias/imagenes/2016/07/04/lifestyle/1467646262_522853_1467646344_noticia_normal.jpg'})  no-repeat center center`,
            backgroundSize: "cover",
            height: "50px",
            width: "50px"
          }}
          className="list-group-item align-middle bg-dark"
        ></li>
        <li className="list-group-item align-middle bg-dark">
        <h2 className="text-l text-light font-semibold">{displayName}</h2>
        {/*<h3 className="italic text-light">{email}</h3>*/}
        </li>
      <li className="list-group-item bg-dark">
      <button className="btn btn-danger" onClick = {() => {auth.signOut()}}>Cerrar sesión</button>
      </li>
      </ul>
    </div>
  ) 
};

export default ProfilePage;

