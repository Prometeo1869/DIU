import http from "../http-common";

class AgendaDataService {
  
  create(data) {
    return http.post("/persons", data);
  }

  getAll() {
    return http.get("/persons");
  }

  findById(id) {
    return http.get(`/persons/${id}`);
  }

  findByTitle(lastName) {
    return http.get(`/persons/${lastName}`);
  }

  getSortFirstName() {
    return http.get("/persons_nameorder");
  }

  getSortLastName() {
    return http.get("/persons_lastname");
  }

  update(id, data) {
    return http.put(`/persons/${id}`, data);
  }

  delete(id) {
    return http.delete(`/persons/${id}`);
  }

  deleteAll() {
    return http.delete(`/persons`);
  }

}

export default new AgendaDataService();