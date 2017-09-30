package com.chadbutz.pai.model;

import org.springframework.data.repository.CrudRepository;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

}
