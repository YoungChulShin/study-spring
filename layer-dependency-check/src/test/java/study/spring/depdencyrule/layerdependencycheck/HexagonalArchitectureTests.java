//package study.spring.depdencyrule.layerdependencycheck;
//
//import com.tngtech.archunit.core.domain.JavaClasses;
//import com.tngtech.archunit.core.importer.ClassFileImporter;
//import org.junit.jupiter.api.Test;
//import study.spring.depdencyrule.layerdependencycheck.archunit.HexagonalArchitecture;
//
///**
// * @see https://github.com/wikibook/clean-architecture/blob/main/src/main/java/io/reflectoring/buckpal/BuckPalApplication.java
// */
//public class HexagonalArchitectureTests {
//
//  @Test
//  void validateRegistrationContextArchitecture() {
//    JavaClasses importedClasses =
//        new ClassFileImporter().importPackages("study.spring.depdencyrule.layerdependencycheck");
//
//    HexagonalArchitecture.boundedContext("student")
//        .withDomainLayer("domain")
//        .withAdaptersLayer("adapter")
//        .outgoing("in.web")
//          .and()
////        .withApplicationLayer("application")
////          .services("service")
////          .incomingPorts("port.in")
////          .outgoingPorts("port.out")
////          .and()
//        .check(importedClasses);
//  }
//
//}
