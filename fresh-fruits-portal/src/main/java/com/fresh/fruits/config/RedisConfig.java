package com.fresh.fruits.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * SpringBoot整合Redis配置
 */
@Configuration
public class RedisConfig {

    /**
     * 1.创建JedisPoolConfig对象。在该对象中完成一些链接池配置
     * @ConfigurationProperties:会将前缀相同的内容创建一个实体。
     */
    @Bean("jedisPoolConfig")
    @ConfigurationProperties(prefix="spring.redis.pool")
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        return config;
    }

    /**
     * 2.创建JedisConnectionFactory：配置redis链接信息
     */
    @Bean("jedisConnectionFactory")
    @ConfigurationProperties(prefix="spring.redis")
    public JedisConnectionFactory jedisConnectionFactory(@Qualifier("jedisPoolConfig") JedisPoolConfig config){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setPoolConfig(config);
        return factory;
    }

    /**
     * 3.创建RedisTemplate:用于执行Redis操作的方法
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(@Qualifier("jedisConnectionFactory") JedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();

        //创建Json序列化对象
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        //解决查询缓存转换异常的问题
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setKeySerializer(jackson2JsonRedisSerializer);// key序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);// value序列化

        //关联
        template.setConnectionFactory(factory);
        return template;
    }
}