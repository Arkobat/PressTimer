package io.github.arkobat.presstimer.core.redis;

import lombok.AccessLevel;
import lombok.Getter;
import redis.clients.jedis.*;

import java.time.Duration;

@Getter(AccessLevel.PACKAGE)
public class Connection {

    private String host;
    private int port;
    private String password;
    private final JedisPool jedisPool;

    public Connection(String host, int port, int timeout, String password) {
        this.host = host;
        this.port = port;
        this.password = password;
        this.jedisPool = new JedisPool(buildPoolConfig(), host, port, timeout, password, false);
    }

    private JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
