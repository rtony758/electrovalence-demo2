package com.tony.electrovalencedemo2.cotroller;


import com.google.gson.Gson;

import com.tony.electrovalencedemo2.util.GetWeatherInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
public class HomeController extends BaseController {

    Gson gson = new Gson();
    GetWeatherInfo getWeatherInfo = new GetWeatherInfo();

    @RequestMapping("/")
    @ResponseBody
    public Map<String, Object> latLng(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> json = new HashMap<String, Object>();
        String lat = request.getParameter("lat");
        String lng = request.getParameter("lng");
        System.out.println("获取到的lat:" + lat);
        System.out.println("获取到的lng:" + lng);
        System.out.println("#########################################################");

        GetWeatherInfo getWeatherInfo = new GetWeatherInfo();
        getWeatherInfo.setLng(lng);
        getWeatherInfo.setLat(lat);

        getWeatherInfo.getInfo();
        json.put("ghi_sfcCount",getWeatherInfo.getGhi_sfcCount());
        json.put("rain_sfcCount",getWeatherInfo.getRain_sfcCount());
        json.put("t2m_sfcCount",getWeatherInfo.getT2m_sfcCount());

        return json;
    }


//    @RequestMapping("/asd")
//    @ResponseBody
//    public String tokenUtil(HttpServletRequest request, HttpServletResponse response) throws Exception {
//
//        GetWeatherInfo getWeatherInfo = new GetWeatherInfo();
//        getWeatherInfo.getInfo();
//        long lastToken = System.currentTimeMillis();
//        System.out.println(lastToken);
//
//        return null;
//    }


}
