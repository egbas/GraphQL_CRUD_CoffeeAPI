package com.egbas.demographQLCRUD.controller;


import com.egbas.demographQLCRUD.entity.Coffee;
import com.egbas.demographQLCRUD.enums.Size;
import com.egbas.demographQLCRUD.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;

    @QueryMapping
    public List<Coffee> findAll(){
        return coffeeService.findAll();
    }

    @QueryMapping
    public Optional<Coffee> findOne(@Argument Integer id){
        return coffeeService.findOne(id);
    }

    @MutationMapping
    public Coffee createCoffee(@Argument String name, @Argument Size size){
        return coffeeService.createCoffee(name, size);
    }

    @MutationMapping
    public Coffee updateCoffee(@Argument Integer id, @Argument String name, @Argument  Size size){
        return coffeeService.updateCoffee(id, name, size);
    }

    @MutationMapping
    public Coffee deleteCoffee(@Argument Integer id){
        return coffeeService.deleteCoffee(id);
    }
}
