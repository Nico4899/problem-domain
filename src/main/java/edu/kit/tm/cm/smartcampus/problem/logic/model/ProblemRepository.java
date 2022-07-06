package edu.kit.tm.cm.smartcampus.problem.logic.model;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProblemRepository {

    Collection<Problem> findAll();

    Optional<Problem> findById(String id);

    String create(Problem problem);

    Optional<Problem> update(Problem problem);

    void delete(String pin);

}
