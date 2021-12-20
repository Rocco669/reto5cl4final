package com.ciclo4.reto3.repository.crud;

import com.ciclo4.reto3.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderCrudRepository extends MongoRepository<Order, Integer> {

  @Query("{'salesMan.zone': ?0}")
  List<Order> findByZone(final String country);

  @Query("{status: ?0}")
  List<Order> findByStatus(final String status);

  // Para seleccionar la orden con el id maximo
  Optional<Order> findTopByOrderByIdDesc();

}
