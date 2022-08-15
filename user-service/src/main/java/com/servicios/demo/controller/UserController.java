package com.servicios.demo.controller;

import com.servicios.demo.entity.User;
import com.servicios.demo.model.Bike;
import com.servicios.demo.model.Car;
import com.servicios.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
          List<User> users = userService.getAll();
          if(users.isEmpty())
              return ResponseEntity.noContent().build();
          return  ResponseEntity.ok(users);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        User users = userService.getUserById(id);
        if(users == null)
            return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(users);

    }

    @PostMapping()
    public ResponseEntity<User> save(@RequestBody User user){
        User userNew = userService.save(user);
        return  ResponseEntity.ok(userNew);

    }

    @GetMapping("/cars/{userId}")
    public ResponseEntity<List<Car>> getCars(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Car> cars = userService.getCars(userId);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/bikes/{userId}")
    public ResponseEntity<List<Bike>> getBikes(@PathVariable("userId") int userId){
        User user = userService.getUserById(userId);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Bike> bikes = userService.getBikes(userId);
        return ResponseEntity.ok(bikes);
    }

    @PostMapping("saveCar/{userId}")
    public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car){
        if(userService.getUserById(userId)==null)
            return ResponseEntity.notFound().build();
        Car carNew = userService.saveCar(userId,car);
        return ResponseEntity.ok(carNew);
    }

    @PostMapping("saveBike/{userId}")
    public ResponseEntity<Bike> saveCar(@PathVariable("userId") int userId, @RequestBody Bike bike){
        if(userService.getUserById(userId)==null)
            return ResponseEntity.notFound().build();
        Bike bikeNew = userService.saveBike(userId,bike);
        return ResponseEntity.ok(bikeNew);
    }

    @GetMapping("/getAll/{userId}")
        public ResponseEntity<Map<String,Object>> getAllVehicles(@PathVariable("userId") int userId){
          Map<String, Object> result = userService.getUserAndVehicles(userId);
          return ResponseEntity.ok(result);
        }
    }
