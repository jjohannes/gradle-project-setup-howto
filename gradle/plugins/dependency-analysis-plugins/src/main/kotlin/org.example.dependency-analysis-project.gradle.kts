plugins {
    id("java")
    id("org.gradlex.java-module-dependencies")
}

tasks.check {
    dependsOn(tasks.checkAllModuleInfo)
}
