package study.spring.depdencyrule.layerdependencycheck;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.api.Test;

public class DependencyRuleTests {

  @Test
  void domainLayerDoesNotDependOnApplicationLayer() {
    JavaClasses importedClasses =
        new ClassFileImporter().importPackages("study.spring.depdencyrule.layerdependencycheck.student");

    noClasses()
        .that()
        .resideInAPackage("study.spring.depdencyrule.layerdependencycheck.student.domain..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("study.spring.depdencyrule.layerdependencycheck.student.application..")
        .check(importedClasses);
  }

  @Test
  void applicationLayerDoesNotDependOnAdapterLayer() {
    JavaClasses importedClasses =
        new ClassFileImporter().importPackages("study.spring.depdencyrule.layerdependencycheck.student");

    noClasses()
        .that()
        .resideInAPackage("study.spring.depdencyrule.layerdependencycheck.student.application..")
        .should()
        .dependOnClassesThat()
        .resideInAnyPackage("study.spring.depdencyrule.layerdependencycheck.student.adapter..")
        .check(importedClasses);
  }
}
