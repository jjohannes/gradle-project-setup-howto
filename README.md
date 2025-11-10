# How to set up a larger Gradle project

This repo contains a Gradle project structure with:

- **Centralized and maintainable** build configuration and custom build logic
- **No dependency hell** through smart dependency management with dependency rules and analysis

The `main` branch contains everything for a traditional Java project.
The structure though, is good for any kind of project you may build with Gradle
(**Kotlin**, **Groovy**, **Scala**, ...).

> [!NOTE]
> There are adjustments on other branches of this repo that show how the setup can be varied:
> - 🧩 [**Java Module System**](https://github.com/jjohannes/gradle-project-setup-howto/tree/java_module_system)
> - 📦 [**Versions in BOM/Platform**](https://github.com/jjohannes/gradle-project-setup-howto/tree/java_bom)
> - 🦩 [**Kotlin**](https://github.com/jjohannes/gradle-project-setup-howto/tree/kotlin)
> - 🤖 [**Android**](https://github.com/jjohannes/gradle-project-setup-howto/tree/android)
> - 🍃 [**Java** and **Spring Boot**](https://github.com/jjohannes/gradle-project-setup-howto/tree/spring_boot)

> [!TIP]
> There are two sister repositories that share the same example with a different focus in the build setup
> - 🧶 [**idiomatic-gradle**](https://github.com/jjohannes/idiomatic-gradle) Focus on structuring builds for both mono-repo or multi-repo setups
> - 🕹️ [**javarcade**](https://github.com/jjohannes/javarcade) Focus on structured dependency management with the JavaRCA recipe

## Project Overview

### Folder structure

```
├── settings.gradle.kts     - Entry point file for Gradle to work with the project struture
├── $product-name           - Modules of the software are organized in 'product' folders 
│   └── $module-name          - Each Module of the software has a folder
│       ├── src               - Production code and test code
│       └── build.gradle.kts  - Defines the type of the Module and its dependencies
├── gradle                  - Contains the build configuraton
│   ├── version.txt           - Defines the version of the software all Modules share
│   ├── jdk-version.txt       - Defines Java version used in the project 
│   ├── libs.versions.toml    - Version catalog defines versions of 3rd party modules
│   ├── versions/build...     - 3rd party version restrictions if needed
│   ├── aggregation/build...  - Aggregated reports for the whole project
│   ├── plugins/build...      - Define which 3rd party plugins are used and their versions
│   │   └── src/main/kotlin   - Individual plugin configurations (aka Convention Plugins)
│   └── wrapper               - Define Gradle version used via Gradle Wrapper
├── gradle.properties       - Gradle startup parameters and global switches
├── build.gradle.kts        - Use plugins for checks and auto-formating on the whole project
└── .github                 - Integrate with GitHub Actions CI system
```

- [Video: The Settings File](https://www.youtube.com/watch?v=Ajs8pTbg8as&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)

### The $product-name/$module-name/build.gradle.kts files

Select a _Component Type_ by using the corresponding convention plugin and define the dependencies of the module.
For example:

```
plugins {
    id("org.example.gradle.component.library") // This is a 'library'
    id("org.example.gradle.feature.publish")   // Build feature only in this 'library' Module
}

dependencies {
    api(projects.coruscant)     // Depends on another Module of our project
    implementation(libs.guava)  // Depends on a 3rd party Module
}
```

- [Video: The Build Files](https://www.youtube.com/watch?v=OKjE_Lt_66U&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [Video: Declaring Dependencies](https://www.youtube.com/watch?v=igug9tbl4J4&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)

### The Convention Plugins

- [Video: (Convention) Plugins](https://www.youtube.com/watch?v=N95YI-szd78&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)

Convention plugins are used to configure each aspect of the build centrally. To keep it structured, we put
them into four categories: _Base_, _Feature_, _Check_, _Report_. Below you find all plugins listed. For more details,
inspect the corresponding plugin files. _Understanding Gradle_ videos that cover related topics are linked below
each plugin file.

#### Base Plugins

_Base_ plugins need to be used in all Modules to establish a certain foundation for the setup.
For example, the same dependency management configuration should be applied everywhere to consistently use the same
3rd party libraries everywhere.

- [org.example.gradle.base.identity.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.base.identity.gradle.kts)
- [org.example.gradle.base.lifecycle.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.base.lifecycle.gradle.kts)
  - [Video: Lifecycle Tasks](https://www.youtube.com/watch?v=sOo0p4Gpjcc&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.base.lifecycle.root.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.base.lifecycle.root.gradle.kts)
- [org.example.gradle.base.dependency-rules.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.base.dependency-rules.gradle.kts)
  - [Video: Dependency Version Conflicts + Consistent Resolution](https://www.youtube.com/watch?v=YYWhfy6c2YQ&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
  - [Video: Capability Conflicts + Component Metadata Rules](https://www.youtube.com/watch?v=5g20kbbqBFk&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)

#### Feature Plugins

Each _feature_ plugin configures one aspect of building the software – like _compiling code_ or _testing code_.

- [org.example.gradle.feature.repositories.settings.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.repositories.settings.gradle.kts)
- [org.example.gradle.feature.project-structure.settings.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.project-structure.settings.gradle.kts)
  - [Video: Settings Plugins](https://www.youtube.com/watch?v=tlx3tzuLSWk&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.feature.compile-java.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.compile-java.gradle.kts)
  - [Video: Source Sets](https://www.youtube.com/watch?v=74PDtHkS_w4&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
  - [Video: The JavaCompile Task](https://www.youtube.com/watch?v=wFewehz6rW8&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.feature.javadoc.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.javadoc.gradle.kts)
- [org.example.gradle.feature.test.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.test.gradle.kts)
  - [Video: Configuring Testing](https://www.youtube.com/watch?v=7f_gBvGQN_0&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
  - [Video: The Test Task](https://www.youtube.com/watch?v=YJjNQJSaFww&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.feature.test-end-to-end.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.test-end-to-end.gradle.kts)
  - [Video: Feature Variants](https://www.youtube.com/watch?v=XCzyUESaBHQ&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.feature.test-fixtures.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.test-fixtures.gradle.kts)
  - [Video: Test Fixtures](https://www.youtube.com/watch?v=fSRN6YKa5B0&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.feature.checksum.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.checksum.gradle.kts)
  - [Video: Configuring Task Inputs and Outputs](https://www.youtube.com/watch?v=Pj9hSRauiQM&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
  - [Video: Implementing Tasks and Extensions](https://www.youtube.com/watch?v=wrgyUKC7vOY&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.feature.publish.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.publish.gradle.kts)
  - [Video: Publishing Libraries](https://www.youtube.com/watch?v=8z5KFCLZDd0&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.feature.use-all-catalog-versions.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.use-all-catalog-versions.gradle.kts)
  - [Video: Centralizing Dependency Versions](https://www.youtube.com/watch?v=8044F5gc1dE&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.feature.build-cache.settings.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.feature.build-cache.settings.gradle.kts)
  - [Video: Caching](https://www.youtube.com/watch?v=nHb0kIcTrFE&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)

#### Report Plugins

_Report_ plugins add reporting functionality to discover potential issues with the software or the build setup.
They may generate data that is picked up and displayed by external tools like
[Develocity](https://scans.gradle.com/) or [Dependency Track](https://dependencytrack.org/).
More reporting tools may be integrated in this category.
Report plugins are not necessarily needed to build a working software.

- [org.example.gradle.report.code-coverage.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.report.code-coverage.gradle.kts)
  - [Video: Aggregating Custom Artifacts](https://www.youtube.com/watch?v=2gPJD0mAres&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
  - [Video: Test and Code Coverage Reporting](https://www.youtube.com/watch?v=uZvzWlP9BYE&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.report.plugin-analysis.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.report.plugin-analysis.gradle.kts)
- [org.example.gradle.report.sbom.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.report.sbom.gradle.kts)
- [org.example.gradle.report.develocity.settings.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.report.develocity.settings.gradle.kts)

#### Check Plugins

Check plugins help with keeping the software maintainable over time.
They check things like the dependency setup or code formatting.
More style checkers or static code analysis tools could be added in this category.
Check plugins are not necessarily needed to build a working software.

- [org.example.gradle.check.dependencies.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.check.dependencies.gradle.kts)
  - [Video: Dependency Analysis](https://www.youtube.com/watch?v=Lipf5piizZc&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
  - [Video: Detect and Resolve Collisions on a Classpath](https://www.youtube.com/watch?v=KocTqF0hO_8&list=PLWQK2ZdV4Yl2k2OmC_gsjDpdIBTN0qqkE)
- [org.example.gradle.check.format-gradle.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.check.format-gradle.gradle.kts)
- [org.example.gradle.check.format-gradle.root.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.check.format-gradle.root.gradle.kts)
- [org.example.gradle.check.format-java.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.check.format-java.gradle.kts)

#### Component Plugins

_Component_ plugins combine plugins from all categories above to define _Component Types_ that are then used in the `build.gradle.kts`
files of the individual Modules of our software.
 
- [org.example.gradle.component.application.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.component.application.gradle.kts)
- [org.example.gradle.component.library.gradle.kts](gradle/plugins/src/main/kotlin/org.example.gradle.component.library.gradle.kts)

#### Testing Plugins

The [Gradle TestKit](https://docs.gradle.org/current/userguide/test_kit.html) can be used to test plugins.
This can be helpful to enforce a certain structure, e.g. by testing if each plugin works on its own.
And if you add custom tasks and advanced logic, you can add tests for that. As example, this project contains one test class:
[ConventionPluginTest.kt](gradle/plugins/src/test/kotlin/org/example/gradle/test/ConventionPluginTest.kt)

There is also a help task that you can use to get a diagram of the convention plugins defined in the project:

`./gradlew :aggregation:analysePluginApplicationOrder`

The task generates a [PlantUML](https://plantuml.com) file that you can render, for example, with the PlantUML IntelliJ plugin.

### Continuously build and report using GitHub Actions and Dependabot

- [build.yaml](.github/workflows/build.yaml) Configure GitHub to run builds and produce reports (👉[inspect](https://github.com/jjohannes/gradle-project-setup-howto/actions/workflows/build.yaml)). Integrates with:
  - [Develocity Build Scans](https://scans.gradle.com/) (👉[inspect](https://scans.gradle.com/s/h3odwhbjjd2qm))
  - [Gradle Remote Build Cache](https://docs.gradle.com/develocity/build-cache-node/)
  - [Reposilite](https://reposilite.com/) (👉[inspect](https://repo.onepiece.software/#/snapshots))
  - [Dependency Track](https://dependencytrack.org/)
- [renovate.json](renovate.json) Configure [Renovate](https://github.com/apps/renovate) to automatically get version updates (👉[inspect](https://github.com/jjohannes/gradle-project-setup-howto/pulls/app%2Frenovate))

## Notes

- If you have a question, please ask in an [issue](https://github.com/jjohannes/gradle-project-setup-howto/issues/new).
- The concrete things done in all places (custom tasks, components used in dependencies, additional plugins applied, etc.) are just examples.
  If you, for example, need to use additional Gradle plugins you can add these in the corresponding place, keeping the suggested structure.
- There was a different version of this repository I initially published in 2022. The setup was more complex by splitting
  the Gradle configuration over more folders which required more boilerplate. After using a setup like this in several
  projects, I felt that the setup was overly complex without adding much value. I ended up striping it down to what this
  repository is now. The older version is still accessible on the
  [2022_java](https://github.com/jjohannes/gradle-project-setup-howto/tree/2022_java) branch.

## FAQ

[List of questions](https://github.com/jjohannes/gradle-project-setup-howto/issues?q=is%3Aissue+label%3Aquestion) asked in issues so far.

- [Why is the :app project special?](https://github.com/jjohannes/gradle-project-setup-howto/issues/4)
- [Dependency Analysis: How to ignore dependencies added by plugins?](https://github.com/jjohannes/gradle-project-setup-howto/issues/6)
- [Dependency Analysis: How to remove dependencies added by plugins?](https://github.com/jjohannes/gradle-project-setup-howto/issues/15)
- [How many convention plugins should be used?](https://github.com/jjohannes/gradle-project-setup-howto/issues/10)
- [How to customize the wrapper task?](https://github.com/jjohannes/gradle-project-setup-howto/issues/12)
- [What are good practices when publishing libraries (API consistency, multiple variants, transitives)?](https://github.com/jjohannes/gradle-project-setup-howto/issues/17)
- [Kotlin branch: Why is there a special handling of kotlin-stdlib?](https://github.com/jjohannes/gradle-project-setup-howto/issues/13)
- [Kotlin branch: How to avoid the 'The Kotlin Gradle plugin was loaded multiple times' error?](https://github.com/jjohannes/gradle-project-setup-howto/issues/19)
- [Spring branch: Why does the example not use the Spring Boot Dependency Management plugin?](https://github.com/jjohannes/gradle-project-setup-howto/issues/20)

More questions or points you would like to discuss? Please [open an issue](https://github.com/jjohannes/gradle-project-setup-howto/issues/new).
