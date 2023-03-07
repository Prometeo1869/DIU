import React, { useContext } from "react";
import { BrowserRouter as Router } from "react-router-dom";
import SignIn from "./SignIn";
import ProfilePage from "./ProfilePage";
import { UserContext } from "../providers/UserProvider";

function Application() {
  const user = useContext(UserContext);
  return (
    user ?
      <ProfilePage />
      :
      <Router>

        <SignIn path="/" />

      </Router>
  );
}

export default Application;
