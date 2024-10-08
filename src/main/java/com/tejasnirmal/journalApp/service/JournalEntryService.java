package com.tejasnirmal.journalApp.service;

import com.tejasnirmal.journalApp.model.JournalEntry;
import com.tejasnirmal.journalApp.model.User;
import com.tejasnirmal.journalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@Component
@Slf4j
public class JournalEntryService
{

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

//    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName)
    {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }
        catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An Error Occur while saving the entry.", e);
        }
    }

    public void saveEntry(JournalEntry journalEntry)
    {
            journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id)
    {
        return journalEntryRepository.findById(String.valueOf(id));
    }

//    @Transactional
    public boolean deleteById(ObjectId id, String userName)
    {
        boolean removed = false;
        try
        {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(String.valueOf(id));
            }

        } catch (Exception e)
        {
            System.out.println(e);
            throw new RuntimeException("An error occur while deleting the entry", e);
        }
        return removed;

    }


}
