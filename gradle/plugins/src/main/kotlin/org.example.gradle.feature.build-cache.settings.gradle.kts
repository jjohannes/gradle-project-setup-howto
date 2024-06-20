buildCache {
    val ci = providers.environmentVariable("CI").getOrElse("false").toBoolean()
    local { isEnabled = !ci }
    remote<HttpBuildCache> {
        url = uri("https://cache.onepiece.software/cache/")
        if (ci) {
            isPush = true
            credentials {
                username = providers.environmentVariable("BUILD_CACHE_USER").get()
                password = providers.environmentVariable("BUILD_CACHE_PWD").get()
            }
        }
    }
}
