package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.pojo.ResultModel;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("add")
    public ResultModel<Object> add(User user) {
        try {
            userService.save(user);
            return new ResultModel<>().success();

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel<>().error("服务器异常");
        }
    }

    @RequestMapping("get")
    public ResultModel<Object> get(Integer id) {
        try {
            User user = userService.getById(id);
            return new ResultModel().success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常");
        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("delete")
    public ResultModel<Object> delete(Integer id) {
        try {
            userService.removeById(id);
            return new ResultModel().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常");
        }
    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ResultModel<Object> update(User user) {
        try {
            userService.updateById(user);
            return new ResultModel().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常");
        }
    }

    /**
     * 查询一条
     * @param userName
     * @param userPwd
     * @return
     */
    /**
     * 查询单条
     *
     * @param userName 用户名
     * @param userPwd  密码
     * @return
     */
    @RequestMapping("getOne")
    public ResultModel<Object> getOne(String userName, String userPwd) {
        try {
            // MP 查询条件构造器
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", userName);
            queryWrapper.eq("user_pwd", userPwd);
            // where userName = ? and userPwd = ?
            User user = userService.getOne(queryWrapper);
            return new ResultModel().success(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }


}
