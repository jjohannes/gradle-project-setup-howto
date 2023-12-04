module org.example.product.kashyyyk {
    exports org.example.product.kashyyyk;

    requires transitive org.example.product.naboo;
    requires transitive org.example.product.tatooine;

    requires org.example.product.bespin;
    requires org.example.product.kamino;
}
