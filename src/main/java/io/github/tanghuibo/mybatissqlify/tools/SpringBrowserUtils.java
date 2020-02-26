package io.github.tanghuibo.mybatissqlify.tools;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;


/**
 * Spring浏览器工具类
 *
 * @author tanghuibo
 * @date 2020/2/27上午12:07
 */
public class SpringBrowserUtils {

    /**
     * 打开浏览器
     * @param context
     * @throws Exception
     */
    public static void openBrowserSpringMVC(ConfigurableApplicationContext context) {
        Environment bean = context.getBean(Environment.class);
        String port = bean.getProperty("server.port", "8080");
        try {
            String url = String.format("http://127.0.0.1:%s/", port);
            BrowserUtils.openUrl(url);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
