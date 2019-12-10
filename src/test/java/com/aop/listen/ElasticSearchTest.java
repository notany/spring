package com.aop.listen;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @author ：张红
 * @date ：2019/10/14 11:46
 * @description：
 * @version:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {
    TransportClient client;
    @Before
    @SuppressWarnings({ "unchecked" })
    public void before() throws UnknownHostException, InterruptedException, ExecutionException {
        Settings esSettings = Settings.builder()
                .put("cluster.name", "my-application") //设置ES实例的名称
                .put("client.transport.sniff", false) //自动嗅探整个集群的状态，把集群中其他ES节点的ip添加到本地的客户端列表中
                .build();
        client = new PreBuiltTransportClient(esSettings);//初始化client较老版本发生了变化，此方法有几个重载方法，初始化插件等。
        //此步骤添加IP，至少一个，其实一个就够了，因为添加了自动嗅探配置
        client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
    }

    @Test
    public void index() throws Exception {
        Map<String,Object> infoMap = new HashMap<String, Object>();
        infoMap.put("name", "广告信息11");
        infoMap.put("title", "我的广告22");
        infoMap.put("createTime", new Date());
        infoMap.put("count", 1022);
        IndexResponse indexResponse = client.prepareIndex("test", "info","100").setSource(infoMap).execute().actionGet();
        System.out.println("id:"+indexResponse.getId());
    }

    @Test
    public void get() throws Exception {
        GetResponse response = client.prepareGet("sxq", "user", "2")
                .execute().actionGet();
        System.out.println("response.getId():"+response.getId());
        System.out.println("response.getSourceAsString():"+response.getSourceAsString());
    }

    @Test
    public void query() throws Exception {
        //term查询
//        QueryBuilder queryBuilder = QueryBuilders.termQuery("age", 50) ;
        //range查询
        QueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gt(50);
        SearchResponse searchResponse = client.prepareSearch("sxq")
                .setTypes("user")
                .setQuery(rangeQueryBuilder)
                .addSort("age", SortOrder.DESC)
                .setSize(20)
                .execute()
                .actionGet();
        SearchHits hits = searchResponse.getHits();
        System.out.println("查到记录数："+hits.getTotalHits());
        SearchHit[] searchHists = hits.getHits();
        if(searchHists.length>0){
            for(SearchHit hit:searchHists){
            }
        }
    }

}