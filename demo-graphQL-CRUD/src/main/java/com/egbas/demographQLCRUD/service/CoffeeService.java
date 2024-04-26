package com.egbas.demographQLCRUD.service;

import com.egbas.demographQLCRUD.entity.Coffee;
import com.egbas.demographQLCRUD.enums.Size;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CoffeeService {

    List<Coffee> coffeeList = new ArrayList<>();
    AtomicInteger id = new AtomicInteger();

    public List<Coffee> findAll(){
        return coffeeList;
    }

    public Optional<Coffee> findOne(Integer id){
        return coffeeList.stream().filter(coffee ->
            coffee.id() == id
        ).findFirst();
    }

    public Coffee createCoffee(String name, Size size){
        Coffee coffee = new Coffee(id.incrementAndGet(), name, size);
        coffeeList.add(coffee);
        return coffee;
    }

    public Coffee deleteCoffee(Integer id){
        Coffee coffee = coffeeList.stream()
                .filter(
                        c -> c.id() == id
                ).findFirst().orElseThrow(() -> new IllegalArgumentException());

        coffeeList.remove(coffee);
        return coffee;
    }

    public Coffee updateCoffee(Integer id, String name, Size size){
        Coffee updateCoffee = new Coffee(id, name, size);
        Optional<Coffee> optional = coffeeList.stream()
                .filter(
                        c -> c.id() == id
                ).findFirst();

        if (optional.isPresent()){
            Coffee coffee1 = optional.get();
            int index = coffeeList.indexOf(coffee1);
            coffeeList.set(index, updateCoffee);
        } else{
            throw new IllegalArgumentException("Invalid Coffee selected");
        }

        return updateCoffee;
    }

    @PostConstruct
    private void init(){
        coffeeList.add(new Coffee(id.incrementAndGet(), "Capuchinno", Size.MEDIUM));
        coffeeList.add(new Coffee(id.incrementAndGet(), "Mapuchinno", Size.MEDIUM));
        coffeeList.add(new Coffee(id.incrementAndGet(), "Latte", Size.MEDIUM));
        coffeeList.add(new Coffee(id.incrementAndGet(), "Brewed", Size.MEDIUM));
        coffeeList.add(new Coffee(id.incrementAndGet(), "Italianno", Size.MEDIUM));
    }
}
