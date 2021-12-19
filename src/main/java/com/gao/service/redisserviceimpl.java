package com.gao.service;

import com.alibaba.fastjson.JSON;
import com.gao.dao.usermapper;
import com.gao.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2021/12/17 23:32
 */
@Service
public class redisserviceimpl implements redisservice {
    @Autowired
    private usermapper um;
    @Override
    @Cacheable(value = "test",key = "1")
    public List<user> getuserforcache() {
        JSON.toJSONString(um.selectaa());
        user u=new user(1,"asd",2);
        List<user> lu = new ArrayList();
        lu.add(u);

        return lu;
    }
    @Cacheable(value = "test",key = "2")
    public String getuserforcache2() {

        user u=new user(1,"asd",2);
        List<user> lu = new ArrayList();
        lu.add(u);

        return JSON.toJSONString(um.selectaa());
    }
    /*//修改了数据库的数据，同时更新缓存。先调用目标方法，然后缓存方法结果。
    @CachePut(value = "test",key="#result.id")  //只能是result.id
    public UserInfo updateUser(UserInfo userInfo) {
        userInfoMapper.updateUser(userInfo);
        return userInfo;
    }

    //删除数据之后，清除缓存
    @CacheEvict(value = "test", key = "#id")
    public String deleteUser(Integer id) {
        userInfoMapper.deleteUserById(id);
        return "已删除";
    }*/
}
