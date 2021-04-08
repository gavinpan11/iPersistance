package com.gavin11.ipersistance.parser;

import com.gavin11.ipersistance.config.Configuration;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.parser
 * @description XmlConfigurationBuilder <br>
 * @date 2021/4/2 8:25 <br>
 */
public class XmlConfigurationBuilder {

    private final Configuration configuration;

    public XmlConfigurationBuilder() {
        this.configuration = new Configuration();
    }

    @SuppressWarnings("unchecked")
    public Configuration getConfiguration(InputStream in) throws Exception {
        Document doc = new SAXReader().read(in);
        Element rootElement = doc.getRootElement();
        DataSource cpds = getDataSourceConfig(rootElement);

        Element mappersEle = rootElement.element("mappers");
        List<Element> mapperEles = mappersEle.selectNodes("//mapper");

        XmlMappedStatementBuilder msBuilder = new XmlMappedStatementBuilder(configuration);
        for (Element me : mapperEles) {
            String path = me.attributeValue("resource");
            msBuilder.parse(path);
        }

        configuration.setDs(cpds);

        return configuration;
    }

    private DataSource getDataSourceConfig(Element rootElement) throws PropertyVetoException {
        Element dsEle = rootElement.element("datasource");
        @SuppressWarnings("unchecked")
        List<Element> propEles = dsEle.selectNodes("//property");

        Properties dsProperties = new Properties();
        for (Element propEle : propEles) {
            dsProperties.setProperty(propEle.attributeValue("name"), propEle.attributeValue("value"));
        }

        // 设置设置连接池参数
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass(dsProperties.getProperty("driverClass"));
        cpds.setJdbcUrl(dsProperties.getProperty("jdbcUrl"));
        cpds.setUser(dsProperties.getProperty("username"));
        cpds.setPassword(dsProperties.getProperty("password"));
        cpds.setInitialPoolSize(Integer.parseInt(dsProperties.getProperty("initPoolSize")));
        cpds.setMaxPoolSize(Integer.parseInt(dsProperties.getProperty("maxPoolSize")));
        cpds.setMaxIdleTime(Integer.parseInt(dsProperties.getProperty("maxIdleTime")));
        return cpds;
    }
}
