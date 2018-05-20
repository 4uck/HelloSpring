package com.example.myapp;

import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {

    public User findByLogin(String login);

    public User findByLoginAndPassword(String login, String password);

    public boolean existsByLoginAndPassword(String login, String password);

//    public boolean existsUser(User user);
}
