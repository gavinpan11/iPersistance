package com.gavin11.ipersistance.session;

import com.gavin11.ipersistance.config.Configuration;
import com.gavin11.ipersistance.io.Resources;
import com.gavin11.ipersistance.parser.XmlConfigurationBuilder;

import java.io.InputStream;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.factory
 * @description SqlSessionFactoryBuilder <br>
 * @date 2021/4/2 7:43 <br>
 */
public class SqlSessionFactoryBuilder {

    /**
     * 解析xml配置文件
     * @param in in
     * @return SqlSessionFactory对象
     * @throws Exception e
     */
    public static SqlSessionFactory build(InputStream in) throws Exception {
        // 解析db-config.xml, 将其中的内容封装Configuration中
        XmlConfigurationBuilder xcb = new XmlConfigurationBuilder();
        Configuration configuration = xcb.getConfiguration(in);

        // 创建一个SqlSessionFactory对象
        return new DefaultSqlSessionFactory(configuration);
    }

    /**
     * 解析配置文件
     * @param path 配置文件路径
     * @return SqlSessionFactory对象
     * @throws Exception e
     */
    public static SqlSessionFactory build(String path) throws Exception {
        try (
                InputStream in = Resources.getResourceAsStream(path)
                ) {
            return build(in);
        }
    }

    /**
     * 解析配置文件
     * @return SqlSessionFactory对象
     * @throws Exception e
     */
    public static SqlSessionFactory build() throws Exception {
        String path = "db-config.xml";
        return build(path);
    }
}
