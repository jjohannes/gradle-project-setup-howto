module org.example.product.corellia.test.fixtures {
    exports org.example.product.corellia.fixtures;

    requires transitive org.example.product.corellia;
    requires transitive com.google.common;
    requires transitive javax.annotations.jsr305;

}
