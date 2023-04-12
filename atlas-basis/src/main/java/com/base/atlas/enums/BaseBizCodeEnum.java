package com.base.atlas.enums;

/**
 * @author wujie
 * @date 2023/2/9 16:42
 */
public enum BaseBizCodeEnum {

    REQ_TIME_ERROR ("1001","时间戳为空"),
    NO ("0","否");
    /**
     * code
     */
    private String code;
    /**
     * 名称
     */
    private String name;

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
    BaseBizCodeEnum(String code,String name){
        this.code = code;
        this.name = name;
    }


}
