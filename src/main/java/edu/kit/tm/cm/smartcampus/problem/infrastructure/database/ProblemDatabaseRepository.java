package edu.kit.tm.cm.smartcampus.problem.infrastructure.database;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;
import edu.kit.tm.cm.smartcampus.problem.logic.model.ProblemRepository;

import java.util.Collection;
import java.util.Optional;

public class ProblemDatabaseRepository implements ProblemRepository {
    @Override
    public Collection<Problem> findAll() {
        return null;
    }

    @Override
    public Optional<Problem> findById(String id) {
        return Optional.empty();
    }

    @Override
    public String create(Problem problem) {
        return null;
    }

    @Override
    public Optional<Problem> update(Problem problem) {
        return Optional.empty();
    }

    @Override
    public void delete(String pin) {

    }
}
