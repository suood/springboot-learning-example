package org.spring.springboot;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import okhttp3.*;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.domain.ElectricStation;
import org.spring.springboot.domain.StationPort;
import org.spring.springboot.service.impl.ElectricStationService;
import org.spring.springboot.service.impl.StationPortService;
import org.spring.springboot.utils.DynamicBeanUtils;
import org.spring.springboot.utils.ReadJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by bysocket on 05/06/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationTests.class);


    @Autowired
    private ElectricStationService electricStationService;

    @Autowired
    private StationPortService stationPortService;

    @Test
    public void getStationRecord() throws IOException, ParseException {
        JSONObject jsonObject = JSON.parseObject(ReadJson.LoadFile2String());
        List<ElectricStation> electricStationListlectricStation = JSON.parseArray(jsonObject.getString("list"),ElectricStation.class);
                LOGGER.info(jsonObject.toJSONString());
                final ElectricStationService electricStationServiceLocal = DynamicBeanUtils.getBean(ElectricStationService.class);
                for (ElectricStation electricStation : electricStationListlectricStation) {
                    electricStationServiceLocal.insertOrUpdate(electricStation);
                }
//        OkHttpClient client = new OkHttpClient()
//                .newBuilder()
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(60, TimeUnit.SECONDS)
//                .readTimeout(60, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true)
////                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
//                .build();
//        Request request = new Request.Builder()
//                .url("http://api.issks.com/issksapi/V2/ec/nearbyStation/json.shtml")
//                .addHeader("Host", "api.issks.com")
//                .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
//                .addHeader("X-Requested-With", "XMLHttpRequest")
//                .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 7.1.1; ONEPLUS A5000 Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.49 Mobile MQQBrowser/6.2 TBS/043610 Safari/537.36 MicroMessenger/6.5.16.1120 NetType/WIFI Language/zh_CN")
//                .addHeader("Referer", "http://api.issks.com/issksapi/V2/ec/nearbyStation.shtml")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Accept-Language", "zh-CN,en-US;q=0.8")
//                .addHeader("Cookie", "cityName=%u5317%u4EAC%u5E02; JSESSIONID=3B940F1347BD0BB265428A9C773F3621; 14a03328ed59437f0a5a7fea4391b872=acd5ea007e5288f824ade769f0f04d5f")
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                LOGGER.info(call.toString());
//                LOGGER.info(e.getMessage());
//                System.err.println("失败" + "*****" );
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                String responseString = response.body().string();
//                JSONObject responseJSONObject = JSON.parseObject(responseString);
//                List<ElectricStation> electricStationListlectricStation = JSON.parseArray(responseJSONObject.getString("list"),ElectricStation.class);
//                LOGGER.info(responseString);
//                final ElectricStationService electricStationServiceLocal = DynamicBeanUtils.getBean(ElectricStationService.class);
//                for (ElectricStation electricStation : electricStationListlectricStation) {
//                    electricStationServiceLocal.insertOrUpdate(electricStation);
//                }
//            }
//        });
    }
    @Test
    public void getStationPort(){
        List<Map<String,Integer>> idList = electricStationService.getStationIdList();
        List<List<Map<String, Integer>>> SumIdList = Lists.partition(idList,10);
        for (int i = 0;i<SumIdList.size();i++){
            List<Map<String, Integer>> subIdList = SumIdList.get(i);
            List <StationWork> stationWorkList = new ArrayList<StationWork>(subIdList.size()) ;

            CountDownLatch latch = new CountDownLatch(10);
            for (int w = 0; w < subIdList.size() ; w++) {

                StationWork stationWork =new StationWork((Integer)idList.get(w).get("id"),null,latch);
                stationWorkList.add(stationWork);
            }
            try {
                latch.await();
            } catch (InterruptedException e) {
                System.out.println("出错重抓");
                e.printStackTrace();
            }
            List<StationPort> stationPortList = new ArrayList<StationPort>();

            for (int a = 0; a < stationWorkList.size(); a++) {
                StationWork stationWork = stationWorkList.get(i);
                JSONObject jsonObject = JSON.parseObject(stationWork.responseJsonString);
                List<StationPort> subStationPorts = JSON.parseArray(jsonObject.getString("list"),StationPort.class);
                stationPortList.addAll(subStationPorts);
            }
            stationPortService.insertBench(stationPortList);
        }


    }

    class StationWork extends Thread {
        Integer stationId ;
        CountDownLatch latch;
        String responseJsonString;
        public StationWork(Integer stationId , String responseJsonString , CountDownLatch latch){
            this.stationId=stationId;
            this.responseJsonString=responseJsonString;
            this.latch=latch;
        }
        boolean isError;
        public void  run (){
            try {


                OkHttpClient client = new OkHttpClient()
                        .newBuilder()
                        .connectTimeout(60, TimeUnit.SECONDS)
                        .writeTimeout(60, TimeUnit.SECONDS)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
//                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort)))
                        .build();
                Request request = new Request.Builder()
                        .url("http://api.issks.com/issksapi/V2/ec/chargingList.shtml?stationId="+stationId)
                        .addHeader("Host", "api.issks.com")
                        .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                        .addHeader("X-Requested-With", "XMLHttpRequest")
                        .addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 7.1.1; ONEPLUS A5000 Build/NMF26X; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/53.0.2785.49 Mobile MQQBrowser/6.2 TBS/043610 Safari/537.36 MicroMessenger/6.5.16.1120 NetType/WIFI Language/zh_CN")
                        .addHeader("Referer", "http://api.issks.com/issksapi/V2/ec/nearbyStation.shtml")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Accept-Language", "zh-CN,en-US;q=0.8")
                        .addHeader("Cookie", "cityName=%u5317%u4EAC%u5E02; JSESSIONID=3B940F1347BD0BB265428A9C773F3621; 14a03328ed59437f0a5a7fea4391b872=acd5ea007e5288f824ade769f0f04d5f")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        isError = true;
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        isError = true;
                        responseJsonString = response.body().string();

                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                latch.countDown();//这里非常有必要放在finally里面执行，无论如何都会countDown
            }
        }
    }

    class StationListWork extends  Thread{
        ElectricStationService electricStationService;
        StationListWork (){}
        @Override
        public void  run(){
        }
    }
}
