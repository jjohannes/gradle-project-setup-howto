package org.example.product.coruscant;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.inject.Inject;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.util.ByteArrayDataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * I am published and therefore I have Javadoc!
 */
public class CoruscantModule {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoruscantModule.class);

    /**
     * Does fancy stuff.
     */
    @Inject
    public CoruscantModule() {
        LOGGER.info("Coruscant Module created");
        try(InputStream hello = CoruscantModule.class.getResourceAsStream("hello.txt")) {
            System.out.println(new BufferedReader(new InputStreamReader(hello)).readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param mailAddress address to check
     * @return true is address is valid
     * @throws IOException if data handler creation fails
     */
    public boolean isValidEmailAddress(String mailAddress) throws IOException {
        try {
            InternetAddress emailAddress = new InternetAddress(mailAddress);
            emailAddress.validate();
        } catch (AddressException e) {
            LOGGER.debug(e.getMessage(), e);
            return false;
        }
        createDataHandler();
        return true;
    }

    private DataHandler createDataHandler() throws IOException {
        // Request request = Request.Get("url");
        DataSource dataSource = new ByteArrayDataSource("", "");
        return new DataHandler(dataSource);
    }

    /**
     * @param pattern pattern to match
     * @return all matching resources
     */
    public static ImmutableSet<String> getResources(String pattern) {
        return ImmutableSet.of("A", "B");
    }

    /**
     * @return new Json mapper
     */
    public ObjectMapper createMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
        mapper.configure(Feature.ALLOW_COMMENTS, true);
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }

}
