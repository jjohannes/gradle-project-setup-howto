buildCache {
    val remoteBuildCacheURL = providers.environmentVariable("BUILD_CACHE_URL")
    if (remoteBuildCacheURL.isPresent) {
        val ci = providers.environmentVariable("CI").getOrElse("false").toBoolean()
        remote<HttpBuildCache> {
            url = uri(remoteBuildCacheURL.get())
            if (ci) {
                isPush = true
                credentials {
                    username = providers.environmentVariable("BUILD_CACHE_USER").get()
                    password = providers.environmentVariable("BUILD_CACHE_PWD").get()
                }
            }
        }
    }
}
