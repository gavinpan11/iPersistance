package com.gavin11.ipersistance.config;

import com.gavin11.ipersistance.enums.SqlCommandType;

/**
 * 对应*Mapper.xml文件中的内容
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.config
 * @description MappedStatement <br>
 * @date 2021/4/2 7:39 <br>
 */
public class MappedStatement {

    /** statementId, 由namespace.id构成 */
    private String id;
    /** 参数类型 */
    private String paramType;
    /** 结果类型 */
    private String resultType;
    /** sql语句 */
    private String sql;
    /** sql类型 */
    private SqlCommandType sqlCommandType;

    public MappedStatement() {
    }

    public MappedStatement(String id, String paramType, String resultType, String sql, SqlCommandType sqlCommandType) {
        this.id = id;
        this.paramType = paramType;
        this.resultType = resultType;
        this.sql = sql;
        this.sqlCommandType = sqlCommandType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public void setSqlCommandType(SqlCommandType sqlCommandType) {
        this.sqlCommandType = sqlCommandType;
    }
}
