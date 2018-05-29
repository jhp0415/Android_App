package com.full1.finaljecheon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Full1 on 2017-07-31.
 */

public class EventAdapter extends BaseAdapter{
    public ArrayList<EventViewItem> listViewItemList = new ArrayList<EventViewItem>() ;


    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_eventlist, parent, false);
        }
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        TextView titleTextView2 = (TextView) convertView.findViewById(R.id.textView2) ;
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView1);
        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        EventViewItem listViewItem = listViewItemList.get(position);

        //이미지 셋팅 추가
        String packName = context.getPackageName(); // 패키지명
        String resName;
        if(listViewItem.getImgurl()==" ")
            resName = "@drawable/"+"img_no";
        else resName = "@drawable/"+listViewItem.getImgurl();
        int resID = context.getResources().getIdentifier(resName, "drawable", packName);
        imageView.setImageResource(resID);
        titleTextView.setText(listViewItem.getTitle());
        titleTextView2.setText(listViewItem.getPlace());
        return convertView;
    }

    public void addItem(String imgurl, String title, String place) {
        EventViewItem item = new EventViewItem();

        item.setImgurl(imgurl);
        item.setTitle(title);
        item.setPlace(place);
        listViewItemList.add(item);
    }
}
