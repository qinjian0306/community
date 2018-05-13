package org.chinamyheart.community.Enum;

/**
 * 用户角色
 */
public enum UserRoleEnum {

    Doctor(1, "医生"),
    Patient(2, "病人"),
    Admin(3, "管理员");

    private Integer code;
    private Object value;

    private UserRoleEnum(Integer code, Object value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
