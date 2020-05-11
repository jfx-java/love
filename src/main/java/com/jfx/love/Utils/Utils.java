package com.jfx.love.Utils;



public class Utils implements Runnable {
    @Override
    public void run() {

    }


//    /**
//     * 接地json字符串到实例对象
//     *
//     * @param clazz 和JSON对应的类的Class，必须拥有setXxx()函数，其中xxx为属性
//     * @param json  被解析的JSON字符串
//     * @return 返回传入的Object对象实例
//     */
//    public static <T> T parseJson2Object(Class<T> clazz, String json) {
//        JSONObject jsonObject = null;
//        try {
//            jsonObject = new JSONObject(json);
//        } catch (Exception e) {
//            e.printStackTrace();
////            LogUtil.loge(TAG, "Exception:" + e.getMessage());
//        }
//        return parseJson2Object(clazz, jsonObject);
//    }
//
//    /**
//     * 解析JSONObject对象到具体类，递归算法
//     *
//     * @param clazz      和JSON对应的类的Class，必须拥有setXxx()函数，其中xxx为属性
//     * @param jsonObject 被解析的JSON对象
//     * @return 返回传入的Object对象实例
//     */
//    private static <T> T parseJson2Object(Class<T> clazz, JSONObject jsonObject) {
//        T obj = null;
//        try {
//            //获取clazz的实例
//            obj = clazz.newInstance();
//            // 获取属性列表
//            Field[] fields = clazz.getDeclaredFields();
//            // 遍历每个属性，如果为基本类型和String则直接赋值，如果为List则得到每个Item添加后再赋值，如果是其它类则得到类的实例后赋值
//            for (Field field : fields) {
//                // 设置属性可操作
//                field.setAccessible(true);
//                // 获取字段类型
//                Class<?> typeClazz = field.getType();
//                // 是否基础变量
//                if (typeClazz.isPrimitive()) {
//                    setProperty(obj, field, jsonObject.opt(field.getName()));
//                } else {
//                    // 得到类型实例
//                    Object typeObj;
//                    if (typeClazz.isInterface() && typeClazz.getSimpleName().contains("List")) {//Field如果声明为List<T>接口，由于接口的Class对象不能newInstance()，此时需要转化为ArrayList
//                        typeObj = ArrayList.class.newInstance();
//                    } else {
//                        typeObj = typeClazz.newInstance();
//                    }
//                    // 是否为List
//                    if (typeObj instanceof List) {
//                        // 得到类型的结构，如:java.util.ArrayList<com.xxx.xxx>
//                        Type type = field.getGenericType();
//                        ParameterizedType pt = (ParameterizedType) type;
//                        // 获得List元素类型
//                        Class<?> dataClass = (Class<?>) pt.getActualTypeArguments()[0];
//                        // 得到List的JSONArray数组
//                        JSONArray jArray = jsonObject.getJSONArray(field.getName());
//                        // 将每个元素的实例类加入到类型的实例中
//                        for (int i = 0; i < jArray.length(); i++) {
//                            //对于数组，递归调用解析子元素
//                            ((List<Object>) typeObj).add(parseJson2Object(dataClass, jsonObject.getJSONArray(field.getName()).getJSONObject(i)));
//                        }
//                        setProperty(obj, field, typeObj);
//                    } else if (typeObj instanceof String) {// 是否为String
//                        setProperty(obj, field, jsonObject.opt(field.getName()));
//                    } else {//是否为其它对象
//                        //递归解析对象
//                        setProperty(obj, field, parseJson2Object(typeClazz, jsonObject.getJSONObject(field.getName())));
//                    }
//                }
//            }
//        } catch (Exception e) {
//            LogUtil.loge(TAG, "Exception:" + e.getMessage());
//        }
//        return obj;
//    }
//
//    /**
//     * 给实例对象的成员变量赋值
//     *
//     * @param obj      实例对象
//     * @param field    要被赋值的成员变量
//     * @param valueObj 要被赋值的成员变量的值
//     */
//    private static void setProperty(Object obj, Field field, Object valueObj) {
//        try {
//            Class<?> clazz = obj.getClass();
//            //获取类的setXxx方法，xxx为属性
//            Method method = clazz.getDeclaredMethod("set" + field.getName().substring(0, 1).toUpperCase(Locale.getDefault()) + field.getName().substring(1), field.getType());
//            //设置set方法可访问
//            method.setAccessible(true);
//            //调用set方法为实例对象的成员变量赋值
//            method.invoke(obj, valueObj);
//        } catch (NoSuchMethodException e) {
//            LogUtil.loge(TAG, "method [set" + field.getName().substring(0, 1).toUpperCase(Locale.getDefault()) + field.getName().substring(1) + "] not found");
//        } catch (IllegalArgumentException e) {
//            LogUtil.loge(TAG, "method [set" + field.getName().substring(0, 1).toUpperCase(Locale.getDefault()) + field.getName().substring(1) + "] illegal argument:" + valueObj + "," + e.getMessage());
//        } catch (IllegalAccessException e) {
//            LogUtil.loge(TAG, "IllegalAccessException:" + e.getMessage());
//        } catch (InvocationTargetException e) {
//            LogUtil.loge(TAG, "IllegalAccessException:" + e.getMessage());
//        }
//
//    }
}
