package com.apress.spring.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.apress.spring.domain.Journal;
import com.apress.spring.domain.JournalTO;
import com.apress.spring.exception.CreateException;
import com.apress.spring.exception.NotFoundException;
import com.apress.spring.exception.UpdateException;
import com.apress.spring.repository.JournalRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JournalService {
  private static final Logger log = LoggerFactory.getLogger(JournalService.class);

  @Autowired
  JournalRepository repo;

  public void insertData() throws ParseException {
    log.info("> Inserting data ...");
    repo.save(new Journal("Get to know Spring Boot", "Today I will learn Spring Boot", "01/01/2016"));
    repo.save(new Journal("Simple Spring Boot Project", "I will do my first Spring Boot Project", "01/02/2016"));
    repo.save(new Journal("Spring Boot Reading", "Read more about Spring Boot", "02/01/2016"));
    repo.save(new Journal("Spring Boot in the Cloud", "Spring Boot using Cloud Foundry", "03/01/2016"));
    log.info("> Done.");
  }

  public List<JournalTO> findAll() {
    List<Journal> list = repo.findAll();
    List<JournalTO> listTO = new ArrayList<JournalTO>();
    for(Journal journal : list) {
      JournalTO journalTO = journal.toJournalTO();
      listTO.add(journalTO);
    }
    return listTO;
  }

  public JournalTO findById(Long id) throws NotFoundException {
    Optional<Journal> found = repo.findById(id);
    if(found.isPresent()) {
            JournalTO journalTO = found.get().toJournalTO();
            return journalTO;
    } else {
      throw new NotFoundException("Journal with " + id + " not found");
    }
  }

  public void create(JournalTO journalTO) throws CreateException {
    try {
      Journal journal = new Journal(journalTO.getTitle(), journalTO.getSummary(), journalTO.getCreated());
      repo.save(journal);
    } catch(ParseException pe) {
      throw new CreateException(pe.toString());
    }
  }

  public void update(Long id, JournalTO journalTO) throws UpdateException, NotFoundException {
    Optional<Journal> found = repo.findById(id);
    if (found.isPresent()) {
      try {
        Journal journal = found.get();
        journal.setTitle(journalTO.getTitle());
        journal.setSummary(journalTO.getSummary());
        journal.setCreated(journalTO.getCreated());
        repo.save(journal);
      } catch (ParseException pe) {
        throw new UpdateException(pe.toString());
      }
    } else {
      throw new NotFoundException("Journal with " + id + " not found");
    }
  }

  public void delete(Long id) throws NotFoundException {
    Optional<Journal> found = repo.findById(id);
    if(found.isPresent()) {
            repo.deleteById(id);
    } else {
      throw new NotFoundException("Journal with " + id + " not found");
    }
  }

}