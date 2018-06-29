/**
 * this class implements default interface and my custom interface.
 */

package com.example.myapp;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String>, AccountCustomRepository {

    public Account findByLogin(String login);

    public boolean existsByLoginAndPassword(String login, String password);

    public boolean existsByLogin(String login);
}
