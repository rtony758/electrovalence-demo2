package com.tony.electrovalencedemo2.domain;

public class CountDao {
    private int ghi_sfcCount;
    private int rain_sfcCount;
    private int t2m_sfcCount;

    public CountDao() {
    }

    public CountDao(int ghi_sfcCount, int rain_sfcCount, int t2m_sfcCount) {
        this.ghi_sfcCount = ghi_sfcCount;
        this.rain_sfcCount = rain_sfcCount;
        this.t2m_sfcCount = t2m_sfcCount;
    }

    public int getGhi_sfcCount() {
        return ghi_sfcCount;
    }

    public void setGhi_sfcCount(int ghi_sfcCount) {
        this.ghi_sfcCount = ghi_sfcCount;
    }

    public int getRain_sfcCount() {
        return rain_sfcCount;
    }

    public void setRain_sfcCount(int rain_sfcCount) {
        this.rain_sfcCount = rain_sfcCount;
    }

    public int getT2m_sfcCount() {
        return t2m_sfcCount;
    }

    public void setT2m_sfcCount(int t2m_sfcCount) {
        this.t2m_sfcCount = t2m_sfcCount;
    }

    @Override
    public String toString() {
        return "CountDao{" +
                "ghi_sfcCount='" + ghi_sfcCount + '\'' +
                ", rain_sfcCount='" + rain_sfcCount + '\'' +
                ", t2m_sfcCount='" + t2m_sfcCount + '\'' +
                '}';
    }
}
