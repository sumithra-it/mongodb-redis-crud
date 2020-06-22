package com.redis.rediscrud.service;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import java.util.List;
import com.redis.rediscrud.model.FruitModel;
import lombok.extern.slf4j.Slf4j;
import com.redis.rediscrud.service.FruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.Optional;

@Slf4j
@Service
public class FruitService {

	@Autowired
    private final FruitRepository fruitRepository;
	
	@Autowired
	MongoTemplate mongotemplate;

    public FruitService(FruitRepository fruitRepository) {
    	this.fruitRepository = fruitRepository;
    }

    @Cacheable(value="fruit")
    public FruitModel createFruit(FruitModel fruitModel) {
    	log.info("Not found in cache: Inside Save: Cache will be written by save method now");
        return fruitRepository.save(fruitModel);
    }

    @CachePut(value="fruit", key="#id")
    public FruitModel updateFruitByIdWorking(String id, FruitModel fruitModel) {
    	log.info("Not found in cache. UpdateFruitById called");
    	String result = null;
    	if (fruitRepository.existsById(id)) {
    		return fruitRepository.save(fruitModel);	
    	} else {
    		result = "Nothing to update. Not found in DB." ;
    	}
    	log.info(result);
    	return null;
    }  
    
    @CachePut(value="fruit", key="#fruitModel.id")
    public FruitModel updateFruitById(String id, FruitModel fruitModel) {
    	String result = null;
    	log.info("Not found in cache. UpdateFruitById called");
    	if (fruitRepository.existsById(id)) {
    		return fruitRepository.save(fruitModel);	
    	} else {
    		result = "Nothing to update. Not found in DB." ;
    	}
    	log.info(result);
    	return null;
    }  
    
  // do not cache all. Cache is needs to be refreshed, if there are update calls. 
    public List<FruitModel> findAll() {
    	log.info("Not found in cache. Loading findAll from Db");
        return fruitRepository.findAll();
    }

    @Cacheable(value="fruit", key="#id")
	public FruitModel findFruitById(String id) {
		log.info("Not found in cache. Inside find: Fetching by id: " + id);
		Optional<FruitModel> opt = fruitRepository.findById(id);
		if (!opt.isPresent()) return null;
		return opt.get();
	}
	
    @Cacheable(cacheNames = "myCache")
    public String cacheThis(){
        log.info("Returning NOT from cache!");
        return "this Is it";
    }
    
    //TODO: This method needs to be tested - Doesnt return any results
	public List<FruitModel> findFruitByName(String name) {
		log.info("Not found in cache. Fetching by name: " + name);
		List<FruitModel> list = fruitRepository.findByName(name);
		log.info("Found by name: {} matches", list.size());
		return list;
	}
}
