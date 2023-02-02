import axios from "axios";

export default axios.create({
  baseURL: "http://ec2-54-83-139-137.compute-1.amazonaws.com",
  headers: {
    "Content-type": "application/json"
  }
});
