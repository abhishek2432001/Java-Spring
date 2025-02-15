package com.edigest.journalApp.controller;


import com.edigest.journalApp.entity.JournalEntry;
import com.edigest.journalApp.entity.User;
import com.edigest.journalApp.service.JournalEntryService;
import com.edigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntityControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{username}/")
    public ResponseEntity<?> getAllJournalEntryOfUser(@PathVariable String userName){
        User author = userService.findByUserName(userName);
        List<JournalEntry> all = author.getJournalEntries();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<JournalEntry>> getALl(){
        List<JournalEntry> all = journalEntryService.finalAllJournalEntry();
        if(all != null && ! all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry journalEntry, @PathVariable("username") String name){
        try{

            journalEntryService.saveJournalEntry(journalEntry, name);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryBYId(@PathVariable ObjectId myId){
         Optional<JournalEntry> journalEntry =  journalEntryService.findJournalEntryById(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId, @PathVariable String userName){

        journalEntryService.deleteJournalById(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(
            @PathVariable ObjectId id,
            @PathVariable String userName,
            @RequestBody JournalEntry journalEntry){
        JournalEntry oldJournalEntry =  journalEntryService.findJournalEntryById(id).orElse(null);

        if(oldJournalEntry != null){
            oldJournalEntry.setTitle(journalEntry.getTitle().equals("") ? oldJournalEntry.getTitle() : journalEntry.getTitle());
            oldJournalEntry.setContent(journalEntry.getContent() != null && journalEntry.getContent().equals("") ? oldJournalEntry.getContent() : journalEntry.getContent());
            journalEntryService.saveUpdateJournalEntry(oldJournalEntry);
            return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
