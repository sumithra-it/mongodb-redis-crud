package com.redis.rediscrud.service;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import com.redis.rediscrud.model.FruitModel;

@Data
public class FruitRequest {
    private String name;
    private String color;
    public FruitModel toFruitModel(){
       return FruitModel.builder().color(this.color).name(this.name).build();
    }
}
