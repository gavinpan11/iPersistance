package com.gavin11.ipersistance.exception;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.exception
 * @description WrongResultException <br>
 * @date 2021/4/3 13:55 <br>
 */
public class WrongResultException extends RuntimeException {

    public WrongResultException() {
    }

    public WrongResultException(String message) {
        super(message);
    }

    public WrongResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongResultException(Throwable cause) {
        super(cause);
    }

    public WrongResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
