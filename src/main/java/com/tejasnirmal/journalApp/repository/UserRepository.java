package com.tejasnirmal.journalApp.repository;

import com.tejasnirmal.journalApp.model.JournalEntry;
import com.tejasnirmal.journalApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserName(String userName);

    void deleteByUserName(String username);

}
