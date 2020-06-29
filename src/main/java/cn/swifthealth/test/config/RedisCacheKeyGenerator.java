package cn.swifthealth.test.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * 生成 md5 类型的 key 值
 */
public class RedisCacheKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        String className = target.getClass().getName();
        String methodName = method.getName();

        StringBuffer stringBuffer = new StringBuffer(className).append(methodName);
        for (Object param : params) {
            stringBuffer.append(param.toString());
        }
        return DigestUtils.md5Hex(stringBuffer.toString());
//        return  className+"."+methodName+"|"+DigestUtils.md5Hex(stringBuffer.toString());
    }
}
