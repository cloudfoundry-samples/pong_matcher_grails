// code_snippet gsg-grails-s5 start

dataSource {
    pooled = true
    jmxExport = true
    driverClassName = "com.mysql.jdbc.Driver"
    dialect = org.hibernate.dialect.MySQL5InnoDBDialect
    uri = new URI(System.env.DATABASE_URL ?: "mysql://foo:bar@localhost")
    username = uri.userInfo ? uri.userInfo.split(":")[0] : ""
    password = uri.userInfo ? uri.userInfo.split(":")[1] : ""
    url = "jdbc:mysql://" + uri.host + uri.path

    properties {
        dbProperties {
            autoReconnect = true
        }
    }
}
// code_snippet gsg-grails-s5 end


hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}
