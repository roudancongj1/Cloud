package com.sch.controller;

import com.sch.utils.resultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2021/12/31 14:02
 */
@RestController
@RequestMapping("/data")
public class dataController {
    @GetMapping("test")
    public Map test(){
        List l=new ArrayList();
        l.add("222222");
        l.add("333333");
        //return l.toString();
        Map map = new HashMap();
        map.put("info","啊大苏打");
        map.put("name","张三");
        return map;
    }
    @GetMapping("cardInfo")
    public List<Map> cardInfo(){
        Map map1 = new HashMap();
        map1.put("label","优秀的团队");
        map1.put("service","训练有素的服务");
        Map map2 = new HashMap();
        map2.put("label","和善的");
        map2.put("service","大苏打撒旦");
        Map map3 = new HashMap();
        map3.put("label","阿斯顿萨达");
        map3.put("service","3232");
        Map map4 = new HashMap();
        map4.put("label","嗷嗷嗷");
        map4.put("service","顶顶顶顶顶");
        List list = new ArrayList();
        list.add(map1);
        list.add(map2);list.add(map3);
        list.add(map4);


        return list;
    }
    @RequestMapping("result")
    public resultUtil result(){
       // return resultUtil.ok().put("asdasdaa");
       // return resultUtil.ok().put("2232","dsadsa");

      //  return resultUtil.ok().put("asdas","dsadsa");
        HashMap<String, Object> map = new HashMap<>();
        map.put("asdas","sad");
        map.put("22","sad");
        map.put("asd33as","sad");
        return resultUtil.ok().put(map);
    }
}
