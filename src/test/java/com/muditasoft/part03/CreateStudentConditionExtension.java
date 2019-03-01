package com.muditasoft.part03;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.List;

public class CreateStudentConditionExtension implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        if (List.of("student").containsAll(context.getTags()) || List.of("student", "createStudent").containsAll(context.getTags())) {
            return ConditionEvaluationResult.enabled("Create Student is enabled");
        }

        return ConditionEvaluationResult.disabled("Only create Student allowed to run");
    }

}
