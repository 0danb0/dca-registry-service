package com.danb.dca.registry_service.utils;

public interface ConstantStrings {
    // ==============================
    // Generic constants
    // ==============================
    String APPLICATION_NAME_STRING = "dca-registry-service";
    String HEADER_APP_KEY_NAME_STRING = "X-APP-KEY";
    String HEADER_APP_INTERNAL_KEY_NAME_STRING = "X-INTERNAL-APP-KEY";

    // ==============================
    // Common messages
    // ==============================
    String PATTERN_APPLICATION_ID_MESSAGE = "Application not authorized";
    String NOT_NULL_APPLICATION_ID_MESSAGE = "ApplicationId couldn't be null";
    String NOT_NULL_EMAIL_MESSAGE = "Email couldn't be null";

    // ==============================
    // Database keys
    // ==============================
    String PK_KEY = "pk";
    String SK_KEY = "sk";
    String USER_UUID_KEY = "user_uuid";
    String CREATION_DATE_KEY = "creation_date";
    String UPDATE_DATE_KEY = "update_date";
    String LAST_ACCESS_DATE_KEY = "last_access_date";
    String APPLICATION_ID_KEY = "application_id";
    String EMAIL_KEY = "email_id";
    String PASSWORD_KEY = "password";
    String ACTIVE_KEY = "active";
    String ROLES_KEY = "roles";
    String ROOT_PK = "danb";
    String APPENDIX_PK = "registry";
}
