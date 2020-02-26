package com.siemens.dl.goods;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.siemens.dl.goods");

        noClasses()
            .that()
                .resideInAnyPackage("com.siemens.dl.goods.service..")
            .or()
                .resideInAnyPackage("com.siemens.dl.goods.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.siemens.dl.goods.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
