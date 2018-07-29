package com.apress.spring.web;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.apress.spring.domain.Journal;
import com.apress.spring.domain.JournalTO;
import com.apress.spring.repository.JournalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/journals")
public class JournalController {

        @Autowired
        JournalRepository repo;

        @RequestMapping(method=RequestMethod.GET, 
                        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
        public ResponseEntity<List<JournalTO>> list() {
                List<Journal> list = repo.findAll();
                List<JournalTO> listTO = new ArrayList<JournalTO>();
                for(Journal journal : list) {
                        JournalTO journalTO = journal.toJournalTO();
                        listTO.add(journalTO);
                }
                return new ResponseEntity<List<JournalTO>>(listTO, HttpStatus.OK);
        }

        @RequestMapping(method=RequestMethod.GET, value="/{id}",
                        produces = {MediaType.APPLICATION_JSON_UTF8_VALUE })
        public ResponseEntity<?> getById(@PathVariable("id") Long id) {
                Optional<Journal> found = repo.findById(id);
                if(found.isPresent()) {
                        JournalTO journalTO = found.get().toJournalTO();
                        return new ResponseEntity<>(journalTO, HttpStatus.OK);
                } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @RequestMapping(method=RequestMethod.POST)
        public ResponseEntity<?> create(@RequestBody JournalTO journalTO) {
                try {
                        Journal journal = new Journal(journalTO.getTitle(), journalTO.getSummary(), journalTO.getCreated());
                        repo.save(journal);
                        return new ResponseEntity<>(HttpStatus.CREATED);
                } catch(ParseException pe) {
                        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
        }

        @RequestMapping(method=RequestMethod.PUT, value="/{id}")
        public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody JournalTO journalTO) {
                Optional<Journal> found = repo.findById(id);
                if(found.isPresent()) {
                        try {
                                Journal journal = found.get();
                                journal.setTitle(journalTO.getTitle());
                                journal.setSummary(journalTO.getSummary());
                                journal.setCreated(journalTO.getCreated());
                                repo.save(journal);
                                return new ResponseEntity<>(HttpStatus.OK);
                        } catch(ParseException pe) {
                                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                        }
                } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @RequestMapping(method=RequestMethod.DELETE, value="/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id) {
                Optional<Journal> found = repo.findById(id);
                if(found.isPresent()) {
                        repo.deleteById(id);
                        return new ResponseEntity<>(HttpStatus.OK);
                } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }
 

        @RequestMapping("/")
        public String index(Model model) {
                model.addAttribute("journal", repo.findAll());
                return "index";
        }
}