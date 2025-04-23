package org.example.product.coruscant

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableSet
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter
import java.io.Writer
import java.util.regex.Pattern
import javax.activation.DataHandler
import javax.activation.DataSource
import javax.inject.Inject
import javax.mail.internet.AddressException
import javax.mail.internet.InternetAddress
import javax.mail.util.ByteArrayDataSource
import org.apache.http.client.fluent.Request
import org.json.JSONArray
import org.opensaml.saml2.metadata.EntityDescriptor
import org.reflections.Reflections
import org.slf4j.LoggerFactory

/** I am published and therefore I have Javadoc! */
class CoruscantModule {
    /** Does fancy stuff. */
    init {
        LOGGER.info("Coruscant Module created")
        CoruscantModule::class.java.getResourceAsStream("hello.txt").use { hello ->
            println(BufferedReader(InputStreamReader(hello)).readLine())
        }
    }

    @get:Inject
    val someRootEntityDescriptors: ImmutableList<EntityDescriptor>?
        get() = null

    /**
     * @param mailAddress address to check
     * @return true is address is valid
     * @throws IOException if data handler creation fails
     */
    fun isValidEmailAddress(mailAddress: String?): Boolean {
        try {
            val emailAddress = InternetAddress(mailAddress)
            emailAddress.validate()
        } catch (e: AddressException) {
            LOGGER.debug(e.message, e)
            return false
        }
        createDataHandler()
        return true
    }

    private fun createDataHandler(): DataHandler {
        val request = Request.Get("url")
        val dataSource: DataSource = ByteArrayDataSource("", "")
        return DataHandler(dataSource)
    }

    /** @return new Json mapper */
    fun createMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false)
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true)
        mapper.registerModule(JsonOrgModule())
        mapper.registerModule(JavaTimeModule())
        return mapper
    }

    /**
     * @param objectToSerialize some object
     * @return as json
     */
    fun toJSONArray(objectToSerialize: Any?): JSONArray? {
        val w: Writer = StringWriter()
        val jsonArray: JSONArray =
            try {
                createMapper().writeValue(w, objectToSerialize)
                JSONArray(w.toString())
            } catch (e: Exception) {
                LOGGER.error("Boom", e)
                return null
            }
        return jsonArray
    }

    companion object {
        private val LOGGER = LoggerFactory.getLogger(CoruscantModule::class.java)

        /**
         * @param pattern pattern to match
         * @return all matching resources
         */
        fun getResources(pattern: String): ImmutableSet<String> {
            val reflection = Reflections()
            return ImmutableSet.copyOf(reflection.getResources(Pattern.compile(pattern)))
        }
    }
}
