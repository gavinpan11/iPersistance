package com.gavin11.ipersistance.config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.config
 * @description BoundSql <br>
 * @date 2021/4/3 11:28 <br>
 */
public class BoundSql {
    /** 解析后的sql形式, 如select * from user where id = ? and username = ?*/
    private String parsedSql;
    /** 存放该条sql中， 占位符#{}中的参数名 */
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    public BoundSql() {
    }

    public BoundSql(String parsedSql, List<ParameterMapping> parameterMappings) {
        this.parsedSql = parsedSql;
        this.parameterMappings = parameterMappings;
    }

    public String getParsedSql() {
        return parsedSql;
    }

    public void setParsedSql(String parsedSql) {
        this.parsedSql = parsedSql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
