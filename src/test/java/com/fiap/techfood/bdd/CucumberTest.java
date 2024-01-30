package com.fiap.techfood.bdd;

import org.junit.Ignore;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@Ignore("Class not ready for tests")
public class CucumberTest {
}
