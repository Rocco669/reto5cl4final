package com.ciclo4.reto3.repository;

import com.ciclo4.reto3.model.Product;
import com.ciclo4.reto3.repository.crud.ProductCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

  @Autowired
  private ProductCrudRepository productCrudRepository;

  public List<Product> getAll() {
    return productCrudRepository.findAll();
  }

  public Optional<Product> getProduct(int id) { // Buscar por ID para el metodo delete
    return productCrudRepository.findById(id);
  }

  public Optional<Product> lastUserId() {
    return productCrudRepository.findTopByOrderByIdDesc();
  }

  public Product create(Product product) {
    return productCrudRepository.save(product);
  }

  public void update(Product product) {
    productCrudRepository.save(product);
  }

  public void delete(Product product) {
    productCrudRepository.delete(product);
  }

  public List<Product> gadgetsByPrice(double precio) {
    return productCrudRepository.findByPriceLessThanEqual(precio);
  }

  public List<Product> findByDescriptionLike(String description) {
    return productCrudRepository.findByDescriptionLike(description);
  }

}
