package com.danb.dca.registry_service.enums;

import lombok.Getter;

@Getter
public enum ErrorMsg {

    DCA_RGT_SRV_01("DCA-RGT-SRV-01", "Invalid fields:", ""),

    DCA_RGT_SRV_02("DCA-RGT-SRV-02", "Invalid fields: application-license", "Invalid or inactive key, contact support."),

    DCA_RGT_SRV_99("DCA-RGT-SRV-99", "Generic error", "");

    private final String code;

    private final String message;

    private final String detail;


    ErrorMsg(String code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }
}

