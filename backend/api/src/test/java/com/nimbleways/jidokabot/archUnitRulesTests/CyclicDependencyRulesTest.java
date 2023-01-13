package com.nimbleways.jidokabot.archUnitRulesTests;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

import com.tngtech.archunit.core.importer.ImportOption;

@AnalyzeClasses(packages = "com.nimbleways.springboilerplate",importOptions = { ImportOption.DoNotIncludeTests.class })
public class CyclicDependencyRulesTest {
    @ArchTest
    static final ArchRule no_cycles_by_method_calls_between_slices =
    slices().matching("springboilerplate.(*)..").should().beFreeOfCycles(); 
}
