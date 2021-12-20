package com.ciclo4.reto3.service;

import com.ciclo4.reto3.model.Product;
import com.ciclo4.reto3.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author rei
 */

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  public List<Product> getAll() {
    return productRepository.getAll();
  }

  public Optional<Product> getProduct(int id) {
    return productRepository.getProduct(id);
  }

  // Sacado de la tutoria del profe Cristian, gracias profe :)
  public Product create(Product product) {

    // obtiene el maximo id existente en la coleccion
    Optional<Product> productLastId = productRepository.lastUserId();

    // si el id del Usaurio que se recibe como parametro es nulo, entonces valida el
    // maximo id existente en base de datos
    if (product.getId() == 0) {
      // valida el maximo id generado, si no hay ninguno aun el primer id sera 1
      if (!productLastId.isPresent())
        product.setId(1);
      // si retorna informacion suma 1 al maximo id existente y lo asigna como el
      // codigo del usuario
      else
        product.setId(productLastId.get().getId() + 1);

    }

    Optional<Product> existProduct = productRepository.getProduct(product.getId());
    if (!existProduct.isPresent()) {
      return productRepository.create(product);
    } else {
      return product;
    }
  }

  /**
   * 
   * @param product
   * @return
   */
  public Product update(Product product) {
    if (product.getId() != 0) {
      Optional<Product> optionalProduct = productRepository.getProduct(product.getId());
      if (optionalProduct.isPresent()) {
        if (product.getBrand() != null) {
          optionalProduct.get().setBrand(product.getBrand());
        }
        if (product.getCategory() != null) {
          optionalProduct.get().setCategory(product.getCategory());
        }
        if (product.getPresentation() != null) {
          optionalProduct.get().setPresentation(product.getPresentation());
        }
        if (product.getPrice() != null) {
          optionalProduct.get().setPrice(product.getPrice());
        }
        if (product.getDescription() != null) {
          optionalProduct.get().setDescription(product.getDescription());
        }
        if (product.getPrice() != 0.00) {
          optionalProduct.get().setPrice(product.getPrice());
        }
        if (product.getQuantity() != 0) {
          optionalProduct.get().setQuantity(product.getQuantity());
        }
        if (product.getPhotography() != null) {
          optionalProduct.get().setPhotography(product.getPhotography());
        }
        if (product.getPhotography() != null) {
          optionalProduct.get().setPhotography(product.getPhotography());
        }
        optionalProduct.get().setAvailability(product.getAvailability());
        productRepository.update(optionalProduct.get());
        return optionalProduct.get();
      } else {
        return product;
      }
    } else {
      return product;
    }
  }

  /**
   * 
   * @param id
   * @return
   */

  public boolean delete(int id) {
    Optional<Product> productOptional = productRepository.getProduct(id);
    if (productOptional.isPresent()) {
      productRepository.delete(productOptional.get());
      return true;
    }
    return false;
  }

  public List<Product> gadgetsByPrice(double price) {
    return productRepository.gadgetsByPrice(price);
  }

  public List<Product> findByDescriptionLike(String description) {
    return productRepository.findByDescriptionLike(description);
  }

}
