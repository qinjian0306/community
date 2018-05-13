package org.chinamyheart.community.Enum;

/**
 *
 * 病例状态
 */
public enum CaseStatusEnum {
    Active(0, "启用中"),
    Close(1, "关闭");

    private Integer code;
    private String value;

    private CaseStatusEnum(Integer code, String value) {
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
        for (CaseStatusEnum caseStatusEnum : CaseStatusEnum.values()) {
            if (caseStatusEnum.getCode().equals(code)) {
                return caseStatusEnum.getValue();
            }
        }
        return null;
    }

}
