package com.sch.utils;

import net.sf.jsqlparser.expression.StringValue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Auth: Gao
 * @Date: 2022/1/12 12:49
 */
public class captchaUtil {

    private BufferedImage image;
    private String codes;
    private static final char[] code="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".toCharArray();
    private static final String SESSION_CODE_NAME="code";

    private captchaUtil() {
        init();
    }

    public static captchaUtil Instance(){
        return new captchaUtil();
    }

    public BufferedImage getImage(){
        return this.image;
    }

    public String getStr(){
        return this.codes;
    }

    private void init(){
        int width=100;
        int height=40;
        Random random = new Random();
        //内存中创建图像
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //获取图形上下文--一个color用一次
        Graphics g =image.getGraphics();
        //填充背景色
        g.setColor(randomColor(200,250));
        g.fillRect(0,0,width,height);
        //设置字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        //随机产生线
        g.setColor(randomColor(160,200));
        for(int i=0;i<155;i++){
            int x=random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        //获取随机4位验证码
        String codes="";
        for (int i = 0; i < 4; i++) {
            String codei= String.valueOf(code[random.nextInt(code.length)]);
            codes += codei;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(codei,i*13+20,30);
        }
        //赋值验证码
        this.codes=codes;
        //图像生效
        g.dispose();
        //赋值图片
        this.image=image;
    }

    //给定范围获取随机色
    private static Color randomColor(int x,int y){
        Random random = new Random();
        if(x>255)
            x=255;
        if(y>255)
            y=255;
        int r=x+random.nextInt(y-x);
        int g=x+random.nextInt(y-x);
        int b=x+random.nextInt(y-x);
        return new Color(r,g,b);
    }
}
