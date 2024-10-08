package com.tejasnirmal.journalApp.repository;

import com.tejasnirmal.journalApp.model.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {

}
