package com.gavin11.ipersistance.exception;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.exception
 * @description SqlParamException <br>
 * @date 2021/4/3 11:52 <br>
 */
public class SqlParamException extends IllegalArgumentException {

    public SqlParamException() {
    }

    public SqlParamException(String s) {
        super(s);
    }

    public SqlParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public SqlParamException(Throwable cause) {
        super(cause);
    }
}
