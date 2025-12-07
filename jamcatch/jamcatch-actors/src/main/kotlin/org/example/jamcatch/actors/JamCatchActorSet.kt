package org.example.jamcatch.actors

import java.io.InputStream
import java.nio.charset.StandardCharsets
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord
import org.example.jamcatch.actors.collisions.Collisions
import org.example.javarca.model.*

class JamCatchActorSet : ActorSet {
    private val actors: Set<Actor>

    init {
        val itemsCsv = JamCatchActorSet::class.java.getResourceAsStream("res/jamcatch.csv")!!
        actors =
            parse(itemsCsv)
                .map { record ->
                    Actor(
                        record.get("SYMBOL").get(0),
                        ActorProperty.entries
                            .mapNotNull { property -> parsePropertyValue(record, property) }
                            .toSet(),
                        Collisions.ALL.getOrDefault(record.get("SYMBOL").get(0), emptyMap()),
                    )
                }
                .toSet()
    }

    override fun items(): Set<Actor> {
        return actors
    }

    private fun parsePropertyValue(
        record: CSVRecord,
        property: ActorProperty,
    ): ActorPropertyModifier? {
        val value = record.get(property)
        if (!value.isBlank()) {
            return ActorPropertyModifier(property, value.toInt())
        }
        return null
    }

    private fun parse(itemsCsv: InputStream): List<CSVRecord> {
        val format = CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).get()
        return CSVParser.parse(itemsCsv, StandardCharsets.UTF_8, format).records
    }
}
