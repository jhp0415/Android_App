package com.full1.finaljecheon;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

/**
 * Created by Full1 on 2017-07-31.
 */

public class EventClicked extends AppCompatActivity{
    ListView listview1;
    ListView listview2;
    ListView listview3;
    EventAdapter adapter1;
    EventAdapter adapter2;
    EventAdapter adapter3;
    ArrayList<EventViewItem> arrayList1 ;
    ArrayList<EventViewItem> arrayList2;
    ArrayList<EventViewItem> arrayList3;
    ArrayList<EventViewItem> arrayList4;
    ArrayList<EventViewItem> arrayList5;
    EventManager eventManager = MainActivity.eventManager;
    Intent intent;
    @Override
    protected void attachBaseContext(Context newBase) {
        //폰트설정정
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_event1);
        intent = getIntent();
        String table = intent.getStringExtra("table");
        String age = intent.getStringExtra("age");
        String id = intent.getStringExtra("ID");
////////////////////////////////////////////////////////////////////////////////////////////////
        adapter1 = new EventAdapter();
        adapter2 = new EventAdapter();
        adapter3 = new EventAdapter();

        arrayList1 = adapter1.listViewItemList;
        arrayList2 = adapter2.listViewItemList;
        arrayList3 = adapter3.listViewItemList;

        listview1 = (ListView)findViewById(R.id.list_event);
        listview1.setAdapter(adapter1);
//        for(int i = 0; i < eventManager.getInstance().getArrayList1().size();i++)
//        {
//            adapter1.addItem(eventManager.getInstance().getArrayList1().get(i).getExc(),
//                    eventManager.getInstance().getArrayList1().get(i).getName());
//        }
//        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getApplicationContext(), EventListViewItemClicked.class);
//                intent.putExtra("ID", position);
//                intent.putExtra("TABLE", "tour");
//                startActivity(intent);
//            }
//        });

        switch (table)
        {
            case "공연행사":
                switch (age)
                {
                    case "어린이(가족)": {

                        for (int i = 0; i < 6; i++) {
                            adapter1.addItem(eventManager.getInstance().getArrayList1().get(i).getExc(), eventManager.getInstance().getArrayList1().get(i).getName(), eventManager.getInstance().getArrayList1().get(i).getPlace());
                        }
                        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), EventListViewItemClicked.class);
                                intent.putExtra("ID", position);
                                intent.putExtra("table", "공연행사");
                                intent.putExtra("age", "어린이(가족)");
                                startActivity(intent);
                            }
                        });
                    }
                        break;
                    case "청소년":
                        for(int i = 6; i <12;i++)
                        {
                            if(eventManager.getInstance().getArrayList1().get(i).getTitle() == "청소년") {
                                adapter1.addItem(eventManager.getInstance().getArrayList1().get(i).getExc(), eventManager.getInstance().getArrayList1().get(i).getName(), eventManager.getInstance().getArrayList1().get(i).getPlace());
                            }
                        }
                        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), EventListViewItemClicked.class);
                                intent.putExtra("ID", position+6);
                                intent.putExtra("table", "공연행사");
                                intent.putExtra("age", "청소년");
                                startActivity(intent);
                            }
                        });
                        break;
                    case "중/장년층":
                        for(int i = 12; i < 18;i++)
                        {
                            if(eventManager.getInstance().getArrayList1().get(i).getTitle() == "중/장년층") {
                                adapter1.addItem(eventManager.getInstance().getArrayList1().get(i).getExc(), eventManager.getInstance().getArrayList1().get(i).getName(), eventManager.getInstance().getArrayList1().get(i).getPlace());
                            }
                        }
                        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), EventListViewItemClicked.class);
                                intent.putExtra("ID", position+12);
                                intent.putExtra("table", "공연행사");
                                intent.putExtra("age", "중/장년층");
                                startActivity(intent);
                            }
                        });
                        break;
                }
                break;
            case "체험행사":
                switch (age)
                {
                    case "어린이(가족)":
                        for(int i = 0; i < 6;i++)
                        {
                            if(eventManager.getInstance().getArrayList2().get(i).getTitle() == "어린이(가족)") {
                                adapter1.addItem(eventManager.getInstance().getArrayList2().get(i).getExc(), eventManager.getInstance().getArrayList2().get(i).getName(), eventManager.getInstance().getArrayList2().get(i).getPlace());
                            }
                        }
                        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), EventListViewItemClicked.class);
                                intent.putExtra("ID", position);
                                intent.putExtra("table", "체험행사");
                                intent.putExtra("age", "어린이(가족)");
                                startActivity(intent);
                            }
                        });
                        break;
                    case "청소년":
                        for(int i = 6; i < 12;i++)
                        {
                            if(eventManager.getInstance().getArrayList2().get(i).getTitle() == "청소년") {
                                adapter1.addItem(eventManager.getInstance().getArrayList2().get(i).getExc(), eventManager.getInstance().getArrayList2().get(i).getName(), eventManager.getInstance().getArrayList2().get(i).getPlace());
                            }
                        }
                        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), EventListViewItemClicked.class);
                                intent.putExtra("ID", position+6);
                                intent.putExtra("table", "체험행사");
                                intent.putExtra("age", "청소년");
                                startActivity(intent);
                            }
                        });
                        break;
                    case "중/장년층":
                        for(int i = 12; i < 18;i++)
                        {
                            if(eventManager.getInstance().getArrayList2().get(i).getTitle() == "중/장년층") {
                                adapter1.addItem(eventManager.getInstance().getArrayList2().get(i).getExc(), eventManager.getInstance().getArrayList2().get(i).getName(), eventManager.getInstance().getArrayList2().get(i).getPlace());
                            }
                        }
                        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getApplicationContext(), EventListViewItemClicked.class);
                                intent.putExtra("ID", position+12);
                                intent.putExtra("table", "체험행사");
                                intent.putExtra("age", "중/장년층");
                                startActivity(intent);
                            }
                        });
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
                        break;
                    case "개장식":
                        break;
                    case "폐막식":
                        break;
                }
                break;
            case "특별행사":
                switch (age)
                {
                    case "힐링체험":
                        break;
                    case "아시아테라피":
                        break;
                }
                break;


        }

    }
}
