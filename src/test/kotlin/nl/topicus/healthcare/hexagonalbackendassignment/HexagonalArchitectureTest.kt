package nl.topicus.healthcare.hexagonalbackendassignment

import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses

@AnalyzeClasses(packages = ["nl.topicus.healthcare"], importOptions = [ImportOption.DoNotIncludeTests::class])
class HexagonalArchitectureTest {

    private val domainPackage = "..topicus.healthcare.hexagonalbackendassignment.domain.."
    private val allowedUtilPackages = arrayOf("java..", "kotlin..", "org.jetbrains..")

    @ArchTest
    internal fun `Code in the domain package should not depend on code outside the domain package`(importedClasses: JavaClasses) {
        val rule = noClasses().that().resideInAPackage(domainPackage)
            .should().dependOnClassesThat().resideOutsideOfPackages(*allowedUtilPackages, domainPackage)

        rule.check(importedClasses)
    }
}