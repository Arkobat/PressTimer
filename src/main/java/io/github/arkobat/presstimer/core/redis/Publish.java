package io.github.arkobat.presstimer.core.redis;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;

@RequiredArgsConstructor
public class Publish {

    private final Connection connection;


    private void publishString(String channel, String message) {
        try (Jedis jedis = connection.getJedisPool().getResource()) {
            System.out.println(message);
            jedis.publish(channel, message);
        }
    }


}
