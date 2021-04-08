package com.gavin11.ipersistance.reflection;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.utils
 * @description ClassBuilder <br>
 * @date 2021/4/3 11:43 <br>
 */
public class ClassBuilder {

    private static final String METHOD_PREFIX_SET = "set";
    private static final String METHOD_PREFIX_GET = "get";

    private final Class<?> clazz;
    private final Map<String, Method> setMethods = new HashMap<>();
    private final Map<String, Method> getMethods = new HashMap<>();

    private ClassBuilder(Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * 根据类名获取类对象
     * @param className 类名
     * @return 类对象
     * @throws ClassNotFoundException e
     */
    public static ClassBuilder getClassBuilder(String className) throws ClassNotFoundException {
        /*if (className == null) {
            return null;
        }*/
        Class<?> clazz = Class.forName(className);
        ClassBuilder cb = new ClassBuilder(clazz);

        cb.differMethods(clazz);

        return cb;
    }

    public Object newInstance() throws Exception {
        return clazz.newInstance();
    }

    public Map<String, Method> getSetMethods() {
        return setMethods;
    }

    public Map<String, Method> getGetMethods() {
        return getMethods;
    }

    private void differMethods(Class<?> clazz) {
        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith(METHOD_PREFIX_SET)) {
                String fieldName = fieldNameByMethod(name, METHOD_PREFIX_SET);
                setMethods.put(fieldName, method);
            } else if (name.startsWith(METHOD_PREFIX_GET)) {
                String fieldName = fieldNameByMethod(name, METHOD_PREFIX_GET);
                getMethods.put(fieldName, method);
            }
        }
    }

    /**
     * 根据get/set方法名, 获取相应的字段名称
     * @param methodName get/set方法名
     * @param prefix 需要移除的前缀
     * @return 方法名对应的字段名称
     */
    private String fieldNameByMethod(String methodName, String prefix) {
        char[] chars = methodName.replace(prefix, "").toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }
}
