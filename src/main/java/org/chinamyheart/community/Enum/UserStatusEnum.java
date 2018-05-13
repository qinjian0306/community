package org.chinamyheart.community.Enum;

/**
 * 医生状态
 */
public enum  UserStatusEnum {
    UnReview(0, "未填写认证信息"),// 未填写认证信息
    Pass(1, "批准"),// 批准认证
    Refuse(2, "拒绝"),// 拒绝认证
    Waiting(3, "已认证,正在审核");// 已填写认证信息 正在审核

    private Integer code;
    private Object value;

    private UserStatusEnum(Integer code, Object value) {
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
