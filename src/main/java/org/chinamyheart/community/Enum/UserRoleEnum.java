package org.chinamyheart.community.Enum;

/**
 * 用户角色
 */
public enum UserRoleEnum {

    Doctor(1, "医生"),
    Patient(2, "病人"),
    Admin(3, "管理员");

    private Integer code;
    private String value;

    private UserRoleEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByCode(Integer code) {
        for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
            if (userRoleEnum.getCode().equals(code)) {
                return userRoleEnum.getValue();
            }
        }
        return null;
    }

}
