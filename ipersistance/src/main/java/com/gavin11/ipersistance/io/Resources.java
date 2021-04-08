package com.gavin11.ipersistance.io;

import java.io.InputStream;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.io
 * @description Resources <br>
 * @date 2021/4/2 7:17 <br>
 */
public class Resources {

    /**
     * 获配置文件按字节输入流的形式载入内存
     * @param path 配置文件路径
     * @return 字节输入流
     */
    public static InputStream getResourceAsStream(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
