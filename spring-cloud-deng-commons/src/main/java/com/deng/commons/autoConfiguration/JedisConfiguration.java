package com.deng.commons.autoConfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@Slf4j
public class JedisConfiguration {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.password}")
	private String password;
	@Value("${spring.redis.timeout}")
	private int timeout;
	
	@Value("${spring.redis.pool.maxTotal}")
	private int maxTotal;
	@Value("${spring.redis.jedis.pool.max-idle}")
	private int maxIdle;
	@Value("${spring.redis.pool.maxWaitMillis}")
	private long maxWaitMillis;
	
	@Value("${spring.redis.jedis.pool.min-idle}")
	private int minIdle;
	@Value("${spring.redis.pool.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;
	
	
	@Value("${spring.redis.pool.numTestsPerEvictionRun}")
	private int numTestsPerEvictionRun;
	@Value("${spring.redis.pool.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;
	@Value("${spring.redis.pool.testOnBorrow}")
	private boolean testOnBorrow;
	@Value("${spring.redis.pool.testOnReturn}")
	private boolean testOnReturn;
	@Value("${spring.redis.pool.testWhileIdle}")
	private boolean testWhileIdle;
	
	@Bean
	public JedisPool jedisPool() {
    	JedisPoolConfig config = new JedisPoolConfig();
    	config.setMaxIdle(maxIdle);
    	config.setMaxWaitMillis(maxWaitMillis);
    	config.setMaxTotal(maxTotal);
    	
    	config.setMinIdle(minIdle);
    	config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
    	
    	config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
    	config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
    	config.setTestOnBorrow(testOnBorrow);
    	config.setTestOnReturn(testOnReturn);
    	config.setTestWhileIdle(testWhileIdle);
    	JedisPool jedisPool = new JedisPool(config, host, port, timeout, null);
    	log.info("JedisPool生成成功，Redis地址：" + host + ":" + port);
    	return jedisPool;
	}
}
