import http from "../http-common";

class ProductDataService {
  getAll() {
    return http.get("/products");
  }

  create(data) {
    return http.post("/products", data);
  }

  update(id, data) {
    return http.put(`/products/${id}`, data);
  }

  delete(id) {
    return http.delete(`/products/${id}`);
  }

}

export default new ProductsDataService();
