package com.pluralsight.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;
import redis.clients.jedis.Protocol;

public class redisApp {

    public static void main(String[] args) {
        JedisPool pool;
        try {
            URI redisURI = new URI(System.getenv("REDIS_URL"));
            pool = new JedisPool(new JedisPoolConfig(),
                    redisURI.getHost(),
                    redisURI.getPort(),
                    Protocol.DEFAULT_TIMEOUT,
                    redisURI.getUserInfo().split(":",2)[1]);
        } catch (URISyntaxException e) {
            throw new RuntimeException("Redis couldn't be configured from URL in REDISTOGO_URL env var ");
        }
        System.out.println("Adding Key/values to Redis Store");
        System.out.println("=================================");
        try {
            Jedis jedis = pool.getResource();
            for(int i = 11; i < 20; i++) {
                System.out.println("Setting Key "+i);
                jedis.set("key"+i, "value"+i);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
