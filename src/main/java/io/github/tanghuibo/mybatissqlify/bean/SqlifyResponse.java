package io.github.tanghuibo.mybatissqlify.bean;

import java.util.List;

/**
 * 返回体
 * @author tanghuibo
 * @date 2019/12/21下午7:34
 */
public class SqlifyResponse {

    /**
     * 渲染前SQL
     */
    private String sqlRenderBefore;

    /**
     * 渲染后sql
     */
    private String sqlRenderAfter;

    /**
     * 参数
     */
    private List<Object> param;

    public String getSqlRenderAfter() {
        return sqlRenderAfter;
    }

    public void setSqlRenderAfter(String sqlRenderAfter) {
        this.sqlRenderAfter = sqlRenderAfter;
    }

    public List<Object> getParam() {
        return param;
    }

    public void setParam(List<Object> param) {
        this.param = param;
    }

    public String getSqlRenderBefore() {
        return sqlRenderBefore;
    }

    public void setSqlRenderBefore(String sqlRenderBefore) {
        this.sqlRenderBefore = sqlRenderBefore;
    }
}
