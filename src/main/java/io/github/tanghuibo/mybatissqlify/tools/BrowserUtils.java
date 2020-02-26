package io.github.tanghuibo.mybatissqlify.tools;

import org.apache.ibatis.javassist.NotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 浏览器工具类
 *
 * @author tanghuibo
 * @date 2020/2/27上午12:07
 */
public class BrowserUtils {

    /**
     * 打开浏览器
     * @param url
     * @throws Exception
     */
    public static void openUrl(String url) throws Exception {
        // 获取操作系统的名字
        String osName = System.getProperty("os.name", "");
        if (osName.startsWith("Mac OS")) {
            openInMac(url);
        } else if (osName.startsWith("Windows")) {
            openInWindows(url);
        } else {
            openInOther(url);
        }
    }

    private static void openInOther(String url) throws Exception {
        // Unix or Linux的打开方式
        String[] browsers = {"firefox", "opera", "konqueror", "epiphany",
                "mozilla", "netscape"};
        String browser = null;
        for (int count = 0; count < browsers.length && browser == null; count++) {
            // 执行代码，在brower有值后跳出，
            // 这里是如果进程创建成功了，==0是表示正常结束。
            if (Runtime.getRuntime()
                    .exec(new String[]{"which", browsers[count]})
                    .waitFor() == 0) {
                browser = browsers[count];
            }
        }
        if (browser == null) {
            throw new NotFoundException("Could not find web browser");
        }  else {
            Runtime.getRuntime().exec(new String[]{browser, url});
        }
    }

    private static void openInWindows(String url) throws IOException {
        // windows的打开方式。
        Runtime.getRuntime().exec(
                "rundll32 url.dll,FileProtocolHandler " + url);
    }

    private static void openInMac(String url) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        // 苹果的打开方式
        Class fileMgr = Class.forName("com.apple.eio.FileManager");
        Method openURL = fileMgr.getDeclaredMethod("openURL", String.class);
        openURL.invoke(null, url);
    }
}
