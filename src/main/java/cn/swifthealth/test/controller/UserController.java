package cn.swifthealth.test.controller;

import cn.swifthealth.test.po.User;
import cn.swifthealth.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gleam
 * @date 2020-6-3
 */
@RequestMapping("/user")
@RestController()
@Api(tags = "redis测试")
public class UserController {
    @Resource
    UserService userService;

    @GetMapping(value = "/{id}")
    @ApiOperation("redis测试--插入")
    public User getById(@PathVariable("id") Long id) {
        return userService.selectById(id);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("redis测试--删除")
    public Boolean deleteById(@PathVariable("id") Long id) {
        return userService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("redis测试--更新")
    public User updateById(@PathVariable("id") Long id) {
        return userService.updateById(id);
    }

    @GetMapping(value = "/refresh/{id}")
    @ApiOperation("redis测试--更新")
    public String selectByIdRefreshEvery30Seconds(@PathVariable("id") Long id) {
        return userService.selectByIdRefreshEvery30Seconds(id);
    }

}

