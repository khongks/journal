package com.apress.spring.domain;
import lombok.Data;

@Data
public class JournalTO {
  private Long id;
  private String title;
  private String created;
  private String summary;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }
  
  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * @return the summary
   */
  public String getSummary() {
    return summary;
  }

  /**
   * @param summary the summary to set
   */
  public void setSummary(String summary) {
    this.summary = summary;
  }

  /**
   * @param created the created to set
   */
  public void setCreated(String created) {
    this.created = created;
  }

  /**
   * @return the created
   */
  public String getCreated() {
    return created;
  }
}
