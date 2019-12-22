package io.github.tanghuibo.mybatissqlify.dao;

import io.github.tanghuibo.mybatissqlify.bean.SqlifyResponse;
import io.github.tanghuibo.mybatissqlify.service.SqlifyContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

/**
 * mybatis拦截器
 * @author tanghuibo
 * @date 2019/12/21下午8:05
 */
@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class SqlLogInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Configuration configuration = mappedStatement.getConfiguration();

        setSqlifyResponse(boundSql, configuration);

        return new ArrayList<>(0);
    }

    private void setSqlifyResponse(BoundSql boundSql, Configuration configuration) {
        SqlifyResponse response = new SqlifyResponse();
        //1. 获取参数替换前sql
        response.setSqlRenderBefore(getSqlRenderBefore(boundSql));
        //2. 获取参数
        response.setParam(getParam(boundSql, configuration));
        //3. 参数替换
        response.setSqlRenderAfter(getSqlRenderAfter(response.getSqlRenderBefore(), response.getParam()));
        //4. 设置上下文
        SqlifyContext.setSqlifyResponse(response);
    }

    private String getSqlRenderAfter(String sql, List<Object> params) {

        for (Object param : params) {
            sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getMysqlValue(param)
                    .replaceAll("\n", "\\\\n")));
        }
        return sql;
    }

    private String getMysqlValue(Object param) {
        if(param == null) {
            return "''";
        }
        if(param instanceof Number) {
            return String.valueOf(param);
        }
        return  String.format("'%s'", param);
    }


    private List<Object> getParam(BoundSql boundSql, Configuration configuration) {

        List<ParameterMapping> params = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();

        if(params == null || params.size() == 0) {
            return new ArrayList<>(0);
        }
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        if(typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            List<Object> list =  new ArrayList<>();
            list.add(parameterObject);
            return list;
        }

        MetaObject metaObject = configuration.newMetaObject(parameterObject);

        return params.stream().map(item -> getParam(boundSql, metaObject, item))
                .collect(Collectors.toList());
    }

    private Object getParam(BoundSql boundSql, MetaObject metaObject, ParameterMapping param) {
        String property = param.getProperty();
        if(metaObject.hasGetter(property)) {
             return metaObject.getValue(property);
        }
        if(boundSql.hasAdditionalParameter(property)) {
            return boundSql.getAdditionalParameter(property);
        }
        return null;
    }

    private String getSqlRenderBefore(BoundSql boundSql) {
        return boundSql.getSql();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
