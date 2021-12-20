package com.ciclo4.reto3.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import com.ciclo4.reto3.model.Order;
import com.ciclo4.reto3.repository.crud.OrderCrudRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepository {

  @Autowired
  private OrderCrudRepository orderCrudRepository;

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<Order> getAll() {
    return (List<Order>) orderCrudRepository.findAll();
  }

  public Optional<Order> getOrder(int id) {
    return orderCrudRepository.findById(id);
  }

  public Order create(Order order) {
    return orderCrudRepository.save(order);
  }

  public void update(Order order) {
    orderCrudRepository.save(order);
  }

  public void delete(Order order) {
    orderCrudRepository.delete(order);
  }

  public Optional<Order> lastUserId() {
    return orderCrudRepository.findTopByOrderByIdDesc();
  }

  // Ordenes de pedido asociadas a los asesores de una zona
  public List<Order> findByZone(String zona) {
    return orderCrudRepository.findByZone(zona);
  }

  // metodos reto4
  public List<Order> ordersSalesManByID(Integer id) {
    Query query = new Query();

    Criteria criterio = Criteria.where("salesMan.id").is(id);
    query.addCriteria(criterio);

    List<Order> orders = mongoTemplate.find(query, Order.class);

    return orders;
  }

  public List<Order> ordersSalesManByState(String state, Integer id) {
    Query query = new Query();
    Criteria criterio = Criteria.where("salesMan.id").is(id)
        .and("status").is(state);

    query.addCriteria(criterio);

    List<Order> orders = mongoTemplate.find(query, Order.class);

    return orders;
  }

  // ordenes del asesor por fecha
  public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Query query = new Query();

    Criteria dateCriteria = Criteria.where("registerDay")
        .gte(LocalDate.parse(dateStr, dtf).minusDays(1).atStartOfDay())
        .lt(LocalDate.parse(dateStr, dtf).plusDays(1).atStartOfDay())
        .and("salesMan.id").is(id);

    query.addCriteria(dateCriteria);

    List<Order> orders = mongoTemplate.find(query, Order.class);

    return orders;
  }

}
