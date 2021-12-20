package com.ciclo4.reto3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "usuarios")
@Data // Para no escribir los get y set
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  private Integer id;
  private String identification;
  private String name;
  private Date birthtDay;
  private String monthBirthtDay;
  private String address;
  private String cellPhone;
  private String email;
  private String password;
  private String zone;
  private String type;

}
