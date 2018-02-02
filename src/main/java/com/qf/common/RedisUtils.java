package com.qf.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *  @author 田宇
 *  @date 2018/1/30 0030 13:36
 *  @description 操作Redis缓存数据库
 */
public class RedisUtils {

    @Autowired
    private static RedisTemplate<String,String> redisTemplate;

    public static void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    /*
    从数据库中拿出的数据默认升序[1,2,3,4,5]
    从头压进list时[5,4,3,2,1]
    */
    public static void lpush(String key,String value){
        redisTemplate.opsForList().rightPush(key,value);
    }

    //2.拿出索引为0的那条数据，就是id最大的数据
    public static String getFirst(String key){

       return (String) redisTemplate.opsForList().index(key,0l);
    }

    //3.验证是否存在指定的key
    public static boolean exists(String key){
        return redisTemplate.hasKey(key);
    }
}
