package com.example.gkjexam.MyBean;

/**
 * Created by 郭康杰 on 2017/8/18.
 */
public class MyCariBean {
    private String area;
    private String coverImg;
    private int lastUpdate;
    private String name;
    private String type;

    @Override
    public String toString() {
        return "MyCariBean{" +
                "area='" + area + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", lastUpdate=" + lastUpdate +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public MyCariBean() {
    }

    public MyCariBean(String area, String coverImg, int lastUpdate, String name, String type) {
        this.area = area;
        this.coverImg = coverImg;
        this.lastUpdate = lastUpdate;
        this.name = name;
        this.type = type;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public int getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(int lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
