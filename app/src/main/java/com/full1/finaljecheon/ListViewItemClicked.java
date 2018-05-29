package com.full1.finaljecheon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapMarkerItem;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapView;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

public class ListViewItemClicked extends AppCompatActivity {

    Intent intent;
    DataManager tourmanager = MainActivity.tourmanager;
    int id;
    String table;
    //----------------------------------------------


    //---------------------------------
    RelativeLayout relativeLayout;
    TMapView tMapView;
    TMapPoint point;//원하는 좌표
    Bitmap nowpoint;
    Context context;
    TMapData tMapData;
    TMapMarkerItem tourMarkerItem;
    Double lat, lon;


    @Override
    protected void attachBaseContext(Context newBase) {
        //폰트설정정
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_item_clicked);
        //-------------티맵 초기화------------------------------------------------------
        nowpoint = BitmapFactory.decodeResource(getResources(), R.drawable.poi_star);
        relativeLayout = (RelativeLayout)findViewById(R.id.lay_map);
        context = getApplicationContext();
        tMapView = new TMapView(context);
        relativeLayout = (RelativeLayout) findViewById(R.id.lay_map);//??
        tMapView.setSKPMapApiKey("08b6b1e0-1a8c-3e66-816e-ca76eece65a6");
        tMapView.setLanguage(TMapView.LANGUAGE_KOREAN);
        tMapView.setZoomLevel(15);
        tMapView.setMapType(TMapView.MAPTYPE_STANDARD);
        relativeLayout.addView(tMapView);
        nowpoint=BitmapFactory.decodeResource(context.getResources(), R.drawable.poi_dot);
        tMapData = new TMapData();
        //-------------------------------------------------------------------------------------------------

        TextView title1 = (TextView)findViewById(R.id.textview_tour_titlebar);
        TextView title2 = (TextView)findViewById(R.id.textview_tour_address);
        TextView title3 = (TextView)findViewById(R.id.textview_tour_phone_data);
        TextView title4 = (TextView)findViewById(R.id.textview_tour_parking);
        TextView title5 = (TextView)findViewById(R.id.textview_tour_mileage);
        TextView title6 = (TextView)findViewById(R.id.textview_tour_homepage);
        TextView title7 = (TextView)findViewById(R.id.textview_tour_intro);
        TextView title8 = (TextView)findViewById(R.id.textview_tour_exc);
        ImageView image = (ImageView)findViewById(R.id.img_tour_mainimg);

        intent = getIntent();
        id = intent.getExtras().getInt("ID");
        table = intent.getExtras().getString("TABLE");
        tMapData = new TMapData();

