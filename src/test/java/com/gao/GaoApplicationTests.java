
package com.gao;

import com.gao.pojo.student;
import com.gao.pojo.user;
import com.gao.service.usermapper;
import com.gao.util.dayenum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class GaoApplicationTests {
    @Autowired
    private usermapper um;
    @Test
    void contextLoads() {
        List<student> ls=um.selectList(null);
        //ls.forEach(System.out::println);
       for (student s:ls
             ) {
            System.out.println(s);
        }

 for (int i=0;i<ls.size();i++){
            System.out.println(ls.get(i));
        }

    }
    @Test
    void t1(){
        dayenum de=dayenum.oneday;
        System.out.println(de);
    }
    @Test
    void t2(){
        user u=new user();
        u.setId(1);
        String uu=u.getPassword();
        System.out.println(u.getPassword());
        if (u.getPassword()==null){
            System.out.println("mimaweikong");
        }
        System.out.println(uu);
    }
    @Test
    //@Transactional
    void t3(){
        int i=1;
        i++;
        System.out.println(i);
        int p=1/0;
        i++;
        System.out.println(i);
    }

}

