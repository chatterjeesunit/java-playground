package com.play.integration.redis;

import org.apache.commons.lang.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sunitc on 4/17/18.
 */
public class JedisStandAloneDemo {

    public static void main(String[] args) {
        jedisStandAloneConnect();
        jedisPoolConnect();
    }

    private static void jedisPoolConnect() {

        //JedisPoolConfig can be used to configure the Jedis connection pool properties
        //It extends from apache common's GenericObjectPoolConfig
        JedisPoolConfig config = new JedisPoolConfig();
        //We can set max pool size, max idle size.
        config.setMaxTotal(50);
        config.setMaxIdle(15);


        //Create a new Jedis Pool
        //Use Default JedisPoolConfig with the default configuration
        //Set the host, port, connection timeout, password and ssl mode arguments
        JedisPool jedisPool = new JedisPool(
                config, "localhost", 6379, Protocol.DEFAULT_TIMEOUT, null, false);


        // Will print Active = 0, Idle = 0, Waiting = 0
        printConnectionPoolDetails(jedisPool);

        //Get a Jedis connection from the pool
        Jedis jedisConnection = jedisPool.getResource();

        // Will print Active = 1, Idle = 0, Waiting = 0
        printConnectionPoolDetails(jedisPool);


        //Use Jedis.close() to close this connection and return to the Pool
        // Do not use JedisPool.returnResource() directly
        jedisConnection.close();


        // Will print Active = 0, Idle = 1, Waiting = 0
        printConnectionPoolDetails(jedisPool);

        //Now we will fetch connection bulk, to test pool size
        Jedis[] connections = new Jedis[100];

        //Open 25 new Connections
        openConnectionsInCurrentThread(jedisPool, connections, 25);

        // Will print Active = 25, Idle = 0, Waiting = 0
        printConnectionPoolDetails(jedisPool);

        //Now close all the 25 connections
        closeConnectionsInCurrentThread(connections, 25);

        // Will print Active = 0, Idle = 15, Waiting = 0
        // Although 25 connections got released, only 15 are in idle, since the maxIdle was set to 15 earlier.
        printConnectionPoolDetails(jedisPool);


        //Open 40 new Connections
        openConnectionsInCurrentThread(jedisPool, connections, 40);

        // Will print Active = 40, Idle = 0, Waiting = 0
        printConnectionPoolDetails(jedisPool);


        //Connection pool has now only 10 connections left.
        //Lets now create 15 more connections (but in separate thread, so that current thread does not blocks)
        ExecutorService executor = openConnectionsInNewThread(jedisPool, 15);
        executor.shutdown();

        //Let current thread sleep for 5 seconds
        sleep(5);


        // Will print Active = 50, Idle = 0, Waiting = 5
        printConnectionPoolDetails(jedisPool);

        //Close 2 connections
        closeConnectionsInCurrentThread(connections, 2);

        // Will print Active = 48, Idle = 0, Waiting = 3
        printConnectionPoolDetails(jedisPool);

        //Let current thread sleep for 5 seconds
        sleep(5);

        // Will print Active = 48, Idle = 0, Waiting = 3
        printConnectionPoolDetails(jedisPool);

        //Close the Pool
        jedisPool.close();

    }

    private static void sleep(int sleepInSeconds) {
        try {
            Thread.sleep(sleepInSeconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void openConnectionsInCurrentThread(JedisPool jedisPool, Jedis[] connections, int size) {
        for (int i = 0; i < size; i++) {
            connections[i] = jedisPool.getResource();
        }
    }

    private static void closeConnectionsInCurrentThread(Jedis[] connections, int size) {
        for (int i = 0; i < size; i++) {
            connections[i].close();
        }
    }

    private static ExecutorService openConnectionsInNewThread(JedisPool jedisPool, int size) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    jedisPool.getResource();
                } catch (Exception e) {
                    System.out.print("Exception while getting connection = " + e.toString());
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(2*size);

        for (int i = 0; i <size ; i++) {
            executor.execute(r);
        }
        return executor;
    }

    private static void printConnectionPoolDetails(JedisPool jedisPool) {
        System.out.println("Active = " + jedisPool.getNumActive() +
                ", Idle = " +  jedisPool.getNumIdle() + ", Waiting = " +  jedisPool.getNumWaiters());
    }

    private static void jedisStandAloneConnect() {
        //Connect to Jedis
        //Specify the host, port, connection timeout (in milliseconds), ssl
        Jedis jedisClient = new Jedis("localhost", 6379, 12000, false);

        //String operations
        String strKey = "user:email:sizeLimit";

        jedisClient.del(strKey);

        jedisClient.set(strKey, "1024");
        String strValue = jedisClient.get(strKey);
        System.out.println(strKey + " = " + strValue);

        jedisClient.incrBy(strKey, 1024);
        strValue = jedisClient.get(strKey);
        System.out.println(strKey + " = " + strValue);

        jedisClient.expire(strKey, 120);
        System.out.println("TTL for " + strKey + " = " + jedisClient.ttl(strKey));


        //List Operations
        String lKey = "user:skills";
        jedisClient.del(lKey);
        jedisClient.lpush(lKey, "Redis", "Scala", "Java");
        jedisClient.rpush(lKey, "Mongo", "Elasticsearch", "MySQL", "Jenkins");

        List<String> skills = jedisClient.lrange(lKey, 0, -1);
        System.out.println(lKey + " = " + skills);

        jedisClient.rpop(lKey);

        skills = jedisClient.lrange(lKey, 0, -1);
        System.out.println(lKey + " = " + skills);


        //Hash Operations
        String hKey = "employees";
        byte[] hKeyBytes = serialize(hKey);
        jedisClient.del(hKey);

        Person person1 = new Person("John Doe", 35, new Address("San Francisco", "CA", "US"));
        Person person2 = new Person("Samantha", 21, new Address("Portland", "OR", "US"));
        Person person3 = new Person("Jamie", 18, new Address("Texas", "OH", "US"));

        //We can serialize object to bytes and write it
        jedisClient.hset(hKeyBytes, serialize("1000"), serialize(person1));
        jedisClient.hset(hKeyBytes, serialize("1001"), serialize(person2));

        //Or we can write objects as String
        jedisClient.hset(hKey, "1002", person3.toString());

        //Get bytes back
        Person p1 = (Person)deserialize(jedisClient.hget(hKeyBytes, serialize("1000")));
        System.out.println(p1);

        //Get String back
        String p3 = jedisClient.hget(hKey, "1002");
        System.out.println(p3);



        //Working with byte data instead of Strings with SET/GET Operations
        String strKey2 = "employee:1000";
        byte[] strKey2bytes = serialize(strKey2);
        jedisClient.del(strKey2);

        jedisClient.set(strKey2bytes, serialize(person1));
        Person p4 = (Person)deserialize(jedisClient.get(strKey2bytes));
        System.out.println("Getting bytes data back from GET operation = " + p4);

        //Close the jedis Client
        jedisClient.close();
    }

    private static byte[] serialize(Serializable obj) {
        return SerializationUtils.serialize(obj);
    }

    private static Object deserialize(byte[] bytes) {
        return SerializationUtils.deserialize(bytes);
    }


}


