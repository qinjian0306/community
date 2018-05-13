package org.chinamyheart.community.Enum;

/**
 *
 * 病例状态
 */
public enum CaseStatusEnum {
    Active(0, "启用中"),
    Close(1, "关闭");

    private Integer code;
    private Object value;

    private CaseStatusEnum(Integer code, Object value) {
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
