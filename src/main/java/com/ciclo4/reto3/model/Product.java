package com.ciclo4.reto3.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  private int id;
  private String brand;
  private String category;
  private String presentation;
  private String description;
  private Double price;
  private Boolean availability;
  private int quantity;
  private String photography;
}
