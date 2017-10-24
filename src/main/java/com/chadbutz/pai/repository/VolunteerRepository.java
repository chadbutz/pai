package com.chadbutz.pai.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.chadbutz.pai.model.Volunteer;


// This will be AUTO IMPLEMENTED by Spring into a Bean called conversationRepository
// CRUD refers Create, Read, Update, Delete
@RepositoryRestResource(collectionResourceRel = "volunteers", path = "volunteers")
public interface VolunteerRepository extends PagingAndSortingRepository<Volunteer, Long> {

}
