package com.apress.spring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;

@Entity
@Data
public class Journal {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String title;
    private Date created;
    private String summary;

    @Transient
    private SimpleDateFormat simpleDataFormat = new SimpleDateFormat("MM/dd/yyyy");

    public Journal(String title, String summary, String date) throws ParseException {
        this.title = title;
        this.summary = summary;
        this.created = simpleDataFormat.parse(date);
    }

    public Journal() {}

    public Long getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return this.summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreated() {
        return this.created;
    }

    /**
     * @param created the created to set
     */
    public void setCreated(String created) throws ParseException {
        this.created = simpleDataFormat.parse(created);
    }

    public JournalTO toJournalTO() {
        JournalTO journalTO = new JournalTO();
        journalTO.setId(id);
        journalTO.setTitle(title);
        journalTO.setSummary(summary);
        journalTO.setCreated(simpleDataFormat.format(created));
        return journalTO;
    }

    public String toString() {
        StringBuilder value = new StringBuilder("JournalEntry(");
        value.append("Id: ");
        value.append(id);
        value.append(",Title: ");
        value.append(title);
        value.append(",Summary: ");
        value.append(summary);
        value.append(",Created: ");
        value.append(getCreatedAsShort());
        value.append(")");
        return value.toString();
    }

    public String getCreatedAsShort(){
        return simpleDataFormat.format(created);
    }
}