package com.ciclo4.reto3;

import com.ciclo4.reto3.repository.crud.ProductCrudRepository;
import com.ciclo4.reto3.repository.crud.OrderCrudRepository;
import com.ciclo4.reto3.repository.crud.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Reto3Application implements CommandLineRunner {

  @Autowired
  private UserCrudRepository userCrudRepository;

  @Autowired
  private ProductCrudRepository productCrudRepository;

  @Autowired
  private OrderCrudRepository orderCrudRepository;

  public static void main(String[] args) {
    SpringApplication.run(Reto3Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    userCrudRepository.deleteAll(); // Para que la base de datos se borre por cada ejecución
    productCrudRepository.deleteAll();
    orderCrudRepository.deleteAll();
  }

}
