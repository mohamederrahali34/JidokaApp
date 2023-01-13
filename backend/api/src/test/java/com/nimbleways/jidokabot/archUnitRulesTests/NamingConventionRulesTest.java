package com.nimbleways.jidokabot.archUnitRulesTests;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

import com.tngtech.archunit.core.importer.ImportOption;


@AnalyzeClasses(packages = "com.nimbleways.springboilerplate",importOptions = { ImportOption.DoNotIncludeTests.class }) //how to make prevent archunit from using test folder
public class NamingConventionRulesTest {
        @ArchTest
        static ArchRule services_should_be_suffixed = classes()
                        .that().resideInAPackage("..services..")
                        .should().haveSimpleNameContaining("Service");

        @ArchTest
        static ArchRule controllers_should_be_suffixed = classes()
                        .that().resideInAPackage("..controllers..")
                        .should().haveSimpleNameContaining("Controller");

       

}
