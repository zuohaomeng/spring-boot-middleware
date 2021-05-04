package com.meng.spring.elasticsearch.essdk;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author ZuoHao
 * @date 2021/4/30
 */
public class ESSdkService {
    void create(){
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));

    }

    public static void main(String[] args) {
        new ESSdkService().create();
    }
}
