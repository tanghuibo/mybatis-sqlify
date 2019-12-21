package io.github.tanghuibo.mybatissqlify.service;

import io.github.tanghuibo.mybatissqlify.bean.SqlifyRequest;
import io.github.tanghuibo.mybatissqlify.bean.SqlifyResponse;

/**
 * @author tanghuibo
 * @date 2019/12/21下午7:26
 */
public interface SqlifyService {
    /**
     * 处理sql
     * @param request
     * @return
     */
    SqlifyResponse process(SqlifyRequest request);
}
