package crud.utils;

import java.lang.reflect.Field;
import java.util.Properties;

import org.aopalliance.intercept.Interceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class})})
public class PageLocalCacheDisableInterceptor implements Interceptor {

    private static final String DEFAULT_PAGE_SQLID = ".*Page$";

   
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        if (ms.getId().matches(DEFAULT_PAGE_SQLID)) {
            Class<?> clazz = ms.getClass();
            Field flushLocalCache = clazz.getDeclaredField("flushCacheRequired");
            flushLocalCache.setAccessible(true);
            flushLocalCache.set(ms, true);
        }
        return invocation.proceed();
    }

    
    public Object plugin(Object target) {
        return Plugin.wrap(target, (org.apache.ibatis.plugin.Interceptor) this);
    }

    
    public void setProperties(Properties properties) {
        //do nothing
    }

}




