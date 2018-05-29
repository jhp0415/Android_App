package com.full1.finaljecheon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.skp.Tmap.TMapData;
import com.skp.Tmap.TMapGpsManager;
import com.skp.Tmap.TMapPOIItem;
import com.skp.Tmap.TMapPoint;
import com.skp.Tmap.TMapPolyLine;
import com.skp.Tmap.TMapView;


import java.text.DecimalFormat;
import java.util.ArrayList;


public class Tmap extends AppCompatActivity implements TMapGpsManager.onLocationChangedCallback{

    RelativeLayout relativeLayout;
    TMapView tmapView;
    EditText edittext;
    Button btn_search, btn_now; //검색, 현재위치에서 검색
    TextView texttime, textdistance; //시간, 거리
    Context context = null;//이거는 왜 있는거지?
    TMapGpsManager gps;
    TMapPolyLine tMapPolyLine;


    /**
     * ----------검색할 때-----------
     */
    TMapPoint startpoint;
    Double lat, lon;
    TMapData tMapData;
    Double distance;//시간구할 때


    /**
     * -----현재위치에서-------------
     */

    TMapPoint nowstart;
    Double lat_n, lon_n;
    TMapData tMapDataN;
    Double distance_n;//시간 구할 때
    Double latitude;
    Double longitude;


    /**
     * --------------------한방엑스포 좌표------------------------
     */
    TMapPoint endpoint;


    Bitmap start;
    Bitmap end;
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        //폰트설정정
//        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route2_layout);

        /**변수 초기화*/
        context = getApplicationContext();         //메인 액티비티에서 동작할거에요.
        tmapView = new TMapView(context);
        endpoint = new TMapPoint(37.1451356, 128.1604225);
        start = BitmapFactory.decodeResource(context.getResources(), R.drawable.poi_star);
        end = BitmapFactory.decodeResource(context.getResources(), R.drawable.poi_end);
        btn_search = (Button) findViewById(R.id.btn_search);
        edittext = (EditText) findViewById(R.id.edittext_find_address);
        btn_now = (Button) findViewById(R.id.btn_now);
        tMapData = new TMapData();



        /**----------------------------------------------------------------------------------------------*/
        relativeLayout = (RelativeLayout) findViewById(R.id.lay_map);
        tmapView.setSKPMapApiKey("08b6b1e0-1a8c-3e66-816e-ca76eece65a6");
        tmapView.setLanguage(TMapView.LANGUAGE_KOREAN);//언어설정
        tmapView.setZoomLevel(15);
        tmapView.setMapType(TMapView.MAPTYPE_STANDARD);
        relativeLayout.addView(tmapView);
        tmapView.setTrackingMode(true);//단말의 현재위치로 화면이동//사용여부 반환은 왜 하는거지?
        /**----------------------------------------------------------------------------------------------*/


        /**-----------------------------------------------------------*/

        gps = new TMapGpsManager(this);
        gps.setMinDistance(5);//몇m마다
        gps.setMinTime(1000);//몇초마다  1000=1초
        gps.setProvider(gps.NETWORK_PROVIDER);//실내에서 와이파이로  GPS_PROVIDER 실외에서 내장 gps로
        gps.OpenGps();
        //gps.getLocation();

        /**----------------------------------------------------------*/



        /**--------------------------------------현재위치에서-----------------------------------------*/

        btn_now = (Button)findViewById(R.id.btn_now);

        btn_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lat_n = gps.getLocation().getLatitude();
                lon_n = gps.getLocation().getLongitude();

                nowstart = new TMapPoint(lat_n, lon_n);


