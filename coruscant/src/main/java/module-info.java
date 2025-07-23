module org.example.product.coruscant {
    exports org.example.product.coruscant;

    requires transitive com.fasterxml.jackson.annotation;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive com.google.common;
    requires transitive jakarta.inject;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires jakarta.activation;
    requires jakarta.mail;
    requires org.slf4j;
}
