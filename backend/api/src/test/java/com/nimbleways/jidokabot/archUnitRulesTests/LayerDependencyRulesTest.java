package com.nimbleways.jidokabot.archUnitRulesTests;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packages = "com.nimbleways.springboilerplate")
public class LayerDependencyRulesTest {

        // 'dependOn' catches a wider variety of violations, e.g. having fields of type,
        // having method parameters of type, extending type ...

        @ArchTest
        static final ArchRule services_should_not_depend_on_controllers = noClasses().that()
                        .resideInAPackage("..services..")
                        .should().dependOnClassesThat().resideInAPackage("..controllers..");

        @ArchTest
        static final ArchRule persistence_should_not_depend_on_services = noClasses().that()
                        .resideInAPackage("..repositories..")
                        .should().dependOnClassesThat().resideInAPackage("..services..");

       

}