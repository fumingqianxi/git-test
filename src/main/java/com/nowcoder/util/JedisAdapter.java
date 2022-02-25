package com.nowcoder.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.util.List;

@Service
public class JedisAdapter implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);
    private JedisPool pool = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool();
    }

    public String get(String key){
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            return jedis.get(key);
        }catch (Exception e){
            logger.error("发生错误" + e.getMessage());
            return null;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public void set(String key, String value) {
        Jedis jedis = null;
        try{
            jedis = pool.getResource();
            jedis.set(key, value);
        }catch (Exception e){
            logger.error("发生错误" + e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public Jedis getJedis(){
        return pool.getResource();
    }

    public long sadd(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.sadd(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long srem(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = getJedis();
            return jedis.srem(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public boolean sismember(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public long scard(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public static  void print(int index, Object obj) {
        System.out.println(String.format("%d, %s", index, obj.toString()));
    }

    public long lpush(String key, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.lpush(key, value);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return 0;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public List<String> brpop(int timeout, String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.brpop(timeout, key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
            return null;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

//    public static void main(String[] args) {
//        Jedis jedis = new Jedis();
//        jedis.flushAll();
//
//        jedis.set("hello", "world");
//        print(1, jedis.get("hello"));
//        jedis.rename("hello", "newhello");
//        print(1, jedis.get("newhello"));
//        jedis.setex("hello2", 15, "world");
//
//        jedis.set("pv", "100");
//        jedis.incr("pv");
//        print(2, jedis.get("pv"));
//        jedis.incrBy("pv", 5);
//        print(2, jedis.get("pv"));
//
//        String listName = "listA";
//        for (int i = 0; i < 10; i++) {
//            jedis.lpush(listName, "a" + String.valueOf(i));
//        }
//        print(3, jedis.lrange(listName, 0, 12));
//        print(4, jedis.llen(listName));
//        print(5, jedis.lpop(listName));
//        print(6, jedis.lindex(listName, 3));
//        print(7, jedis.linsert(listName, BinaryClient.LIST_POSITION.AFTER, "a4", "xx"));
//        print(8, jedis.linsert(listName, BinaryClient.LIST_POSITION.BEFORE, "a4", "yy"));
//        print(9, jedis.lrange(listName, 0, 12));
//
//        String userKey = "user12";
//        jedis.hset(userKey, "name", "john");
//        jedis.hset(userKey, "age", "18");
//        jedis.hset(userKey, "phone", "1812312312");
//        print(11, jedis.hget(userKey, "name"));
//        print(12, jedis.hgetAll(userKey));
//        jedis.hdel(userKey, "phone");
//        print(13, jedis.hgetAll(userKey));
//        print(14, jedis.hkeys(userKey));
//        print(15, jedis.hvals(userKey));
//        print(16, jedis.hexists(userKey, "email"));
//        print(17, jedis.hexists(userKey, "age"));
//        jedis.hsetnx(userKey, "school", "zju");
//        jedis.hsetnx(userKey, "school", "yxy");
//        print(18, jedis.hgetAll(userKey));
//
//        String likeKeys1 = "newLike1";
//        String likeKeys2 = "newLike2";
//        for (int i = 0; i < 10; i++) {
//            jedis.sadd(likeKeys1, String.valueOf(i));
//            jedis.sadd(likeKeys2, String.valueOf(i * 2));
//        }
//        print(20, jedis.smembers(likeKeys1));
//        print(21, jedis.smembers(likeKeys2));
//        print(22, jedis.sinter(likeKeys1, likeKeys2));
//        print(23, jedis.sunion(likeKeys1, likeKeys2));
//        print(24, jedis.sdiff(likeKeys1, likeKeys2));
//
//        String rankKey = "rankKey";
//        jedis.zadd(rankKey, 50, "Jim");
//        jedis.zadd(rankKey, 60, "Bob");
//        jedis.zadd(rankKey, 80, "John");
//        jedis.zadd(rankKey, 70, "Mei");
//        jedis.zadd(rankKey, 90, "Lee");
//        print(30, jedis.zcard(rankKey));
//        print(31, jedis.zcount(rankKey, 60, 90));
//        print(32, jedis.zscore(rankKey, "John"));
//        jedis.zincrby(rankKey, 2, "John");
//        print(33, jedis.zscore(rankKey, "John"));
//        jedis.zincrby(rankKey, 2, "Joh");
//        print(34, jedis.zcount(rankKey, 0, 100));
//        print(35, jedis.zrange(rankKey, 1, 3));
//        print(36, jedis.zrevrange(rankKey, 1, 3));
//
//        for (Tuple tuple : jedis.zrangeByScoreWithScores(rankKey, "0", "100")) {
//            print(37, tuple.getElement() + ":" + tuple.getScore());
//        }
//        print(37, jedis.zrank(rankKey, "John"));
//        print(38, jedis.zrevrank(rankKey, "John"));
//
//        JedisPool pool = new JedisPool();
//        for (int i = 0; i < 100; i++) {
//            Jedis j = pool.getResource();
//            //j.get("a");
//            System.out.println("POOL" + i);
//            j.close();
//        }
//    }

    public void setObject(String key, Object obj) {
        set(key, JSON.toJSONString(obj));
    }

    public <T> T getObject(String key, Class<T> clazz) {
        String value = get(key);
        if (value != null) {
            return JSON.parseObject(value, clazz);
        }
        return null;
    }
}
