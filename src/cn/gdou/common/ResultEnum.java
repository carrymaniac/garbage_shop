package cn.gdou.common;

public enum ResultEnum {
    ADD_SUCCESS("增加成功",100),
    DELETE_SUCCESS("删除成功",200),
    DELETE_FAIL("删除失败",201),
    UPDATE_SUCCESS("更新成功",300),
    UPDATE_FAIL("更新失败",301),
    ;
    private String msg;
    private Integer code;

    ResultEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
