package com.apress.spring.controller;

import java.util.List;

import com.apress.spring.domain.JournalTO;
import com.apress.spring.exception.CreateException;
import com.apress.spring.exception.NotFoundException;
import com.apress.spring.exception.UpdateException;
import com.apress.spring.service.JournalService;

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
  // JournalRepository repo;
  JournalService service;

  @RequestMapping(method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public ResponseEntity<List<JournalTO>> list() {
    List<JournalTO> journals = service.findAll();
    return new ResponseEntity<List<JournalTO>>(journals, HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
  public ResponseEntity<?> getById(@PathVariable("id") Long id) {
    try {
      JournalTO journalTO = service.findById(id);
      return new ResponseEntity<>(journalTO, HttpStatus.OK);
    } catch (NotFoundException nfe) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<?> create(@RequestBody JournalTO journalTO) {
    try {
      service.create(journalTO);
      return new ResponseEntity<>(HttpStatus.CREATED);
    } catch (CreateException ce) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody JournalTO journalTO) {
    try {
      service.update(id, journalTO);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (UpdateException ue) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    } catch (NotFoundException nfe) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    try {
      service.delete(id);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (NotFoundException nfe) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping("/")
  public String index(Model model) {
    model.addAttribute("journal", service.findAll());
    return "index";
  }
}