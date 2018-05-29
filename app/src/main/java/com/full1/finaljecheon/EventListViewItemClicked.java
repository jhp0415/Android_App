package com.full1.finaljecheon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by Full1 on 2017-07-31.
 */

public class EventListViewItemClicked extends AppCompatActivity{
    EventManager eventManager = MainActivity.eventManager;
    @Override
    protected void attachBaseContext(Context newBase) {
        //폰트설정정
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datail_event2);
        TextView title1 = (TextView)findViewById(R.id.textview_event_title);
        TextView title2 = (TextView)findViewById(R.id.textview_event_place);
        TextView title3 = (TextView)findViewById(R.id.textview_event_content);
        TextView title4 = (TextView)findViewById(R.id.textview_event_time);
        TextView title5 = (TextView)findViewById(R.id.textview_event_price);

        ImageView image1 = (ImageView)findViewById(R.id.img_event_detail);
        ImageView image2 = (ImageView)findViewById(R.id.img_event_detailmap);

        Intent intent = getIntent();
        String table = intent.getExtras().getString("table");
        String age = intent.getExtras().getString("age");
        int id = intent.getExtras().getInt("ID");
        int count=0;
        String packName = this.getPackageName(); // 패키지명
        switch (table)
        {

            case "공연행사":
                switch (age)
                {

                    case "어린이(가족)":{
                            title1.setText(eventManager.getInstance().getArrayList1().get(id).getName());
                            title2.setText(eventManager.getInstance().getArrayList1().get(id).getPlace());
                            title3.setText(eventManager.getInstance().getArrayList1().get(id).getContent());
                            title4.setText(eventManager.getInstance().getArrayList1().get(id).getTime());
                            title5.setText(eventManager.getInstance().getArrayList1().get(id).getExc());
                            String resName;
                            if(eventManager.getInstance().getArrayList1().get(id).getExc()==" ")
                                resName = "@drawable/"+"img_no";
                            else resName = "@drawable/"+eventManager.getInstance().getArrayList1().get(id).getExc();
                            int resID = getResources().getIdentifier(resName, "drawable", packName);
                            image1.setImageResource(resID);

                        }
                        break;
                    case "청소년":{
                            title1.setText(eventManager.getInstance().getArrayList1().get(id).getName());
                            title2.setText(eventManager.getInstance().getArrayList1().get(id).getPlace());
                            title3.setText(eventManager.getInstance().getArrayList1().get(id).getContent());
                            title4.setText(eventManager.getInstance().getArrayList1().get(id).getTime());
                            title5.setText(eventManager.getInstance().getArrayList1().get(id).getExc());
                            String resName;
                            if(eventManager.getInstance().getArrayList1().get(id).getExc()==" ")
                                resName = "@drawable/"+"img_no";
                            else resName = "@drawable/"+eventManager.getInstance().getArrayList1().get(id).getExc();
                            int resID = getResources().getIdentifier(resName, "drawable", packName);
                            image1.setImageResource(resID);

                        }
                        break;
                    case "중/장년층":{
                            title1.setText(eventManager.getInstance().getArrayList1().get(id).getName());
                            title2.setText(eventManager.getInstance().getArrayList1().get(id).getPlace());
                            title3.setText(eventManager.getInstance().getArrayList1().get(id).getContent());
                            title4.setText(eventManager.getInstance().getArrayList1().get(id).getTime());
                            title5.setText(eventManager.getInstance().getArrayList1().get(id).getExc());
                            String resName;
                            if(eventManager.getInstance().getArrayList1().get(id).getExc()==" ")
                                resName = "@drawable/"+"img_no";
                            else resName = "@drawable/"+eventManager.getInstance().getArrayList1().get(id).getExc();
                            int resID = getResources().getIdentifier(resName, "drawable", packName);
                            image1.setImageResource(resID);

                        }
                        break;
                }
                break;
            case "체험행사":
                switch (age)
                {
                    case "어린이(가족)":
                    {
                        title1.setText(eventManager.getInstance().getArrayList2().get(id).getName());
                        title2.setText(eventManager.getInstance().getArrayList2().get(id).getPlace());
                        title3.setText(eventManager.getInstance().getArrayList2().get(id).getContent());
                        title4.setText(eventManager.getInstance().getArrayList2().get(id).getTime());
                        title5.setText(eventManager.getInstance().getArrayList2().get(id).getExc());
                        String resName;
                        if(eventManager.getInstance().getArrayList2().get(id).getExc()==" ")
                            resName = "@drawable/"+"img_no";
                        else resName = "@drawable/"+eventManager.getInstance().getArrayList2().get(id).getExc();
                        int resID = getResources().getIdentifier(resName, "drawable", packName);
                        image1.setImageResource(resID);

                    }

                        break;
                    case "청소년":
                    {
                        title1.setText(eventManager.getInstance().getArrayList2().get(id).getName());
                        title2.setText(eventManager.getInstance().getArrayList2().get(id).getPlace());
                        title3.setText(eventManager.getInstance().getArrayList2().get(id).getContent());
                        title4.setText(eventManager.getInstance().getArrayList2().get(id).getTime());
                        title5.setText(eventManager.getInstance().getArrayList2().get(id).getExc());
                        String resName;
                        if(eventManager.getInstance().getArrayList2().get(id).getExc()==" ")
                            resName = "@drawable/"+"img_no";
                        else resName = "@drawable/"+eventManager.getInstance().getArrayList2().get(id).getExc();
                        int resID = getResources().getIdentifier(resName, "drawable", packName);
                        image1.setImageResource(resID);

                    }
                        break;
                    case "중/장년층":
                    {
                        title1.setText(eventManager.getInstance().getArrayList2().get(id).getName());
                        title2.setText(eventManager.getInstance().getArrayList2().get(id).getPlace());
                        title3.setText(eventManager.getInstance().getArrayList2().get(id).getContent());
                        title4.setText(eventManager.getInstance().getArrayList2().get(id).getTime());
                        title5.setText(eventManager.getInstance().getArrayList2().get(id).getExc());
                        String resName;
                        if(eventManager.getInstance().getArrayList2().get(id).getExc()==" ")
                            resName = "@drawable/"+"img_no";
                        else resName = "@drawable/"+eventManager.getInstance().getArrayList2().get(id).getExc();
                        int resID = getResources().getIdentifier(resName, "drawable", packName);
                        image1.setImageResource(resID);

                    }
                        break;
                }
                break;
            case "사전행사":
                switch (age)
                {
                    case "D-100":
                        break;
                    case "D-30":
                        break;
                }
                break;
            case "공식행사":
                switch (age)
                {
                    case "개막식":
                        if(eventManager.getInstance().getArrayList4().get(0).getTitle() == "개막식") {
                            title1.setText(eventManager.getInstance().getArrayList4().get(0).getName());
                            title2.setText(eventManager.getInstance().getArrayList4().get(0).getPlace());
                            title3.setText(eventManager.getInstance().getArrayList4().get(0).getContent());
                            title4.setText(eventManager.getInstance().getArrayList4().get(0).getTime());
                            title5.setText(eventManager.getInstance().getArrayList4().get(0).getExc());
                            String resName;
                            //if(eventManager.getInstance().getArrayList4().get(0).getExc()==" ")
                                resName = "@drawable/"+"open";
                            //else resName = "@drawable/"+eventManager.getInstance().getArrayList4().get(0).getExc();
                            int resID = getResources().getIdentifier(resName, "drawable", packName);
                            image1.setImageResource(resID);
                        }
                        break;
                    case "개장식":
                        if(eventManager.getInstance().getArrayList4().get(1).getTitle() == "개장식") {
                            title1.setText(eventManager.getInstance().getArrayList4().get(1).getName());
                            title2.setText(eventManager.getInstance().getArrayList4().get(1).getPlace());
                            title3.setText(eventManager.getInstance().getArrayList4().get(1).getContent());
                            title4.setText(eventManager.getInstance().getArrayList4().get(1).getTime());
                            title5.setText(eventManager.getInstance().getArrayList4().get(1).getExc());
                            String resName;
                            //if(eventManager.getInstance().getArrayList4().get(1).getExc()==" ")
                                resName = "@drawable/"+"opening";
                            //else resName = "@drawable/"+eventManager.getInstance().getArrayList4().get(1).getExc();
                            int resID = getResources().getIdentifier(resName, "drawable", packName);
                            image1.setImageResource(resID);
                        }
                        break;
                    case "폐막식":
                        if(eventManager.getInstance().getArrayList4().get(2).getTitle() == "폐막식") {
                            title1.setText(eventManager.getInstance().getArrayList4().get(2).getName());
                            title2.setText(eventManager.getInstance().getArrayList4().get(2).getPlace());
                            title3.setText(eventManager.getInstance().getArrayList4().get(2).getContent());
                            title4.setText(eventManager.getInstance().getArrayList4().get(2).getTime());
                            title5.setText(eventManager.getInstance().getArrayList4().get(2).getExc());
                            String resName;
                            //if(eventManager.getInstance().getArrayList4().get(2).getExc()==" ")
                                resName = "@drawable/"+"close";
                            //else resName = "@drawable/"+eventManager.getInstance().getArrayList4().get(2).getExc();
                            int resID = getResources().getIdentifier(resName, "drawable", packName);
                            image1.setImageResource(resID);
                        }
                        break;
                }
                break;
            case "특별행사":
                switch (age)
                {
                    case "힐링체험":
                        title1.setText("힐링체험Zone");
                       image1.setImageResource(R.drawable.event5_1);

                        break;
                    case "아시아테라피":
                        title1.setText("아시아테라피Zone");
                        image1.setImageResource(R.drawable.event5_2);
                        break;
                }
                break;


        }

    }
}
