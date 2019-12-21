package io.github.tanghuibo.mybatissqlify.service.impl;

import io.github.tanghuibo.mybatissqlify.bean.SqlifyRequest;
import io.github.tanghuibo.mybatissqlify.bean.SqlifyResponse;
import io.github.tanghuibo.mybatissqlify.dao.SqlifyMapper;
import io.github.tanghuibo.mybatissqlify.service.SqlifyContext;
import io.github.tanghuibo.mybatissqlify.service.SqlifyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tanghuibo
 * @date 2019/12/21下午7:27
 */
@Service
public class SqlifyServiceImpl implements SqlifyService {

    @Resource
    SqlifyMapper sqlifyMapper;

    @Override
    public SqlifyResponse process(SqlifyRequest request) {
        try {
            SqlifyContext.setSqlifyRequest(request);
            Object param = request.getParam();
            sqlifyMapper.run(param);
            return SqlifyContext.getSqlifyResponse();
        } finally {
            SqlifyContext.remove();
        }

    }
}
