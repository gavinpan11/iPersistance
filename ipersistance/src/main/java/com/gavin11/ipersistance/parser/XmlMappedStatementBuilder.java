package com.gavin11.ipersistance.parser;

import com.gavin11.ipersistance.exception.NonSupportedOperationException;
import com.gavin11.ipersistance.config.Configuration;
import com.gavin11.ipersistance.config.MappedStatement;
import com.gavin11.ipersistance.enums.SqlCommandType;
import com.gavin11.ipersistance.io.Resources;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.parser
 * @description XmlMappedStatementBuilder <br>
 * @date 2021/4/2 21:02 <br>
 */
public class XmlMappedStatementBuilder {

    private final Configuration configuration;

    public XmlMappedStatementBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    @SuppressWarnings("unchecked")
    public void parse(String path) throws Exception {
        try (
                InputStream in = Resources.getResourceAsStream(path)
                ) {
            Document doc = new SAXReader().read(in);
            Element rootElement = doc.getRootElement();
            String namespace = rootElement.attributeValue("namespace");

            List<Element> statementEles = rootElement.selectNodes("//select | //insert | //delete | //update");

            Map<String, MappedStatement> statementMap = new HashMap<>();
            for (Element statementEle : statementEles) {
                SqlCommandType sqlCommandType = getSqlCommandType(statementEle);
                String id = statementEle.attributeValue("id");
                MappedStatement mappedStatement = new MappedStatement(
                        id,
                        statementEle.attributeValue("paramType"),
                        statementEle.attributeValue("resultType"),
                        statementEle.getTextTrim(),
                        sqlCommandType
                );
                statementMap.put(namespace + "." + id, mappedStatement);
            }

            configuration.setStatements(statementMap);
        }
    }

    /**
     * 获取sql操作类型
     * @param statementEle 标签对象
     * @return 操作类型
     */
    private SqlCommandType getSqlCommandType(Element statementEle) {
        String name = statementEle.getName();
        SqlCommandType sqlCommandType;
        switch (name) {
            case "select":
                sqlCommandType = SqlCommandType.SELECT;
                break;
            case "insert":
                sqlCommandType = SqlCommandType.INSERT;
                break;
            case "update":
                sqlCommandType = SqlCommandType.UPDATE;
                break;
            case "delete":
                sqlCommandType = SqlCommandType.DELETE;
                break;
             default:
                 throw new NonSupportedOperationException("operation not support: " + name);

        }
        return sqlCommandType;
    }
}
