package com.example.myapp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String>, MyCustomRepository {

    public User findByLogin(String login);

    public boolean existsByLoginAndPassword(String login, String password);
}
