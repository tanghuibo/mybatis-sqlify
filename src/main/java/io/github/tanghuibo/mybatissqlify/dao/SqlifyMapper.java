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
     * 通过 SqlProvider#run动态获取sql并运行
     * @param param
     * @return
     */
    @Lang(XMLLanguageDriver.class)
    @SelectProvider(type = SqlProvider.class, method = "run")
    Object run(Object param);

    class SqlProvider {
        /**
         * 动态sql拼接
         * @return
         */
        public String run() {
            //1. 从上下文中获取sql
            String sql = SqlifyContext.getSqlifyRequest().getMybatisSql();
            //拼接sql
            return String.format("<script>%s</script>", sql);
        }
    }
}
