package com.gavin11.ipersistance.enums;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.study.mybatis.ipersistance.enums
 * @description SqlCommandType <br>
 * @date 2021/4/3 14:58 <br>
 */
public enum SqlCommandType {
    /** select操作 */
    SELECT,
    /** insert操作 */
    INSERT,
    /** update操作 */
    UPDATE,
    /** delete操作 */
    DELETE,
    /** 未知操作 */
    UNKNOWN
}
