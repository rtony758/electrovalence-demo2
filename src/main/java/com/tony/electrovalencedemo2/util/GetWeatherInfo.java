package com.tony.electrovalencedemo2.util;

import com.tony.electrovalencedemo2.service.RedisClient;
import net.sf.json.JSONObject;

import static com.sun.tools.corba.se.idl.constExpr.Expression.one;

public class GetWeatherInfo {

    private String lat;
    private String lng;
    private Integer ghi_sfcCount;
    private Integer rain_sfcCount;
    private Integer t2m_sfcCount;

    public Integer getGhi_sfcCount() {
        return ghi_sfcCount;
    }

    public void setGhi_sfcCount(Integer ghi_sfcCount) {
        this.ghi_sfcCount = ghi_sfcCount;
    }

    public Integer getRain_sfcCount() {
        return rain_sfcCount;
    }

    public void setRain_sfcCount(Integer rain_sfcCount) {
        this.rain_sfcCount = rain_sfcCount;
    }

    public Integer getT2m_sfcCount() {
        return t2m_sfcCount;
    }

    public void setT2m_sfcCount(Integer t2m_sfcCount) {
        this.t2m_sfcCount = t2m_sfcCount;
    }

    public GetWeatherInfo() {
    }

    public GetWeatherInfo(String lat, String lng, long one, Integer ghi_sfcCount, Integer rain_sfcCount, Integer t2m_sfcCount) {
        this.lat = lat;
        this.lng = lng;
        this.ghi_sfcCount = ghi_sfcCount;
        this.rain_sfcCount = rain_sfcCount;
        this.t2m_sfcCount = t2m_sfcCount;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "GetWeatherInfo{" +
                "lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", one=" + one +
                '}';
    }

    public String getInfo() {

        long startTime = System.currentTimeMillis();

        TokenUtil tokenUtil = new TokenUtil();
        //Map<Integer, CountDao> countDaoMap = new HashMap<Integer, CountDao>();

        /**
         * 从前端获取经纬度数值。
         * lat，lng经纬度
         * */

        Integer ghi_sfcCount = 0;
        Integer rain_sfcCount = 0;
        Integer t2m_sfcCount = 0;

        Integer ghi_sfc;
        Integer rain_sfc;
        Integer t2m_sfc;

        String lng = getLng();
        String lat = getLat();

        String userName = "tcszhb";
        String token = null;

        token = new RedisClient().GetTokenByUser(userName);

        System.out.println(token);

        for (int i = 2008; i <= 2017; i++) {

            int year = i;
            String urlGet = "http://54.223.199.3:8082/weather/v1/newres_datasets/gw-c9a3/PY/stat/" + year + "/?lon=" + lng + "&lat=" + lat + "&vars=ghi_sfc,rain_sfc,t2m_sfc";

            /** 取得气象数据JSON赋值给 freeMeso */
            String freeMeso = null;
            try {
                freeMeso = tokenUtil.GetWeatherData(userName, token, urlGet);
            } catch (Exception e) {
                e.printStackTrace();
            }
            //System.out.println(freeMeso);
            if (freeMeso != null) {

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

                ghi_sfcCount += ghi_sfc;
                rain_sfcCount += rain_sfc;
                t2m_sfcCount += t2m_sfc;

            }
//            System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////////////");
//            System.out.println("年份：" + yearInfo);
//            System.out.println("地面短波总辐照度:" + ghi_sfc / 1000 + "千瓦/平方米");
//            System.out.println("降雨量:" + rain_sfc + "毫米");
//            System.out.println("2米温度:" + t2m_sfc + "摄氏度");
//            System.out.println("///////////////////////////////////////////////////////////////////////////////////////////////////////////");

        }

        System.out.println("08~17年均辐照总量相加：" + ghi_sfcCount / 10000 + "千瓦/平方米");
        System.out.println("08~17年均降雨量：" + rain_sfcCount / 10 + "毫米");
        System.out.println("08～17年均平均温：" + t2m_sfcCount / 10 + "摄氏度");
        setGhi_sfcCount(ghi_sfcCount / 10000);
        setRain_sfcCount(rain_sfcCount / 10);
        setT2m_sfcCount(t2m_sfcCount / 10);

        long endTime = System.currentTimeMillis();
        System.out.println("当前程序耗时：" + (endTime - startTime) + "ms");

        return null;
    }


