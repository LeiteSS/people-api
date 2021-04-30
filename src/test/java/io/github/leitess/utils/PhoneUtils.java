package io.github.leitess.utils;

import io.github.leitess.dto.request.PhoneDTO;
import io.github.leitess.entity.Phone;
import io.github.leitess.entity.enums.PhoneType;

public class PhoneUtils {

    private static final String PHONE_NUMBER = "(11)98239-1938";
    private static final PhoneType PHONE_TYPE = PhoneType.MOBILE;
    private static final long PHONE_ID = 1L;

    public static PhoneDTO createFakeDTO() {
        return PhoneDTO.builder()
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }

    public static Phone createFakeEntity() {
        return Phone.builder()
                .id(PHONE_ID)
                .number(PHONE_NUMBER)
                .type(PHONE_TYPE)
                .build();
    }
}
