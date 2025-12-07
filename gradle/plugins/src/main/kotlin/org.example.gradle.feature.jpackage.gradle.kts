plugins { id("org.gradlex.java-module-packaging") }

// Multi target support and packaging
javaModulePackaging {
    target("ubuntu-24.04") {
        operatingSystem = OperatingSystemFamily.LINUX
        architecture = MachineArchitecture.X86_64
        packageTypes = listOf("deb")
        singleStepPackaging = true
    }
    target("macos-15-intel") {
        operatingSystem = OperatingSystemFamily.MACOS
        architecture = MachineArchitecture.X86_64
        packageTypes = listOf("dmg")
        singleStepPackaging = true
    }
    target("macos-15") {
        operatingSystem = OperatingSystemFamily.MACOS
        architecture = MachineArchitecture.ARM64
        packageTypes = listOf("dmg")
        singleStepPackaging = true
    }
    target("windows-2025") {
        operatingSystem = OperatingSystemFamily.WINDOWS
        architecture = MachineArchitecture.X86_64
        packageTypes = listOf("exe")
        singleStepPackaging = true
    }
}
