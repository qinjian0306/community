package org.chinamyheart.community.redis;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * 第三步: 创建jedisPool和jedisPoolConfig对象 并创建自己封装的JedisCli对象 用的时候只需要注入即可使用
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class JedisConfig {

    @Bean
    public JedisPoolConfig jedisPoolConfig(RedisProperties redisProperties) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisProperties.getPool().getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisProperties.getPool().getMaxIdle());
        jedisPoolConfig.setMinIdle(redisProperties.getPool().getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperties.getPool().getMaxWaitMillis());
        jedisPoolConfig.setTestWhileIdle(redisProperties.getPool().isTestWhileIdle());
        jedisPoolConfig.setTestOnBorrow(redisProperties.getPool().isTestOnBorrow());
        jedisPoolConfig.setTestOnReturn(redisProperties.getPool().isTestOnReturn());
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig, RedisProperties redisProperties) {
        return new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(),
                redisProperties.getTimeout(), redisProperties.getPassword());
    }

    /**
     * 封装的jedis对象
     * @param jedisPool
     * @return
     */
    @Bean
    public JedisCli jedisCli(JedisPool jedisPool) {
        JedisCli jedisCli = new JedisCli();
        jedisCli.setJedisPool(jedisPool);
        return jedisCli;
    }
}
