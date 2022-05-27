package br.com.danilomr.redis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@Builder
@RedisHash("people")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private String name;
    private Integer age;

    @TimeToLive
    private Long timeout;
}
