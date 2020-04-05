package com.lx.mms.service.impl;

import com.google.common.base.Joiner;
import com.lx.mms.common.CacheKeysConstants;
import com.lx.mms.common.RedisPool;
import com.lx.mms.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

@Slf4j
@Service
public class SysCacheService {

    @Autowired
    private RedisPool redisPool;


    public void saveCache(String value, int timeoutSeconds, CacheKeysConstants prefix){
        saveCache(value, timeoutSeconds, prefix, null);
    }

    public void saveCache(String value, int timeoutSeconds, CacheKeysConstants prefix, String ... keys){
        if (value == null){
            return;
        }

        ShardedJedis shardedJedis = null;
        try {
            // 生成 key 值
            String cacheKey = generateCacheKey(prefix, keys);
            // 获取到 redis 客户端
            shardedJedis = redisPool.instance();
            // 调用 redis 保存
            shardedJedis.setex(cacheKey, timeoutSeconds, value);
        } catch (Exception e) {
            log.error("保存到 redis 中出错，prefix = {}, keys = {}", prefix, JsonMapper.obj2String(keys));
            e.printStackTrace();
        }finally {
            // 关闭连接
            redisPool.safeClose(shardedJedis);
        }

    }

    public String getFromCache(CacheKeysConstants prefix, String ... keys){
        String cacheKey = generateCacheKey(prefix, keys);
        ShardedJedis shardedJedis = null;

        try {
            shardedJedis = redisPool.instance();
            return shardedJedis.get(cacheKey);
        } catch (Exception e) {
            log.error("从缓存中取数据失败，prefix = {}, keys = {}", prefix, keys);
            e.printStackTrace();
            return null;
        }finally {
            redisPool.safeClose(shardedJedis);
        }
    }
    private String generateCacheKey(CacheKeysConstants prefix, String ... keys){
        StringBuilder key = new StringBuilder(prefix.name());
        if (keys != null && keys.length > 0){
            key.append("_").append(Joiner.on("_").join(keys));
        }
        return key.toString();
    }
}
