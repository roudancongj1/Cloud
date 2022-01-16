
package com.sch;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
class SchApplicationTests {

    @Test
    void contextLoads() {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mydata","root","123")
                .globalConfig(builder -> {
                    builder.author("Gao") // 设置作者
                            .outputDir("D://_____idea//_____autoscan"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("sacn"); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("place") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .execute();
    }

}

