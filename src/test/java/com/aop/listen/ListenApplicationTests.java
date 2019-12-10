package com.aop.listen;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.InetAddress;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListenApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void test() throws IOException {
        Settings settings = Settings.builder()
                .put("cluster.name", "my-application").build();
        //Add transport addresses and do something with the client...
        // on startup
        TransportClient client = new PreBuiltTransportClient(settings)
            .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        //创建索引
        client.admin().indices().prepareCreate("bolg1").get();


        /*
      {
           创建出的索引结构如下
          "article": {
                  "properties": {
                      "id":{
                       "type":"long",
                       "store":true
                      },
                      "title":{
                       "type":"text",
                       "store":true,
                       "index":true,
                       "analyzer":"ik_smart"
                      },
                      "content":{
                       "type":"text",
                       "store":true,
                       "index":true,
                       "analyzer":"ik_smart"
                      }
                  }
              }
        }
 */
//        拼装JSON
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject() //{
                .startObject("article") // "article": {
                .startObject("properties")
                .startObject("id")
                .field("type", "long")
                .field("store", true)
                .endObject()//}
                .startObject("title")
                .field("type", "text")
                .field("store", true)
                .field("index", true)
                .field("analyzer", "ik_smart")
                .endObject()
                .startObject("content")
                .field("type", "text")
                .field("store", true)
                .field("index", true)
                .field("analyzer", "ik_smart")
                .endObject()
                .endObject()
                .endObject()
                .endObject();

        //添加映射
        client.admin().indices()
                .preparePutMapping("bolg1") //准备添加映射=>指定映射所在索引名称
                .setType("article")//指定类型名称
                .setSource(builder) //添加映射内容
                .get();

        //删除索引
//        client.admin().indices().prepareDelete("helll").get();

        // on shutdown
        client.close();
    }
}
