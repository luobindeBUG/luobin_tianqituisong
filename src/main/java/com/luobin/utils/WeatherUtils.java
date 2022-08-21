package com.luobin.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luobin.pojo.Weather;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherUtils {

    public static Weather getWeather(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> map = new HashMap<>();
        map.put("district_id","所在地行政代码 市 需修改");
        map.put("data_type","all");
        map.put("ak","自己应用的ak 需修改");
        String res = restTemplate.getForObject(
                "https://api.map.baidu.com/weather/v1/?district_id={district_id}&data_type={data_type}&ak={ak}",
                String.class,
                map);
        JSONObject json = JSONObject.parseObject(res);
        JSONArray forecasts = json.getJSONObject("result").getJSONArray("forecasts");
        List<Weather> weathers = forecasts.toJavaList(Weather.class);
        JSONObject now = json.getJSONObject("result").getJSONObject("now");
        Weather weather = weathers.get(0);
        weather.setText_now(now.getString("text"));
        weather.setTemp(now.getString("temp"));
        return weather;
    }

}