                tMapData.findPathData(nowstart, endpoint, new TMapData.FindPathDataListenerCallback() {
                    @Override
                    public void onFindPathData(TMapPolyLine tMapPolyLine) {
                        tmapView.setTMapPathIcon(start, end);

                        tMapPolyLine.setLineWidth(20);
                        tMapPolyLine.setLineColor(Color.rgb(265, 125, 107));
                        tmapView.addTMapPath(tMapPolyLine);

                        distance = tMapPolyLine.getDistance() / 1000;
                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tmapView.setCenterPoint((lon_n + 128.1604225)/2, (lat_n + 37.1451356)/2);
                            tmapView.zoomToSpan((lat_n - 37.1451356), (lon_n - 128.1604225));

                            DecimalFormat decimal_distance = new DecimalFormat("##.#");//소수점첫째자리까지만
                            DecimalFormat decimal_time = new DecimalFormat("#");//소수점 없이

                            textdistance = (TextView)findViewById(R.id.textDistance);
                            texttime = (TextView)findViewById(R.id.texttime);

                            textdistance.setText("약 " + decimal_distance.format(distance) + "km");
                            texttime.setText("약 " + decimal_time.format(distance / 60) + "시간" + decimal_time.format(distance % 60) + "분");
                            //화면상에 출발지와 도착지 보이게 화면 조정
                            tmapView.zoomToSpan(lat_n - 37.1451356, lon_n - 128.1604225);
                            tmapView.setLocationPoint((nowstart.getLongitude()+endpoint.getLongitude())/2, (nowstart.getLatitude() + endpoint.getLatitude())/2);


                        } catch (Exception e) {
                            Toast.makeText(context, "주소가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1500);
            }
        });


        /**--------------------------------------검색으로 찾기---------------------------------------------------*/

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tMapData.findAllPOI(edittext.getText().toString(), 1, new TMapData.FindAllPOIListenerCallback() {
                    @Override
                    public void onFindAllPOI(ArrayList<TMapPOIItem> arrayList) {
                        try {
                            lat = arrayList.get(0).getPOIPoint().getLatitude();
                            lon = arrayList.get(0).getPOIPoint().getLongitude();

                            startpoint = new TMapPoint(lat, lon);

                            tMapData.findPathData(startpoint, endpoint, new TMapData.FindPathDataListenerCallback() {
                                @Override
                                public void onFindPathData(TMapPolyLine tMapPolyLine) {
                                    tmapView.setTMapPathIcon(start, end);

                                    tMapPolyLine.setLineWidth(20);
                                    tMapPolyLine.setLineColor(Color.rgb(265, 125, 107));
                                    tmapView.addTMapPath(tMapPolyLine);

                                    distance = tMapPolyLine.getDistance() / 1000;

                                }
                            });

                        } catch (Exception e) {
                            Toast.makeText(context, "주소를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tmapView.setCenterPoint((lon + 128.1604225)/2, (lat + 37.1451356)/2);
                            tmapView.zoomToSpan((lat - 37.1451356), (lon - 128.1604225));

                            DecimalFormat decimal_distance = new DecimalFormat("##.#");//소수점첫째자리까지만
                            DecimalFormat decimal_time = new DecimalFormat("#");//소수점 없이

                            textdistance = (TextView)findViewById(R.id.textDistance);
                            texttime = (TextView)findViewById(R.id.texttime);

                            textdistance.setText("약 " + decimal_distance.format(distance) + "km");
                            texttime.setText("약 " + decimal_time.format(distance / 60) + "시간" + decimal_time.format(distance % 60) + "분");

                            //화면상에 출발지와 도착지 보이게 화면 조정
                            tmapView.zoomToSpan(lat_n - 37.1451356, lon_n - 128.1604225);
                            tmapView.setLocationPoint((nowstart.getLongitude()+endpoint.getLongitude())/2, (nowstart.getLatitude() + endpoint.getLatitude())/2);

                        } catch (Exception e) {
                            Toast.makeText(context, "주소가 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1500);
            }
        });
    }


    @Override
    public void onLocationChange(Location location) {
        tmapView.setLocationPoint(location.getLongitude(), location.getLatitude());

        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }
}