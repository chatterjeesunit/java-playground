package com.integration.redis;

import com.lambdaworks.redis.LettuceFutures;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.RedisURI;
import com.lambdaworks.redis.SetArgs;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.api.sync.RedisCommands;
import com.lambdaworks.redis.codec.RedisCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * Created by sunitc on 4/15/18.
 */
public class LettuceStandAloneDemo {

    public static void main(String[] args) {

        //Create the RedisClient instance and provide a Redis URI pointing to localhost, Port 6379
        RedisClient redisClient = RedisClient.create(
                new RedisURI("localhost", 6379, 30, TimeUnit.SECONDS));


        executeSynchronousStringCommands(redisClient);

        executeSynchronousListOperations(redisClient);

        executeSynchronousObjectOperations(redisClient);

        executeAsynchronousObjectOperations(redisClient);

        //shutdown the client
        redisClient.shutdown();

    }



    private static void executeSynchronousStringCommands(RedisClient redisClient) {
        //Open a Redis Standalone connection
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        //Obtain the command API for synchronous execution.
        RedisCommands<String, String> commands = connection.sync();

        String key1 = "auth:type";
        String key2 = "email:client:maxSize";

        //delete existing keys
        commands.del(key1, key2);

        //Simple SET and GET commands
        commands.set(key1, "LDAP");


        //Simple SET command with a timeout value specified in SetArgs.
        commands.set(key2 , "30", SetArgs.Builder.ex(3600));


        //Simple GET Commands.
        String value1 = commands.get(key1);
        System.out.println(key1 + " = " + value1);

        String value2 = commands.get(key2);
        Long ttl2 = commands.ttl(key2);
        System.out.println(key2 + " = " + value2 + " ; TTL = " + ttl2);

        //Close the connection
        connection.close();
    }


    private static void executeSynchronousListOperations(RedisClient redisClient) {
        //Open a new Connection
        StatefulRedisConnection<String, Object> connection = redisClient.connect(new SerializedObjectCodec());

        //Returns synchronous command API for current connection.
        RedisCommands<String, Object> commands = connection.sync();

        String key = "users:skills";

        //delete existing keys
        commands.del(key);

        //Execute list command LPUSH
        commands.lpush(key, "Java", "Scala", "Spring", "Elastic Search", "Mongo DB", "Redis");

        System.out.println("Total Items in List = " + commands.llen(key));

        //Executes list command RPUSH
        commands.rpush(key, "Solr", "Jenkins", "Git");

        System.out.println("List values = " + commands.lrange(key, 0, -1));

        //Execute list command RPOP
        commands.rpop(key);

        //Iterates the list using LRANGE
        System.out.println("List values = " + commands.lrange(key, 0, -1));

        //Close Connection
        connection.close();
    }

    private static void executeSynchronousObjectOperations(RedisClient redisClient) {
        //Open a new Connection
        StatefulRedisConnection<String, Object> connection = redisClient.connect(new SerializedObjectCodec());

        //Returns synchronous command API for current connection.
        RedisCommands<String, Object> commands = connection.sync();

        String key = "users:sync";

        //delete existing keys
        commands.del(key);

        //Create person records
        Person person1 = new Person("John Doe", 35, new Address("San Francisco", "CA", "US"));
        Person person2 = new Person("Samantha", 21, new Address("Portland", "OR", "US"));


        //Execute hash command HSET
        commands.hset(key, "1000", person1);
        commands.hset(key, "1001", person2);


        //Execute hash command HEXISTS
        System.out.println(commands.hexists(key, "1000"));

        //Execute hash command HGETALL
        commands.hgetall(key)
                .entrySet()
                .stream()
                .forEach(entry ->
                    System.out.println(entry.getKey() + " : " + (Person)entry.getValue()));

        //Close Connection
        connection.close();
    }


    private static void executeAsynchronousObjectOperations(RedisClient redisClient) {
        //Open a new Connection
        StatefulRedisConnection<String, Object> connection = redisClient.connect(new SerializedObjectCodec());

        //Returns asynchronous commands API for given connection
        RedisAsyncCommands<String, Object> commands = connection.async();

        String key = "users:async";

        //Del the key from the cache (synchronously) first.
        connection.sync().del(key);


        //Create the person records
        Person person1 = new Person("John Doe", 35, new Address("San Francisco", "CA", "US"));
        Person person2 = new Person("Samantha", 21, new Address("Portland", "OR", "US"));


        //Create redis future commands for HSET
        RedisFuture<Boolean> command1 = commands.hset(key, "1002", person1);
        RedisFuture<Boolean> command2 = commands.hset(key, "1003", person2);

        //Wait for async commands (command1 and command2) to complete
        LettuceFutures.awaitAll(120, TimeUnit.SECONDS, command1, command2);

        //Execute Future Command HEXISTS
        commands.hexists(key, "1003").thenAccept(System.out::println);


        //Execute Future Command HGET, using awaitOrCancel
        Person value1 =
                (Person)LettuceFutures
                        .awaitOrCancel(commands.hget(key, "1002"), 60, TimeUnit.SECONDS);

        System.out.println("Value1 : " + value1);

        //Execute Future Command HGETALL, using whenComplete
        //The results don't get printed always.
        //Sometimes we get the exception: java.util.concurrent.CancellationException
        commands.hgetall(key).whenComplete( (result, ex) -> {
            if(result != null)
                System.out.println("All Results : " + result);
            else
                System.out.println("All Results : " + ex.toString());
        });


        //Execute Future Command HGETALL, using thenAccept
        //Does not always prints the results
        commands.hgetall(key).thenAccept(map ->
            map.entrySet().stream().forEach(entry ->
                    System.out.println("Results : " + entry.getKey() + " : " + (Person)entry.getValue())));


        //Close Connection
        connection.close();
    }
}


class SerializedObjectCodec implements RedisCodec<String, Object> {

    private Charset charset = Charset.forName("UTF-8");

    @Override
    public String decodeKey(ByteBuffer bytes) {
        return charset.decode(bytes).toString();
    }

    @Override
    public Object decodeValue(ByteBuffer bytes) {
        try {
            byte[] array = new byte[bytes.remaining()];
            bytes.get(array);
            ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(array));
            return is.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ByteBuffer encodeKey(String key) {
        return charset.encode(key);
    }

    @Override
    public ByteBuffer encodeValue(Object value) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bytes);
            os.writeObject(value);
            return ByteBuffer.wrap(bytes.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }
}
