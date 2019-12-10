package com.aop.listen;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

/**
 * @author fangchangtan
 * @date 2019-07-30 14:40
 */
public class EsDemo {

    private static final String ES_IP = "localhost";
    private static final int ES_PORT = 9300;
    private static final String CLUSTER_NAME = "my-application";
    TransportClient esClient;

    public EsDemo() throws UnknownHostException {
        esClient = initEsConn();
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        EsDemo esDemo = new EsDemo();

        esDemo.test3();
        System.out.println("============ app end =======================");

    }

    /**
     * 初始化es的client连接
     * @return
     * @throws UnknownHostException
     */
    public TransportClient initEsConn() throws UnknownHostException {
        //指定ES集群
        Settings setting = Settings.builder().put("cluster.name",CLUSTER_NAME).build();

        //创建访问ES服务器的客户端
        TransportClient client = new PreBuiltTransportClient(setting)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        return client;
    }

    //1.从ES中查询数据
    public void test1() throws UnknownHostException {
        //get方式数据查询 ,参数为Index,type和id
        GetResponse response = esClient.prepareGet("lib5","testadd","P8WsAW4BKRPKG5HZ_zn9").get();

        System.out.println(response.getSourceAsString());
        esClient.close();
    }


    //2.插入数据
    public void test2() throws IOException {
        XContentBuilder doc = XContentFactory.jsonBuilder()
                .startObject()
                .field("id","1")
                .field("title","我在学习es插入操作")
                .field("content","好好学习，天天向上")
                .endObject();

        //添加一个doc
        IndexResponse response = esClient.prepareIndex("lib5","testadd",null)//id为null，由ES自己生成
                .setSource(doc).get();
        System.out.println(response.status());//打印添加是否成功
        esClient.close();
    }

    //删除文档
    public void test3() throws UnknownHostException {

        DeleteResponse response = esClient.prepareDelete("lib5","testadd","P8WsAW4BKRPKG5HZ_zn9")
                .get();
        System.out.println(response.status());//打印添加是否成功
        esClient.close();
    }

    //更新文档
    public void test4() throws IOException, ExecutionException, InterruptedException {
        XContentBuilder mydoc = XContentFactory.jsonBuilder().startObject()
                .field("title", "我在学习ES的修改操作")
                .field("newadd", "新增字段")
                .endObject();
        UpdateRequest request = new UpdateRequest();
        request.index("lib5")
                .type("testadd")
                .id("P8WsAW4BKRPKG5HZ_zn9")
                .doc(mydoc);
        UpdateResponse response = esClient.update(request).get();

        System.out.println(response.status());//打印是否成功
        esClient.close();
    }
}