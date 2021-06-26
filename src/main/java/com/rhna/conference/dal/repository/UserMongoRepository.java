//Name : Malwatta H.G.
//ID : IT19240848
//Group : REG_WE_03

package com.rhna.conference.dal.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.rhna.conference.dal.model.User;


//create new user repository to find data in the database
public interface UserMongoRepository extends MongoRepository<User, String> {
	
	Boolean existsByUsername(String username);

	Optional<User> findByUsername(String username);

	Boolean existsByEmail(String email);
	
}
