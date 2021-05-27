package com.rhna.conference.dal.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;


import com.rhna.conference.dal.model.ERole;
import com.rhna.conference.dal.model.Role;


//created new RoleRepository to find out user role in the database
public interface RoleMongoRepository extends MongoRepository<Role, String> {
	
	Optional<Role> findByName(ERole name);
	
}
