package com.fishlog.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fishlog.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
}