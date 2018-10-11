package com.tony.electrovalencedemo2.domain;

public class Electrovalence {
    /**
     * 1.北京
     * 2.天津
     * 3.河北
     * 4.江苏
     * 5.山东
     * 6.湖北
     * */
    private Integer id;
    private String province;
    private Double electrovalence;

    public Electrovalence() {
    }

    public Electrovalence(Integer id) {
        this.id = id;
    }

    public Electrovalence(Integer id, String province, Double electrovalence) {
        this.id = id;
        this.province = province;
        this.electrovalence = electrovalence;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Double getElectrovalence() {
        return electrovalence;
    }

    public void setElectrovalence(Double electrovalence) {
        this.electrovalence = electrovalence;
    }

    @Override
    public String toString() {
        return "Electrovalence{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", electrovalence=" + electrovalence +
                '}';
    }
}
