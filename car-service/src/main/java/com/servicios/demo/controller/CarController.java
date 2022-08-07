package com.servicios.demo.controller;

import com.servicios.demo.entity.Car;
import com.servicios.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
          List<Car> Cars = carService.getAll();
          if(Cars.isEmpty())
              return ResponseEntity.noContent().build();
          return  ResponseEntity.ok(Cars);

    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Car>> getCarByUserId(@PathVariable("userId") int userId){
        List<Car> cars = carService.byUserId(userId);
        if(cars.isEmpty())
            return ResponseEntity.noContent().build();
        return  ResponseEntity.ok(cars);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable("id") int id){
        Car cars = carService.getCarById(id);
        if(cars == null)
            return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(cars);

    }

    @PostMapping()
    public ResponseEntity<Car> save(@RequestBody Car car){
        Car carNew = carService.save(car);
        return  ResponseEntity.ok(carNew);

    }
}
