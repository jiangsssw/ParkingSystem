package wj.until;
        import org.apache.commons.lang3.StringUtils;
        import java.beans.PropertyDescriptor;
        import java.lang.reflect.Method;
        import java.lang.reflect.ParameterizedType;
        import java.lang.reflect.Type;
        import java.math.BigDecimal;
        import java.text.SimpleDateFormat;
        import java.util.*;

/**
 * Java 通过反射原理, 通过Map转为Java POJO对象, 适用于对象包含List, Map, 数组, Date等
 */
public class ReflectUtil {
    private Class<?> classBind;

    public ReflectUtil() {
    }

    public <T> void registerClass(Class<T> cl) {
        classBind = cl;
    }

    public static void main(String[] args) {
        // CheckInFlightDto dt = new CheckInFlightDto();
        // ReflectUtil util = new ReflectUtil();
        // util.registerClass(CheckInFlightDto.class);
        // util.setValue("flightNo", "12312", dt);
        // System.out.println(dt.getFlightNo());
    }

    public static void mapToObject(Map<String, Object> map, Object o) {
        ReflectUtil util = new ReflectUtil();
        util.registerClass(o.getClass());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            util.setValue(key, val, o);
        }
    }

    /**
     * 根据反射获取某字段的值
     *
     * @param name
     * @return
     */
    public <T> String getValue(String name, Object ob) {
        String value = null;
        try {
            PropertyDescriptor pd = new PropertyDescriptor(name, this.classBind);
            Method rM = pd.getReadMethod();// 获得读方法
            value = (String) rM.invoke(ob);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 根据反射设置某字段的值
     *
     * @param name
     * @param value
     */
    public <T> void setValue(String name, Object value, Object ob) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(name, this.classBind);
            Method wM = pd.getWriteMethod();// 获得写方法
            Class<?> parameterType = wM.getParameterTypes()[0];
            if (parameterType == String.class) {
                wM.invoke(ob, String.valueOf(value));
            } else if (parameterType == int.class || parameterType == Integer.class) {
                String str = String.valueOf(value);
                if (StringUtils.isNotBlank(str)) {
                    wM.invoke(ob, new BigDecimal(String.valueOf(value)).intValue());
                }
            } else if (parameterType == double.class || parameterType == Double.class) {
                String str = String.valueOf(value);
                if (StringUtils.isNotBlank(str)) {
                    wM.invoke(ob, new BigDecimal(String.valueOf(value)).doubleValue());
                }
            } else if (parameterType == java.sql.Date.class) {
                String str = String.valueOf(value);
                if (StringUtils.isNotBlank(str)) {

                    wM.invoke(ob, value);
                }
            }else if (parameterType == java.sql.Timestamp.class) {
                String str = String.valueOf(value);
                if (StringUtils.isNotBlank(str)) {

                    wM.invoke(ob, value);
                }
            }
            else if (parameterType.isArray()) {
                Object[] list = null;
                if (value instanceof List) {
                    list = ((List<?>) value).toArray();
                } else {
                    list = (Object[]) value;
                }
                Object[] args = new Object[list.length];
                for (int index = 0; index < list.length; index++) {
                    Object item = list[index];
                    if (item instanceof Number) {// 如果是数字
                        args[index] = item;
                    } else if (item instanceof String) {// 如果是字符串
                        args[index] = item;
                    } else {
                        args[index] = item;
                    }
                }
                wM.invoke(ob, args);
            } else {
                Type[] parameters = wM.getGenericParameterTypes();
                if (parameters.length > 0) {
                    if (parameters[0] instanceof ParameterizedType) {
                        ParameterizedType pt = (ParameterizedType) parameters[0];
                        Type[] actualTypeArguments = pt.getActualTypeArguments();
                        if (actualTypeArguments.length == 1) {
                            List<Object> args = new ArrayList<Object>();
                            Object[] list = null;
                            if (value instanceof List) {
                                list = ((List<?>) value).toArray();
                            } else {
                                list = (Object[]) value;
                            }
                            for (Object item : list) {
                                if (item instanceof Number) {
                                    args.add(item);
                                } else if (item instanceof String) {
                                    args.add(item);
                                } else {
                                    args.add(item);
                                }
                            }
                            wM.invoke(ob, args);
                        } else if (actualTypeArguments.length == 2) {// MAP 场合
                            Map<Object, Object> targetMp = (Map<Object, Object>) value;
                            Map<Object, Object> args = new HashMap<Object, Object>();
                            for (Object key : targetMp.keySet()) {
                                Object val = targetMp.get(key);
                                if (val instanceof Number) {
                                    args.put(key, val);
                                } else if (val instanceof String) {
                                    args.put(key, val);
                                } else {
                                    args.put(key, val);
                                }
                            }
                            wM.invoke(ob, args);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据反射获取某字段的值
     *
     * @param name
     * @return
     */
    public <T> String getValue(String name, Class<T> model, Object ob) {
        String value = "";
        try {
            PropertyDescriptor pd = new PropertyDescriptor(name, model);
            Method rM = pd.getReadMethod();// 获得读方法
            value = (String) rM.invoke(ob);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 根据反射设置某字段的值
     * @param name
     * @param value
     */
    public <T> void setValue(String name, Object value, Object ob, Class<T> model) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(name, model);
            Method wM = pd.getWriteMethod();// 获得写方法
            wM.invoke(ob, value);//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
