package com.full1.finaljecheon;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Full1 on 2017-07-31.
 */

public class Fragment5Tour extends android.support.v4.app.Fragment {
    TabHost tabHost2;
    TabHost.TabSpec ts1;
    TabHost.TabSpec ts2;
    TabHost.TabSpec ts3;
    ListView listview1;
    ListView listview2;
    ListView listview3;
    ListViewAdapter adapter1;
    ListViewAdapter adapter2;
    ListViewAdapter adapter3;

    ArrayList<ListViewItem> arrayList1;
    ArrayList<ListViewItem> arrayList2;
    ArrayList<ListViewItem> arrayList3;
    DataManager tourmanager = MainActivity.tourmanager;
    View view;
    Context context;
    android.support.v4.app.Fragment fragment1;
    android.support.v4.app.Fragment fragment2;
    android.support.v4.app.Fragment fragment3;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_tour, container, false);
        context = getActivity();
        SetListView();
        return view;
    }
    public void SetListView()
    {
        adapter1 = new ListViewAdapter();
        adapter2 = new ListViewAdapter();
        adapter3 = new ListViewAdapter();

        arrayList1 = adapter1.listViewItemList;
        arrayList2 = adapter2.listViewItemList;
        arrayList3 = adapter3.listViewItemList;

        tabHost2 = (TabHost)view. findViewById(R.id.tabHost2);
        tabHost2.setup();

        CreateTab1();
        try {
            CreateTab2();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CreateTab3();
    }

    public void CreateTab1()
    {
        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        ts1 = tabHost2.newTabSpec("Tab Spec 1");
        ts1.setContent(R.id.lay_tour);
        ts1.setIndicator("Tour");
        tabHost2.addTab(ts1);

        //-------------------------------------------------------------------------------------
        // activity_main.xml에서 정의한 LinearLayout 객체 할당
        LinearLayout inflatedLayout = (LinearLayout)view.findViewById(R.id.lay_tour);
        // LayoutInflater 객체 생성
        LayoutInflater inflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // Inflated_Layout.xml로 구성한 레이아웃을 inflatedLayout 영역으로 확장
        inflater.inflate(R.layout.listview_layout_tour, inflatedLayout);

        //리스트뷰 참조 및 Adapter달기
        listview1 = (ListView) view.findViewById(R.id.list_tour);
        listview1.setAdapter(adapter1);

        for(int i = 0; i < tourmanager.getInstance().getArrayList1().size();i++)
        {
            adapter1.addItem(tourmanager.getInstance().getArrayList1().get(i).getImage_url(),
                    tourmanager.getInstance().getArrayList1().get(i).getName(), tourmanager.getInstance().getArrayList1().get(i).getAddress(),
                    tourmanager.getInstance().getArrayList1().get(i).getPhone());
        }

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ListViewItemClicked.class);

                intent.putExtra("ID", position);
                intent.putExtra("TABLE", "tour");
                startActivity(intent);
            }
        });

    }

    public void CreateTab2() throws ExecutionException, InterruptedException {
        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        ts2 = tabHost2.newTabSpec("Tab Spec 2");
        ts2.setContent(R.id.lay_motel);
        ts2.setIndicator("Motel");
        tabHost2.addTab(ts2);

        // activity_main.xml에서 정의한 LinearLayout 객체 할당
        LinearLayout inflatedLayout = (LinearLayout)view.findViewById(R.id.lay_motel);
        LayoutInflater inflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listview_layout_motel, inflatedLayout);

        //리스트뷰 참조 및 Adapter달기
        listview2 = (ListView) view.findViewById(R.id.list_motel);
        listview2.setAdapter(adapter2);


        for(int i = 0; i < tourmanager.getInstance().getArrayList2().size();i++)
        {
            adapter2.addItem(tourmanager.getInstance().getArrayList2().get(i).getImage_url(),
                    tourmanager.getInstance().getArrayList2().get(i).getName(), tourmanager.getInstance().getArrayList2().get(i).getAddress(),
                    tourmanager.getInstance().getArrayList2().get(i).getPhone());
        }

        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ListViewItemClicked.class);

                intent.putExtra("ID", position);
                intent.putExtra("TABLE", "motel");
                startActivity(intent);
            }
        });

    }
    public void CreateTab3()
    {
        //세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        ts3 = tabHost2.newTabSpec("Tab Spec 3");
        ts3.setContent(R.id.lay_food);
        ts3.setIndicator("Food");
        tabHost2.addTab(ts3);

        // activity_main.xml에서 정의한 LinearLayout 객체 할당
        LinearLayout inflatedLayout = (LinearLayout)view.findViewById(R.id.lay_food);
        LayoutInflater inflater =  (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.listview_layout_food, inflatedLayout);

        //리스트뷰 참조 및 Adapter달기
        listview3 = (ListView)view. findViewById(R.id.list_food);
        listview3.setAdapter(adapter3);

        DatabaseHelpers myDB = new DatabaseHelpers(context);
        Cursor c = myDB.getData("food");

        for(int i = 0; i < tourmanager.getInstance().getArrayList3().size();i++)
        {
            adapter3.addItem(tourmanager.getInstance().getArrayList3().get(i).getImage_url(),
                    tourmanager.getInstance().getArrayList3().get(i).getName(), tourmanager.getInstance().getArrayList3().get(i).getAddress(),
                    tourmanager.getInstance().getArrayList3().get(i).getPhone());
        }

        listview3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(context, ListViewItemClicked.class);

                intent.putExtra("ID", position);
                intent.putExtra("TABLE", "food");
                startActivity(intent);
            }
        });
    }


}
