open module org.example.product.corellia.test {
    requires transitive org.example.product.corellia;
    requires transitive com.google.common;
    requires transitive jsr305;
    requires org.junit.jupiter.api;

    exports org.example.product.corellia.test.fixtures;
}
