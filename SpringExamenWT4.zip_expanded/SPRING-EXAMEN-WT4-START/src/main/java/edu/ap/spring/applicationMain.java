package edu.ap.spring;




import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import edu.ap.spring.applicationMain;
import edu.ap.spring.controller.RedisController;
import edu.ap.spring.redis.RedisService;

@SpringBootApplication
public class  applicationMain {

		
		private RedisService service;
		private String CHANNEL = "edu:ap:redis";
		private String KEY = "edu:ap:test";
		
		@Autowired
		public void setRedisService(RedisService service) {
			this.service = service;
		}
		
		@Bean
		RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
												MessageListenerAdapter listenerAdapter) {

			RedisMessageListenerContainer container = new RedisMessageListenerContainer();
			container.setConnectionFactory(connectionFactory);
			container.addMessageListener(listenerAdapter, new ChannelTopic(CHANNEL));

			return container;
		}

		@Bean
		MessageListenerAdapter listenerAdapter(RedisController controller) {
			return new MessageListenerAdapter(controller, "onMessage");
		}
		

		
		public static void main(String[] args) {
			SpringApplication.run(applicationMain.class, args);
		}
	}

