package com.gavin11.ipersistance.exception;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.exception
 * @description MappedException <br>
 * @date 2021/4/3 12:16 <br>
 */
public class MappedException extends RuntimeException {
    public MappedException() {
    }

    public MappedException(String message) {
        super(message);
    }

    public MappedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MappedException(Throwable cause) {
        super(cause);
    }

    public MappedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
