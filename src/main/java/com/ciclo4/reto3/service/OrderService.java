package com.ciclo4.reto3.service;

import com.ciclo4.reto3.model.Order;
import com.ciclo4.reto3.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  public List<Order> getAll() {
    return orderRepository.getAll();
  }

  public Optional<Order> getOrder(int id) {
    return orderRepository.getOrder(id);
  }

  public Order create(Order order) {

    // obtiene el maximo id existente en la coleccion
    Optional<Order> orderIdMaxima = orderRepository.lastUserId();

    // si el id de la orden que se recibe como parametro es nulo, entonces valida el
    // maximo id existente en base de datos
    if (order.getId() == null) {
      // valida el maximo id generado, si no hay ninguno aun el primer id sera 1
      if (!orderIdMaxima.isPresent())
        order.setId(1);
      // si retorna informacion suma 1 al maximo id existente y lo asigna como el
      // codigo de la orden
      else
        order.setId(orderIdMaxima.get().getId() + 1);
    }

    Optional<Order> e = orderRepository.getOrder(order.getId());
    if (!e.isPresent()) {
      return orderRepository.create(order);
    } else {
      return order;
    }
  }

  public Order update(Order order) {

    if (order.getId() != null) {
      Optional<Order> orderDb = orderRepository.getOrder(order.getId());
      if (orderDb.isPresent()) {
        if (order.getStatus() != null) {
          orderDb.get().setStatus(order.getStatus());
        }
        orderRepository.update(orderDb.get());
        return orderDb.get();
      } else {
        return order;
      }
    } else {
      return order;
    }
  }

  public boolean delete(int id) {
    Boolean aBoolean = getOrder(id).map(order -> {
      orderRepository.delete(order);
      return true;
    }).orElse(false);
    return aBoolean;
  }

  // Ordenes de pedido asociadas a los asesores de una zona
  public List<Order> findByZone(String zona) {
    return orderRepository.findByZone(zona);
  }

  // metodos del reto 4
  public List<Order> ordersSalesManByID(Integer id) {
    return orderRepository.ordersSalesManByID(id);
  }

  // Reto 4: Ordenes de un asesor x Estado
  public List<Order> ordersSalesManByState(String state, Integer id) {
    return orderRepository.ordersSalesManByState(state, id);
  }

  // Reto 4: Ordenes de un asesor x fecha
  public List<Order> ordersSalesManByDate(String dateStr, Integer id) {
    return orderRepository.ordersSalesManByDate(dateStr, id);
  }

}
