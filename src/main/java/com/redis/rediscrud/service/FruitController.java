package com.redis.rediscrud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.redis.rediscrud.model.FruitModel;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@Slf4j
public class FruitController {
	
	@Autowired
    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {this.fruitService = fruitService;}

    @PostMapping("/fruit")
    public ResponseEntity addFruit(@RequestBody FruitModel fruitModel) {
        log.info("Request received to add fruit: {}", fruitModel);
        fruitService.createFruit(fruitModel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/fruit/{id}")
    public ResponseEntity updateFruitById(@PathVariable String id, @RequestBody FruitModel fruitModel) {
    	fruitService.updateFruitById(id, fruitModel);
    	log.info("Fetch fruit with Id: {} request received", id);
    	if (fruitModel != null) 
    		return ResponseEntity.status(HttpStatus.OK).build();
    	else 
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    
    @GetMapping("/fruits")
    public List<FruitModel> getAllFruit() {
    	log.info("Fetch all fruits");
        return fruitService.findAll();
    }
    
    @GetMapping("/fruit/{id}")
    public FruitModel findFruitById(@PathVariable String id) {
    	FruitModel fruit = fruitService.findFruitById(id);
    	log.info("Fetch fruit with Id: {} and found in the result {}", id,
    									fruit);
    	
    	return fruit;
    }
    
    @GetMapping("/fruit/name/{name}")
    public List<FruitModel> findFruitByName(@PathVariable String name) {
    	List<FruitModel> fruitlist = fruitService.findFruitByName(name);
    	log.info("Fetch fruit with name: {} and found in the result {}", name,
    									fruitlist.size());
    	return fruitlist;
    }
    
    @GetMapping("/cachetest")
    public String cachetest() {
        return fruitService.cacheThis();
    }
    
    
}
