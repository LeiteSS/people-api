package io.github.leitess.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.github.leitess.entity.enums.PhoneType;

import java.io.IOException;

public class PhoneTypeSerialize extends StdSerializer<PhoneType> {

    public PhoneTypeSerialize() {
        super(PhoneType.class);
    }

    @Override
    public void serialize(PhoneType type, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(type.getDescription());
    }
}