    //public String getInfo() {
//
//    long startl = System.currentTimeMillis();
//
//    TokenUtil tokenUtil = new TokenUtil();
//    //Map<Integer, CountDao> countDaoMap = new HashMap<Integer, CountDao>();
//
//    /**
//     * 从前端获取经纬度数值。
//     * lat，lng经纬度
//     * */
//
//    int ghi_sfcCount = 0;
//    int rain_sfcCount = 0;
//    int t2m_sfcCount = 0;
//
//    Integer ghi_sfc = null;
//    Integer rain_sfc;
//    Integer t2m_sfc;
//
//    String lng = getLng();
//    String lat = getLat();
//    System.out.println(one);
//
//    String userName = "tcszhb";
//    String token=null;
//
//    token= GetToken(userName);
//
//    System.out.println(token);
//    long one = System.currentTimeMillis();
//    System.out.println("从1970年01月01日00时00分00秒000毫秒—到—>获取到token的毫秒数：" + one);
//    setOne(one);
//    System.out.println(one);
//
//    int year = 0;
//    String urlGet = "http://54.223.199.3:8082/weather/v1/newres_datasets/gw-c9a3/PY/stat/" + year + "/?lon=" + lng + "&lat=" + lat + "&vars=ghi_sfc,rain_sfc,t2m_sfc";
//
//    Map<Integer,CountDao> maps=new HashMap<Integer,CountDao>();
//    try{
//        testReturn(userName,token,lng,lat,maps);
//    }catch (Exception ee){
//        System.out.println("=====test4===");
//
//    }
//
//
//    System.out.println("08~17年均辐照总量相加：" + ghi_sfcCount / 10000 + "千瓦/平方米");
//    System.out.println("08~17年均降雨量：" + rain_sfcCount / 10 + "毫米");
//    System.out.println("08～17年均平均温：" + t2m_sfcCount / 10 + "摄氏度");
//    setGhi_sfcCount(ghi_sfcCount / 10000);
//    setRain_sfcCount(rain_sfcCount / 10);
//    setT2m_sfcCount(t2m_sfcCount / 10);
//
//
//    long endTime = System.currentTimeMillis();
//    System.out.println("当前程序耗时：" + (endTime - startl) + "ms");
//
//    return null;
//}
//    private String GetToken(HttpServletRequest request, String userName) {
//
//        String strResultToken = null;
//        if (request != null) {
//            HttpSession session = request.getSession();
//            Object objToken = session.getAttribute("weatherToken");
//            Object objTokenTime = session.getAttribute("tokenTimestamp");
//            String strToken = null;
//
//            if (objToken != null) {
//                strToken = objToken + "";
//
//            }
//
//            if (strToken == null || strToken == "") {
//                strResultToken = GetTokenByUrl(userName);
//                session.setAttribute("weatherToken", strResultToken);
//                session.setAttribute("tokenTimestamp", System.currentTimeMillis());
//            } else {
//                if (objTokenTime != null) {
//                    long tokenS = (long) objTokenTime;
//                    long tokenE = System.currentTimeMillis();
//
//                    Date dateS = this.stampToDate(tokenS);
//                    Date dateE = this.stampToDate(tokenE);
//                    long difl = dateE.getTime() - dateS.getTime();
//                    double result = difl * 1.0 / (1000 * 60 * 60);
//                    if (result >= 24) {
//                        strResultToken = GetTokenByUrl(userName);
//                        session.setAttribute("weatherToken", strResultToken);
//                        session.setAttribute("tokenTimestamp", System.currentTimeMillis());
//                    } else {
//                        strResultToken = strToken;
//                    }
//                }
//            }
//        } else {
//            System.out.println("===null====");
//        }
//
//        return strResultToken;
//    }
//
//    private String GetTokenByUrl(String userName) {
//
//        System.out.println("=====test====GetTokenByUrl");
//        TokenUtil tokenUtil = new TokenUtil();
//        /** 设置链接参数*/
//
//        String passWord = "aLN@9cPTEJWs";
//        String url = "https://metapi.goldwind.com.cn:443/api/token";
//        /** 加参去获取token的JSON串儿*/
//        JSONObject doGetToken = new JSONObject();
//        try {
//            doGetToken = JSONObject.fromObject((tokenUtil.GetWeatherData(userName, passWord, url)));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        /** 摘出token串儿中的token*/
//        Object accessToken = doGetToken.get("token");
//        /** 将摘出的token赋值给变量*/
//        String token = (String) accessToken;
//        return token;
//    }
//
//    /*
//     * 将时间戳转换为时间
//     */
//    public static Date stampToDate(long lt) {
//
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        Date date = new Date(lt);
//
//        return date;
//    }

}
