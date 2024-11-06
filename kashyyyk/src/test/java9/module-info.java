// module-info.java in 'java9' folder is used to define requires (dependencies) for whitebox tests
open module org.example.product.kashyyyk.test {
    requires org.example.product.kashyyyk;

    requires org.junit.jupiter.api;
}
