package com.ciclo4.reto3.controller;

import com.ciclo4.reto3.model.User;
import com.ciclo4.reto3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

  /**
   * Instancia del UserService con anotaciones de Spring
   */
  @Autowired
  private UserService userService;

  /**
   * Metodo para obtener todos los usuarios, utilizando la capa userService
   * 
   * @return userService.getAll()
   */
  @GetMapping("/all")
  public List<User> getAllUsers() {
    return userService.getAll();
  }

  /**
   * Método para buscar los usuarios por ID
   * 
   * @param id
   * @return userService.getUser(id)
   */
  @GetMapping("{id}")
  public Optional<User> getUser(@PathVariable("id") int id) {
    return userService.getUser(id);
  }

  /**
   * Metodo para saber si un email ya esta registrado a traves del endpoint
   * 
   * @param email
   * @return userService.existEmail(email)
   */
  @GetMapping("/emailexist/{email}")
  public boolean existEmail(@PathVariable("email") String email) {
    return userService.existEmail(email);
  }

  /**
   * Metodo para saber si existe / autenicar un usuario con la combinacion email /
   * contraseña
   * 
   * @param email
   * @param password
   * @return userService.authenticateUser(email, password)
   */
  @GetMapping("/{email}/{password}")
  public User authenticateUser(@PathVariable("email") String email, @PathVariable("password") String password) {
    return userService.authenticateUser(email, password);
  }

  @GetMapping("/birthday/{month}")
  public List<User> birthtDayList(@PathVariable("month") String monthBirthtDay) {
    return userService.birthtDayList(monthBirthtDay);
  }

  /**
   * Metodo para crear un nuevo usuario
   * 
   * @param user
   * @return userService.create(user)
   */
  @PostMapping("/new")
  @ResponseStatus(HttpStatus.CREATED)
  public User create(@RequestBody User user) {
    return userService.create(user);
  }

  /**
   * Metodo para actualizar los datos de un usuario
   * 
   * @param user
   * @return userService.update(user)
   */
  @PutMapping("/update")
  @ResponseStatus(HttpStatus.CREATED)
  public User update(@RequestBody User user) {
    return userService.update(user);
  }

  /**
   * Metodo para borrar un usuario a traves del ID
   * 
   * @param id
   * @return userService.delete(id)
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public boolean delete(@PathVariable("id") int id) {
    return userService.delete(id);
  }

}
