package edu.kit.tm.cm.smartcampus.problem.logic.model;

import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProblemRepository {

    Collection<Problem> findAll();

    Problem findById(String id);

    Problem create(Problem problem);

    Problem update(Problem problem);

    void delete(String pin);

}
