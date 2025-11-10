import org.example.gradle.spotless.SortDependenciesStep

plugins { id("com.diffplug.spotless") }

spotless {
    kotlinGradle {
        ktfmt().kotlinlangStyle().configure { it.setMaxWidth(500) }
        addStep(SortDependenciesStep.create())
    }
}
