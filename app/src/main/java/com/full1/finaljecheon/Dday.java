package com.full1.finaljecheon;

import java.util.Calendar;

/**
 * Created by Full1 on 2017-07-31.
 */

//----------D-Day계산하는 클래스--------------------------------------
public class Dday{
    //D-Day 계산 하는 클래스
    public int Dday(int myear, int mmonth, int mday) {
        try {
            Calendar today = Calendar.getInstance(); //현재 오늘 날짜
            Calendar dday = Calendar.getInstance();
            //TextView Dday = (TextView)findViewById(R.id.text_dday);

            dday.set(myear,mmonth-1,mday);// D-day의 날짜를 입력합니다.

            long day = dday.getTimeInMillis()/86400000;
            // 각각 날의 시간 값을 얻어온 다음
            //( 1일의 값(86400000 = 24시간 * 60분 * 60초 * 1000(1초값) ) )

            long tday = today.getTimeInMillis()/86400000;
            long count = tday - day; // 오늘 날짜에서 dday 날짜를 빼주게 됩니다.
            return (int) count+1; // 날짜는 하루 + 시켜줘야합니다.
            //Dday.setText( "D " + (int)(count+1));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}