plugins {
    id("org.example.java-library")
}

dependencies {
    implementation("org.apache.solr:solr-solrj")
    implementation("org.apache.zookeeper:zookeeper-jute")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
}
