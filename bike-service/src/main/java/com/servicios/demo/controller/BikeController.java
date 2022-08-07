package com.servicios.demo.controller;

import com.servicios.demo.entity.Bike;
import com.servicios.demo.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bike")
public class BikeController {

    @Autowired
    BikeService bikeService;

    @GetMapping
    public ResponseEntity<List<Bike>> getAll(){
          List<Bike> bikes = bikeService.getAll();
          if(bikes.isEmpty())
              return ResponseEntity.noContent().build();
          return  ResponseEntity.ok(bikes);

    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Bike>> getBikeByUserId(@PathVariable("userId") int userId){
        List<Bike> bikes = bikeService.byUserId(userId);
        if(bikes.isEmpty())
            return ResponseEntity.noContent().build();
        return  ResponseEntity.ok(bikes);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Bike> getBike(@PathVariable("id") int id){
        Bike cars = bikeService.getBikeById(id);
        if(cars == null)
            return ResponseEntity.notFound().build();
        return  ResponseEntity.ok(cars);

    }

    @PostMapping()
    public ResponseEntity<Bike> save(@RequestBody Bike bike){
        Bike bikeNew = bikeService.save(bike);
        return  ResponseEntity.ok(bikeNew);

    }
}
