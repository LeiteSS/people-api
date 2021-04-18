package lab.aulaDIO.personApi.utils;

import lab.aulaDIO.personApi.dto.request.PhoneDTO;
import lab.aulaDIO.personApi.entity.Phone;
import lab.aulaDIO.personApi.entity.enums.PhoneType;

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
