/*
package com.laywerspringboot.edition.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

*/
/**
 * @Author:小七
 * @createTime:2020-11-08-17-02
 *//*




    */
/**
     *
     * @author kk
     * 配置redis序列化
     *//*

    @Configuration
    @EnableCaching
    public class RedisConfig extends CachingConfigurerSupport {
        */
/**
         * 选择redis作为默认缓存工具(此种方式使用@Cacheable的时候值是Hex格式)
         * 与下面的方法二选一
         * @param
         * @return

         @Bean public CacheManager cacheManager(RedisConnectionFactory factory) {
         RedisCacheManager rcm = RedisCacheManager.create(factory);
         return rcm;
         }
         *//*



        */
/**
         * 选择redis作为默认缓存工具(此种方式使用@Cacheable的时候值是json格式)
         * 与上面的方法二选一
         *
         * @param
         * @return
         *//*

        @Bean
        public CacheManager cacheManager(RedisTemplate redisTemplate) {
            RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
            RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                    .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));

            return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);

        }

        */
/**
         * retemplate相关配置
         *
         * @param factory
         * @return
         *//*

        @Bean
        public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

            RedisTemplate<String, String> template = new RedisTemplate<>();
            // 配置连接工厂
            template.setConnectionFactory(factory);

            //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
            Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

            ObjectMapper om = new ObjectMapper();
            // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jacksonSeial.setObjectMapper(om);

            // 值采用json序列化
            //template.setValueSerializer(jacksonSeial);
            template.setValueSerializer(new StringRedisSerializer());
            //使用StringRedisSerializer来序列化和反序列化redis的key值
            template.setKeySerializer(new StringRedisSerializer());

            // 设置hash key 和value序列化模式
            template.setHashKeySerializer(new StringRedisSerializer());
            template.setHashValueSerializer(jacksonSeial);
            template.afterPropertiesSet();
            return template;
        }

    }

*/
