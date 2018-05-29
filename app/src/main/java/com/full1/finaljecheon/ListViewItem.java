package com.full1.finaljecheon;

/**
 * Created by Park JH on 2017-07-11.
 */

public class ListViewItem{
    private String imgurl ;
    private String titleStr ;
    private String descStr ;
    private String descStr2 ;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    public void setDesc2(String desc2) {
        descStr2 = desc2 ;
    }

    //public String getIcon() {return this.imgurl ;}
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getImgurl() {
        return imgurl;
    }
    public String getDesc2() {
        return descStr2;
    }

}
