package io.github.leitess.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import io.github.leitess.entity.enums.PhoneType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Jackson {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(phoneTypeModuleMapper());

        return objectMapper;
    }

    private SimpleModule phoneTypeModuleMapper() {
        SimpleModule dateModule = new SimpleModule("JSONRaceModule", PackageVersion.VERSION);

        dateModule.addSerializer(PhoneType.class, new PhoneTypeSerialize());
        dateModule.addDeserializer(PhoneType.class, new PhoneTypeDeserialize());

        return dateModule;
    }
}