        switch (table)
        {
            case "tour": {
                title1.setText(tourmanager.getInstance().getArrayList1().get(id).getName());
                title2.setText(tourmanager.getInstance().getArrayList1().get(id).getAddress());
                title3.setText(tourmanager.getInstance().getArrayList1().get(id).getPhone());
                title4.setText(tourmanager.getInstance().getArrayList1().get(id).getPark());
                title5.setText(tourmanager.getInstance().getArrayList1().get(id).getMileage());
                title6.setText(tourmanager.getInstance().getArrayList1().get(id).getHomepage());
                title7.setText(tourmanager.getInstance().getArrayList1().get(id).getIntro());
                title8.setText(tourmanager.getInstance().getArrayList1().get(id).getExc());

                Glide.with(getApplicationContext()).load(tourmanager.getInstance().getArrayList1().get(id).getImage_url()).into(image);
                SetMap(tourmanager.getInstance().getArrayList1().get(id).getAddress());
                //--------------------------------------------------------





            //------------------------------------------------------

            }
                break;
            case "motel": {
                title1.setText(tourmanager.getInstance().getArrayList2().get(id).getName());
                title2.setText(tourmanager.getInstance().getArrayList2().get(id).getAddress());
                title3.setText(tourmanager.getInstance().getArrayList2().get(id).getPhone());
                title4.setText(tourmanager.getInstance().getArrayList2().get(id).getPark());
                title5.setText(tourmanager.getInstance().getArrayList2().get(id).getMileage());
                title6.setText(tourmanager.getInstance().getArrayList2().get(id).getHomepage());
                title7.setText(tourmanager.getInstance().getArrayList2().get(id).getIntro());
                title8.setText(tourmanager.getInstance().getArrayList2().get(id).getExc());
                Glide.with(getApplicationContext()).load(tourmanager.getInstance().getArrayList2().get(id).getImage_url()).into(image);
                SetMap(tourmanager.getInstance().getArrayList2().get(id).getAddress());

            }
                break;
            case "food": {
                title1.setText(tourmanager.getInstance().getArrayList3().get(id).getName());
                title2.setText(tourmanager.getInstance().getArrayList3().get(id).getAddress());
                title3.setText(tourmanager.getInstance().getArrayList3().get(id).getPhone());
                title4.setText(tourmanager.getInstance().getArrayList3().get(id).getPark());
                title5.setText(tourmanager.getInstance().getArrayList3().get(id).getMileage());
                title6.setText(tourmanager.getInstance().getArrayList3().get(id).getHomepage());
                title7.setText(tourmanager.getInstance().getArrayList3().get(id).getIntro());
                title8.setText(tourmanager.getInstance().getArrayList3().get(id).getExc());
                Glide.with(getApplicationContext()).load(tourmanager.getInstance().getArrayList3().get(id).getImage_url()).into(image);
                SetMap(tourmanager.getInstance().getArrayList3().get(id).getAddress());
            }
                break;
        }



        ////////////


    }

    //전화걸기 클릭 이벤트
    public void onClick(View view) {
        switch (table)
        {
            case "tour":
                switch (view.getId())
                {
                    case R.id.img_callButton:
                        String tel = "tel:"+ tourmanager.getInstance().getArrayList1().get(id).getPhone();
                        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                        break;
                    case R.id.img_httpButton:
                        //홈페이지 이미지를 눌렀을때 사이트 연결하기
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(tourmanager.getInstance().getArrayList1().get(id).getHomepage()));
                        startActivity(intent2);
                        break;
                }
                break;
            case "motel":
                switch (view.getId())
                {
                    case R.id.img_callButton:
                        String tel = "tel:"+ tourmanager.getInstance().getArrayList2().get(id).getPhone();
                        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                        break;
                    case R.id.img_httpButton:
                        //홈페이지 이미지를 눌렀을때 사이트 연결하기
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(tourmanager.getInstance().getArrayList2().get(id).getHomepage()));
                        startActivity(intent2);
                        break;
                }
                break;
            case "food":
                switch (view.getId())
                {
                    case R.id.img_callButton:
                        String tel = "tel:"+ tourmanager.getInstance().getArrayList3().get(id).getPhone();
                        startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
                        break;
                    case R.id.img_httpButton:
                        //홈페이지 이미지를 눌렀을때 사이트 연결하기
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(tourmanager.getInstance().getArrayList3().get(id).getHomepage()));
                        startActivity(intent2);
                        break;
                }
                break;
        }

    }

    public void SetMap(String strdata)
    {
        try {
            tMapData.findAllPOI(strdata, 1, new TMapData.FindAllPOIListenerCallback() {
                @Override
                public void onFindAllPOI(ArrayList<TMapPOIItem> arrayList) {
                    lat = arrayList.get(0).getPOIPoint().getLatitude();
                    lon = arrayList.get(0).getPOIPoint().getLongitude();

                    point = new TMapPoint(lat, lon);

                    tMapView.setCenterPoint(lon, lat);

                    tourMarkerItem = new TMapMarkerItem();

                    tourMarkerItem.setTMapPoint(point);
                    tourMarkerItem.setVisible(TMapMarkerItem.VISIBLE);
                    tourMarkerItem.setIcon(nowpoint);
                    tMapView.addMarkerItem("nowpoint", tourMarkerItem);
                }
            });
        } catch (Exception e) {
            Toast.makeText(context, "오류", Toast.LENGTH_SHORT).show();
        }


    }

}
