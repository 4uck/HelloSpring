package com.example.myapp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, Long> {

    public User findByLogin(String login);
}
