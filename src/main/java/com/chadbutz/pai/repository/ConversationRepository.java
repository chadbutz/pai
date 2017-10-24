package com.chadbutz.pai.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chadbutz.pai.model.Conversation;


// This will be AUTO IMPLEMENTED by Spring into a Bean called conversationRepository
// CRUD refers Create, Read, Update, Delete
@Repository
public interface ConversationRepository extends CrudRepository<Conversation, Long> {

}
