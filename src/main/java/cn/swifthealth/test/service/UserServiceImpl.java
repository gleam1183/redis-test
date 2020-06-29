package cn.swifthealth.test.service;

import cn.swifthealth.test.config.CacheCustom;
import cn.swifthealth.test.po.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {
    /**
     * 先用id生成key，在用这个key查询redis中有无缓存到对应的值
     * <p>
     * 若无缓存，则执行方法selectById，并把方法返回的值缓存到redis
     * <p>
     * 若有缓存，则直接把redis缓存的值返回给用户，不执行方法
     */
    @Cacheable(cacheNames = "user", key = "#id")
    @Override
    public User selectById(Long id) {
        //直接new一个给定id的用户对象，来返回给用户
        return new User(id, "redisOnly", "password");
    }

    /**
     * 按照id删除用户缓存
     *
     * @param id
     * @return 若想删除redis缓存的所有用户数据，可以把注解改成
     * @CacheEvict(cacheNames="user", allEntries=true)
     */
    @CacheEvict(cacheNames = "user", key = "#id")
    @Override
    public boolean deleteById(Long id) {
        // 可以在这里添加删除数据库对应用户数据的操作
        return true;
    }

    // 记录
    private AtomicInteger executeCout = new AtomicInteger(0);

    /**
     * @param id
     * @return
     *
     * @cacheable： 只会执行一次，当标记在一个方法上时表示该方法是支持缓存的，Spring会在其被调用后将其返回值缓存起来，
     * 以保证下次利用同样的参数来执行该方法时可以直接从缓存中获取结果
     * @cacheput： 标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，
     * 并将执行结果以键值对的形式存入指定的缓存中。
     */
    @CachePut(cacheNames = "user", key = "#id")
    @Override
    public User updateById(Long id) {
        // 每次方法执行executeCout
        System.out.println("");
        // 必须把更新后的用户数据返回，这样才能把它缓存到redis中
        return new User(id, "redisOnly" + executeCout.incrementAndGet(), "password");
    }

    /**
     * Cacheable
     * value：缓存key的前缀。
     * key：缓存key的后缀。
     * sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false。
     */
//    @Cacheable(value = "user#30#30", key = "#id")
//    @Cacheable(value = {"user#${select.cache.timeout:10}#${select.cache.refresh:30}"}, key = "#id")
    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    @CacheCustom(expire ="PT5s" ,threshold="PT10s")
    public String selectByIdRefreshEvery30Seconds(Long id) {
        //直接new一个给定id的用户对象，来返回给用户
        return String.valueOf(executeCout.incrementAndGet());
    }
}