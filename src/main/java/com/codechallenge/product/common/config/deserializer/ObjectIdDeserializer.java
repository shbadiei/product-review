package com.codechallenge.product.common.config.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.bson.types.ObjectId;

import java.io.IOException;

public class ObjectIdDeserializer extends JsonDeserializer<ObjectId> {

    @Override
    public ObjectId deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode oid = ((JsonNode)jsonParser.readValueAsTree()).get("$oid");
        return new ObjectId(oid.asText());
    }

}
