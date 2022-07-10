package edu.kit.tm.cm.smartcampus.problem.infrastructure.database.entities;

import edu.kit.tm.cm.smartcampus.problem.logic.model.ProblemState;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Problem")
public class ProblemPersistenceEntity {

  @Id
  private String id;

  private String refId;

  private String nin;

  private String title;

  private String creationTime;

  private String reporter;

  private ProblemState state;

}
