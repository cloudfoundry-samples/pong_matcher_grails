dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "org.postgresql.Driver"
    dialect = org.hibernate.dialect.PostgreSQLDialect
    uri = new URI(System.env.DATABASE_URL ?: "postgres://foo:bar@localhost")
    username = uri.userInfo ? uri.userInfo.split(":")[0] : ""
    password = uri.userInfo ? uri.userInfo.split(":")[1] : ""
    url = "jdbc:postgresql://" + uri.host + uri.path

    properties {
        maxActive = 2
    }
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}
