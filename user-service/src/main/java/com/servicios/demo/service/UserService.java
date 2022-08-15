package com.servicios.demo.service;

import com.servicios.demo.entity.User;
import com.servicios.demo.feignclients.BikeFeignClient;
import com.servicios.demo.feignclients.CarFeignClient;
import com.servicios.demo.model.Bike;
import com.servicios.demo.model.Car;
import com.servicios.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    //restTemplate ayuda a enlistar y conectar a otros ms
    @Autowired
    RestTemplate restTemplate;

    //FeignClient ayuda a conectar con otrs ms y hacer crud
    @Autowired
    CarFeignClient carFeignClient;

    @Autowired
    BikeFeignClient bikeFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(int userId){
        List<Car> cars = restTemplate.getForObject("http://car-service/car/byUser/"+userId, List.class);
        return cars;
    }

    public List<Bike> getBikes(int userId){
        List<Bike> bikes = restTemplate.getForObject("http://bike-service/bike/byUser/"+userId, List.class);
        return bikes;
    }

    public Car saveCar(int userId, Car car){
        car.setUserId(userId);
        Car carnew = carFeignClient.save(car);
        return carnew;
    }

    public Bike saveBike(int userId, Bike bike){
        bike.setUserId(userId);
        Bike bikeNew = bikeFeignClient.save(bike);
        return bikeNew;
    }

    public Map<String,Object> getUserAndVehicles(int userId){
        Map<String,Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if (user == null){
            result.put("Mensaje","No existe el usuario");
            return result;
        }
        result.put("User",user);
        List<Car> cars = carFeignClient.getCars(userId);
        if(cars== null){
            result.put("Cars","Usuario sin coches");
        }else {
            result.put("Cars", cars);
        }

        List<Bike> bikes = bikeFeignClient.getBikes(userId);
        if(bikes == null){
            result.put("Bikes","Usuario sin motos");
        }else {
            result.put("Bikes", bikes);
        }

        return result;

    }




}
