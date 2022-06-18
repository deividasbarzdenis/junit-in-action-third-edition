package org.example.extensions;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.IOException;
import java.util.Properties;

/*
creates a conditional test execution extension by implementing the
ExecutionCondition interface
*/
public class ExecutionContextExtension implements ExecutionCondition {


    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext extensionContext) {
        Properties properties = new Properties();
        String executionContext = "";
        try {
            properties.load(ExecutionContextExtension.class
                    .getClassLoader()
                    .getResourceAsStream("context.properties"));
            executionContext = properties.getProperty("context");

            if (!"regular".equalsIgnoreCase(executionContext) &&
            !"low".equalsIgnoreCase(executionContext)) {
                return ConditionEvaluationResult.disabled("Tests disabled outside regular and low contexts");
            }
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
        return ConditionEvaluationResult.enabled("Test enabled on the " + extensionContext + " context");
    }
}
