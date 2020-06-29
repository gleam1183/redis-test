package cn.swifthealth.test.service;

import cn.swifthealth.test.po.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UserService {
    User selectById(Long id);

    boolean deleteById(Long id);

    User updateById(Long id);

    String selectByIdRefreshEvery30Seconds(Long id);
}
