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
            //1. 设置context
            SqlifyContext.setSqlifyRequest(request);
            //2. 运行mybatis的mapper
            sqlifyMapper.run(request.getParam());
            //3. 获取返回值
            return SqlifyContext.getSqlifyResponse();
        } finally {
            SqlifyContext.remove();
        }

    }
}
