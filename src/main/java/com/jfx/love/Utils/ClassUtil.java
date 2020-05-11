package com.jfx.love.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassUtil {

    private static Logger logger = LoggerFactory.getLogger(ClassUtil.class);
    public static Object getBean(String className) {
        Class clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (Exception ex) {
            logger.info("找不到指定的类");
        }
        if (clazz != null) {
            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception ex) {
                logger.info("找不到指定的类");
            }
        }
        return null;
    }
}
