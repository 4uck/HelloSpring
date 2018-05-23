package com.example.myapp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String>, MyCustomRepository {

    public Account findByLogin(String login);

    public boolean existsByLoginAndPassword(String login, String password);


}
