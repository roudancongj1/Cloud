
package com.gao;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.gao.pojo.student;
import com.gao.pojo.user;
import com.gao.service.userbasemapper;
import com.gao.util.dayenum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collections;
import java.util.List;

@SpringBootTest
class GaoApplicationTests {
    @Autowired
    private userbasemapper um;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
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
    @Test
        //@Transactional
    void t4(){
        stringRedisTemplate.opsForValue().append("ms","hello");
    }
    @Test
    void t5(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/data","root","123")
                .globalConfig(builder -> {
                    builder.author("Gao") // 设置作者
                            .outputDir("D://_____idea//_____autoscan"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("sacn"); // 设置父包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("juxing") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .execute();
        //默认生成D://_____idea//_____autoscan扫描一套
    }

}

