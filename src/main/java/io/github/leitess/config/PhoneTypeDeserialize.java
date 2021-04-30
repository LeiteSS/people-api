package io.github.leitess.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.github.leitess.entity.enums.PhoneType;

import java.io.IOException;

public class PhoneTypeDeserialize extends StdDeserializer<PhoneType> {

    public PhoneTypeDeserialize() {
        super(PhoneType.class);
    }

    @Override
    public PhoneType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return PhoneType.of(p.getText());
    }
}
