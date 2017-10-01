package com.chadbutz.pai.main;

import com.chadbutz.pai.model.Conversation;
import com.chadbutz.pai.model.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController    // This means that this class is a Controller
@RequestMapping(path="/api") // This means URL's start with /demo (after Application path)
public class MainController {
	// This means to get the bean called userRepository
	// Which is auto-generated by Spring, we will use it to handle the data
	private ConversationRepository conversationRepository;

	@Inject
	MainController(ConversationRepository conversationRepository) {
		this.conversationRepository = conversationRepository;
	}

	@RequestMapping(method = RequestMethod.GET,path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewConversation (@RequestParam String name
			, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Conversation n = new Conversation(name, email);

		conversationRepository.save(n);
		return "Saved";
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/all")
	public @ResponseBody Iterable<Conversation> getAllConversations() {
		// This returns a JSON or XML with the users
		return conversationRepository.findAll();
	}

    @Autowired
    @Qualifier("conversationRepository")
	public void setConversationRepository(ConversationRepository conversationRepository) {
	    this.conversationRepository = conversationRepository;
    }
}
