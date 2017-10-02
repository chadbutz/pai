package com.chadbutz.pai.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.chadbutz.pai.model.Conversation;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EntityScan(basePackageClasses=Conversation.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
//	@Bean
//	CommandLineRunner init(ConversationRepository conversationRepository) {
//		return (args) ->
//				Arrays.asList("jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
//						.forEach(a -> {
//							Conversation conversation = conversationRepository.save(new Conversation(a, "email"));
//						});
//	}

}