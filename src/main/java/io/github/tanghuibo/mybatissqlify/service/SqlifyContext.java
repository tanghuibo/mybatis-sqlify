package io.github.tanghuibo.mybatissqlify.service;

import io.github.tanghuibo.mybatissqlify.bean.SqlifyRequest;
import io.github.tanghuibo.mybatissqlify.bean.SqlifyResponse;

/**
 * 上下文
 * @author tanghuibo
 * @date 2019/12/21下午7:33
 */
public class SqlifyContext {
    /**
     * 请求参数
     */
    private static ThreadLocal<SqlifyRequest> sqlifyRequestThreadLocal = new ThreadLocal<>();

    /**
     * 请求参数
     */
    private static ThreadLocal<SqlifyResponse> sqlifyResultThreadLocal = new ThreadLocal<>();

    public static void setSqlifyRequest(SqlifyRequest request) {
        sqlifyRequestThreadLocal.set(request);
    }

    public static SqlifyRequest getSqlifyRequest() {
        return sqlifyRequestThreadLocal.get();
    }

    public static void setSqlifyResponse(SqlifyResponse response) {
        sqlifyResultThreadLocal.set(response);
    }

    public static SqlifyResponse getSqlifyResponse() {
        return sqlifyResultThreadLocal.get();
    }

    public static void removeSqlifyRequest() {
        sqlifyResultThreadLocal.remove();
    }
    public static void removeSqlifyReponse() {
        sqlifyResultThreadLocal.remove();
    }


    public static void remove() {
        removeSqlifyRequest();
        removeSqlifyReponse();
    }
}
