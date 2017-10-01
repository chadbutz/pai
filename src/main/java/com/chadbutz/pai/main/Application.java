package com.chadbutz.pai.main;

import com.chadbutz.pai.model.Conversation;
import com.chadbutz.pai.model.ConversationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
	@Bean
	CommandLineRunner init(ConversationRepository conversationRepository) {
		return (args) ->
				Arrays.asList("jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
						.forEach(a -> {
							Conversation conversation = conversationRepository.save(new Conversation(a, "email"));
						});
	}

}