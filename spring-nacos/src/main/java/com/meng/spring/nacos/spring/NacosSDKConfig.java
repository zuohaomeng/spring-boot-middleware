package com.meng.spring.nacos.spring;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author ZuoHao
 * @date 2021/3/12
 */
public class NacosSDKConfig {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now().minusDays(1);
        //连接到目标服务的地址
        //指定dataid、 groupid
        String serverAddr = "localhost:8848";
        String dataId = "application";
        String groupId = "DEFAULT_GROUP";
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        try {
            //ConfigService-> NacosConfigService
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, groupId, 3000);
            System.out.println(content);
            configService.addListener(dataId, groupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String configInfo) {
                    System.out.println("configInfo:" + configInfo);
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
