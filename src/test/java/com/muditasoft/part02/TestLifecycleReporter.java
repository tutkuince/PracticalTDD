package com.muditasoft.part02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

public interface TestLifecycleReporter {

    @BeforeEach
    default void reportStart(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry("Start", testInfo.getTestMethod().get().getName());
    }

    @BeforeEach
    default void reportEnd(TestInfo testInfo, TestReporter testReporter) {
        testReporter.publishEntry("End", testInfo.getTestMethod().get().getName());
    }

}