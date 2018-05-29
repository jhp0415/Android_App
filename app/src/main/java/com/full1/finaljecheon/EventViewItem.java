package com.full1.finaljecheon;

/**
 * Created by Park JH on 2017-07-11.
 */

public class EventViewItem{
    private String imgurl ;
    private String titleStr ;
    private String place ;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
    public void setPlace(String place) {
        this.place = place;
    }



    //public String getIcon() {return this.imgurl ;}
    public String getTitle() {
        return this.titleStr ;
    }
    public String getImgurl() {
        return imgurl;
    }
    public String getPlace() {
        return place;
    }


}
