package com.gavin11.ipersistance.exception;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.study.mybatis.ipersistance.exception
 * @description NonSupportedOperationException <br>
 * @date 2021/4/3 15:04 <br>
 */
public class NonSupportedOperationException extends IllegalArgumentException {

    public NonSupportedOperationException() {
    }

    public NonSupportedOperationException(String s) {
        super(s);
    }

    public NonSupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NonSupportedOperationException(Throwable cause) {
        super(cause);
    }
}
