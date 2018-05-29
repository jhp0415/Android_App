package com.full1.finaljecheon;
/**
 * Created by Full1 on 2017-07-26.
 */
//데이터 저장 필드 클래스
public class DataField {
    String name;
    String address;
    String phone;
    String image_url;
    String park;
    String homepage;
    String mileage;
    String intro;
    String exc;

    public DataField(String name, String address, String phone, String image_url, String park,
                     String homepage, String mileage, String intro,  String exc) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.image_url = image_url;
        this.park = park;
        this.homepage = homepage;
        this.mileage = mileage;
        this.intro = intro;
        this.exc = exc;

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getImage_url() {
        return image_url;
    }
    public String getPark() {
        return park;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getMileage() {
        return mileage;
    }

    public String getIntro() {
        return intro;
    }

    public String getExc() {
        return exc;
    }

//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public void setImage_url(String image_url) {
//        this.image_url = image_url;
//    }
}
