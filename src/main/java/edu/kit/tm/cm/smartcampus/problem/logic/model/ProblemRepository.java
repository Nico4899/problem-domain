package edu.kit.tm.cm.smartcampus.problem.logic.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProblemRepository extends CrudRepository<Problem, String> {
}
