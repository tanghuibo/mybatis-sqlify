package io.github.tanghuibo.mybatissqlify.bean;

/**
 * 请求体
 * @author tanghuibo
 * @date 2019/12/21下午7:23
 */
public class SqlifyRequest {

    /**
     * mybatis中的sql
     */
    private String mybatisSql;

    /**
     * 参数
     */
    private Object param;


    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getMybatisSql() {
        return mybatisSql;
    }

    public void setMybatisSql(String mybatisSql) {
        this.mybatisSql = mybatisSql;
    }
}
