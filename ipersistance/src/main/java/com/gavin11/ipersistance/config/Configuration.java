package com.gavin11.ipersistance.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 存放数据库配置信息, 对应db-config.xml中的配置内容
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.config
 * @description Configuration <br>
 * @date 2021/4/2 7:37 <br>
 */
public class Configuration {

    private DataSource ds;
    private Map<String, MappedStatement> statements = new HashMap<>();

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public Map<String, MappedStatement> getStatements() {
        return statements;
    }

    public void setStatements(Map<String, MappedStatement> statements) {
        this.statements = statements;
    }
}
