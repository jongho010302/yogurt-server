package com.yogurt.domain.base.model;

public class UserRole {

    public enum RoleEnum {
        ROLE_MEMBER, ROLE_INSTRUCTOR, ROLE_MANAGER, ROLE_OWNER, ROLE_DEVELOPER
    }

    public static final String PATTERN = "(ROLE_STUDENT|ROLE_OWNER|ROLE_MANAGER|ROLE_DEVELOPER|ROLE_INSTRUCTOR)";
    public static final String PATTERN_MESSAGE = "역할의 형식을 맞춰 주세요.";

}