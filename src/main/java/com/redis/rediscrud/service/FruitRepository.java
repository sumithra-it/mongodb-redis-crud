package com.redis.rediscrud.service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import com.redis.rediscrud.model.FruitModel;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface FruitRepository extends MongoRepository<FruitModel ,String> {

	List<FruitModel> findByName(String name);
}
