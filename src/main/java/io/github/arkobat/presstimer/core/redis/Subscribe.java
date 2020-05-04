package io.github.arkobat.presstimer.core.redis;

import lombok.RequiredArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class Subscribe {

    private final Connection connection;
    private final String channel;

    private void listen() {
        try {
            JedisShardInfo jedisShardInfo = new JedisShardInfo(connection.getHost(), connection.getPort());
            jedisShardInfo.setPassword(connection.getPassword());
            Jedis listen = new Jedis(jedisShardInfo);
            CompletableFuture.runAsync(() -> listen.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    System.out.println("Received redis msg: " + message);

                }
            }, channel));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
