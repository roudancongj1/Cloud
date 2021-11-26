
package com.gao;

import com.gao.pojo.student;
import com.gao.service.usermapper;
import com.gao.util.dayenum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}

