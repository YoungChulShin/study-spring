# 저장소 설명
단일 모듈에서 계층형 아키텍처 또는 헥사고날 아키텍처를 구현할 때, 각 계층간에 참조를 테스트 코드를 이용해서 제약하는 방법을 알아본다

# archunit 라이브러리를 이용한 테스트
## archunit 라이브러리
사이트: https://www.archunit.org/

가능: 자바 코드의 아키텍처를 체크해주는 라이브러리

## 사용방법
예: Domain 레이어의 클래스는 Application 레이어의 클래스에 의존하면 안된다는 것을 테스트코드에서 제약한다

샘플 코드
```java
@Test
  void domainLayerDoesNotDependOnApplicationLayer() {
    JavaClasses importedClasses =
        new ClassFileImporter().importPackages("study.spring.depdencyrule.layerdependencycheck.student");

    // 클래스가 없어야한다
    noClasses()
        .that()
        // domain 패키지의 클래스가
        .resideInAPackage("study.spring.depdencyrule.layerdependencycheck.student.domain..")
        .should()
        // application 클래스에 의존을 가지는 클래스
        .dependOnClassesThat()
        .resideInAnyPackage("study.spring.depdencyrule.layerdependencycheck.student.application..")
        .check(importedClasses);
  }
```