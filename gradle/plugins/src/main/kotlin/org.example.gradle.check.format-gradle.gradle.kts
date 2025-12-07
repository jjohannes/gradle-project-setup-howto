plugins { id("com.diffplug.spotless") }

spotless { kotlinGradle { ktfmt().kotlinlangStyle().configure { it.setMaxWidth(500) } } }
