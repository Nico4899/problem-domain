package edu.kit.tm.cm.smartcampus.problem.api.payload;

import edu.kit.tm.cm.smartcampus.problem.logic.model.Problem;

import java.util.Collection;

public record ProblemsResponse(Collection<Problem> problems) {
};
