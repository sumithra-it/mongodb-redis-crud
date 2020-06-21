package com.redis.rediscrud.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.redis.core.RedisHash;
import java.io.Serializable;
@Document
@Data
@Builder
@RedisHash("fruit")
public class FruitModel implements Serializable{

    @Id
    private String id;
    @Indexed
    private String name;
    private String color;
    
    private static final long serialVersionUID = 4211223344L;
}
