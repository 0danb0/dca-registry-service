package com.danb.dca.registry_service.enums;

import lombok.Getter;

import static com.danb.dca.product_serivce.utils.ConstantStrings.*;
import static com.danb.dca.registry_service.utils.ConstantStrings.APPLICATION_NAME_STRING;
import static com.danb.dca.registry_service.utils.ConstantStrings.HEADER_APP_KEY_NAME_STRING;

@Getter
public enum ConstantEnum {

    APPLICATION_NAME(APPLICATION_NAME_STRING),
    HEADER_APP_KEY_NAME(HEADER_APP_KEY_NAME_STRING);

    private final String value;

    ConstantEnum(String value){
        this.value = value;
    }
}
