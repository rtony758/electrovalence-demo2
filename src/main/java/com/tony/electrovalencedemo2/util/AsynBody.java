package com.tony.electrovalencedemo2.util;


import com.tony.electrovalencedemo2.domain.CountDao;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class AsynBody {
    public AsynBody() {

    }

    private Map<Integer, CountDao> runBody(int i, String userName, String token, String lng, String lat, Map<Integer, CountDao> countDaoMap) {
        int year = i;
        Integer ghi_sfc = null;
        Integer rain_sfc;
        Integer t2m_sfc;

        TokenUtil tokenUtil = new TokenUtil();
        String urlGet = "http://54.223.199.3:8082/weather/v1/newres_datasets/gw-c9a3/PY/stat/" + year + "/?lon=" + lng + "&lat=" + lat + "&vars=ghi_sfc,rain_sfc,t2m_sfc";


        /** 取得气象数据JSON赋值给 freeMeso */
        String freeMeso = null;
        try {
            freeMeso = tokenUtil.GetWeatherData(userName, token, urlGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(freeMeso);

        /** 取的气象资源返回的JSON串，解析info部分*/
        JSONObject json = JSONObject.fromObject(freeMeso);
        String info = json.optString("info");

        /** 摘出info部分--->年份*/
        String stat = json.optString("stat");
        json = JSONObject.fromObject(info);
        Integer yearInfo = Integer.valueOf(json.optString("year"));

        /** stat部分*/
        json = JSONObject.fromObject(stat);
        ghi_sfc = Integer.valueOf(json.optInt("ghi_sfc"));
        rain_sfc = Integer.valueOf(json.optInt("rain_sfc"));
        t2m_sfc = Integer.valueOf(json.optInt("t2m_sfc"));

        CountDao countDao = new CountDao(ghi_sfc, rain_sfc, t2m_sfc);

        countDaoMap = this.save(i, countDao, countDaoMap);

        System.out.println("====test11=" + year + ",ghi=" + ghi_sfc + ",rain=" + rain_sfc + ",t2m=" + t2m_sfc + "\n");

        return countDaoMap;

    }

    private Map<Integer, CountDao> save(int year, CountDao countDao, Map<Integer, CountDao> map) {
        Map<Integer, CountDao> mapTp = new HashMap<Integer, CountDao>();
        mapTp = map;
        if (map != null && map.size() > 0) {

            if (map.size() == 10) {
//                int ghi_sfcCount = 0;
//                int rain_sfcCount = 0;
//                int t2m_sfcCount = 0;
//
//                for (Map.Entry<Integer, CountDao> mapOne : map.entrySet()) {
//                    CountDao countdao=mapOne.getValue();
//                    if (countdao!=null) {
//                        ghi_sfcCount += countdao.getGhi_sfcCount();
//                        rain_sfcCount += countdao.getRain_sfcCount();
//                        t2m_sfcCount += countdao.getT2m_sfcCount();
//                        //resultDao=new CountDao(ghi_sfcCount,rain_sfcCount,t2m_sfcCount);
//                    }
//
//                }
            }
        } else {
            mapTp.put(year, countDao);
        }
        return mapTp;
    }

    @SuppressWarnings("unchecked")
    class MyCallable implements Callable {
        private int i;
        private String userName;
        private String token;
        private String lng;
        private String lat;
        private Map<Integer, CountDao> countDaoMap;

        MyCallable(int i, String userName, String token, String lng, String lat, Map<Integer, CountDao> countDaoMap) {
            this.i = i;
            this.userName = userName;
            this.token = token;
            this.lng = lng;
            this.lat = lat;
            this.countDaoMap = countDaoMap;

        }

        public Object call() throws Exception {
            Map<Integer, CountDao> resultMap = new HashMap<Integer, CountDao>();
            resultMap = runBody(i, userName, token, lng, lat, countDaoMap);

            return resultMap;
        }
    }
}
