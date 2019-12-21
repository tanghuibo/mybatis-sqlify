package io.github.tanghuibo.mybatissqlify.dao;

import io.github.tanghuibo.mybatissqlify.service.SqlifyContext;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;

/**
 * @author tanghuibo
 * @date 2019/12/21下午7:29
 */
public interface SqlifyMapper {

    /**
     * 通过 SqlProvider#getSql动态运行sql
     * @param param
     * @return
     */
    @Lang(XMLLanguageDriver.class)
    @SelectProvider(type = SqlProvider.class, method = "run")
    Object run(Object param);

    class SqlProvider {
        public String run() {
            String sql = SqlifyContext.getSqlifyRequest().getMybatisSql();
            return String.format("<script>%s</script>", sql);
        }
    }
}
