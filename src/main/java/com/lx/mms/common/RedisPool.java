package com.lx.mms.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Slf4j
@Component
public class RedisPool {

    @Autowired
    private ShardedJedisPool shardedJedisPool;

    public ShardedJedis instance(){
        return shardedJedisPool.getResource();
    }

    public void safeClose(ShardedJedis shardedJedis){
        try {
            shardedJedis.close();
        } catch (Exception e) {
            log.error("关闭 redis 连接出错", e);
            e.printStackTrace();
        }
    }
}
