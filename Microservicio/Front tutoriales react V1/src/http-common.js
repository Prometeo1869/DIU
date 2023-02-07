import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8082", // "http://ec2-54-83-139-137.compute-1.amazonws.com"
  headers: {
    "Content-type": "application/json"
  }
});
