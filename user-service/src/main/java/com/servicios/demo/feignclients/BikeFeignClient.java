package com.servicios.demo.feignclients;

import com.servicios.demo.model.Bike;
import com.servicios.demo.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "bike-service",  path = "/bike" )
public interface BikeFeignClient {

    @PostMapping()
    Bike save(@RequestBody Bike car);

    @GetMapping("/byUser/{userId}")
    List<Bike> getBikes(@PathVariable("userId" )int userId);
}
