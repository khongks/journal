package com.apress.spring.repository;

import java.util.Date;
import java.util.List;

import com.apress.spring.domain.Journal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JournalRepository extends JpaRepository<Journal, Long> {  
  
  public List<Journal> findByTitleContaining(String word);
  public List<Journal> findByCreatedAfter(Date date);

  @Query("select j from Journal j where j.title like %?1%")
  public List<Journal> findByCustomQuery(String word);
}