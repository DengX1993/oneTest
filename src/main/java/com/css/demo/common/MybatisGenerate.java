package com.css.demo.common;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MybatisGenerate
 * @Description TODO
 * @Date 2020/7/30 13:40
 * @Version 1.0
 */
public class MybatisGenerate {
    public static void main(String[] args) throws URISyntaxException {
        InputStream is=null;
        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            is = classloader.getResourceAsStream("generatorConfig.xml");
            System.out.println("逆向生成成功");
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(is);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(is!=null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
