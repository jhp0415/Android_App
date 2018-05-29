package com.full1.finaljecheon;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.kakao.kakaolink.KakaoLink;
import com.kakao.kakaolink.KakaoTalkLinkMessageBuilder;
import com.kakao.util.KakaoParameterException;
import com.tsengvn.typekit.TypekitContextWrapper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static DataManager tourmanager;
    public static EventManager eventManager;
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    Fragment fragment4;
    Fragment fragment5;
    Context context;
    static ArrayList<EventField> fieldArrayList1;
    static ArrayList<EventField> fieldArrayList2;
    static ArrayList<EventField> fieldArrayList3;
    static ArrayList<EventField> fieldArrayList4;
    static ArrayList<EventField> fieldArrayList5 ;
    private BackPressCloseHandler backPressCloseHandler;
    @Override
    protected void attachBaseContext(Context newBase) {
        //폰트설정정
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //스플래시 액티비티 실행
        context = this;
        //startActivity(new Intent(context, Splash.class));

        backPressCloseHandler = new BackPressCloseHandler(this);
        //액션바 설정
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //----------내장디비 읽어오기------------------------------------------------
        ReadDataBase("tour");
        ReadDataBase("motel");
        ReadDataBase("food");
        WriteEventData();
//-------------------------------------------------------------------------------------
        //액션바 기본 타이틀 보여지지 않게
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);


        //Fragment : 탭 클릭시 보여줄 화면들
        fragment1 = new Fragment1Home();
        fragment2 = new Fragment2Ticket();
        fragment3 = new Fragment3Event();
        fragment4 = new Fragment4Route();
        fragment5 = new Fragment5Tour();


        //기본으로 첫번째 Fragment를 보여지도록 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        //TabLayout에 Tab 3개 추가
        TabLayout tabs = (TabLayout)findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Home"));
        tabs.addTab(tabs.newTab().setText("Ticket"));
        tabs.addTab(tabs.newTab().setText("Event"));
        tabs.addTab(tabs.newTab().setText("Route"));
        tabs.addTab(tabs.newTab().setText("Tour"));
//탭 선택리스너
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            //탭선택시
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if(position==0){
                    selected = fragment1;
                }else if(position==1){
                    selected = fragment2;
                }else if(position==2){
                    selected = fragment3;
                } else if(position==3){
                    selected = fragment4;
                }else if(position==4){
                    selected = fragment5;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();

            }
            //탭선택해제시
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            //선탭된탭을 다시 클릭시
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                Fragment selected = null;
                if(position==0){
                    selected = fragment1;
                }else if(position==1){
                    selected = fragment2;
                }else if(position==2){
                    selected = fragment3;
                } else if(position==3){
                    selected = fragment4;
                }else if(position==4){
                    selected = fragment5;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selected).commit();
            }
        });

    }
    //----------카카오톡 공유 버튼 클릭 이벤트--------------------------------------
    public void kakaolink_click(MenuItem item) {
        try{
            final KakaoLink kakaoLink = KakaoLink.getKakaoLink(this);
            final KakaoTalkLinkMessageBuilder kakaoBuilder = kakaoLink.createKakaoTalkLinkMessageBuilder();

            //메시지 추가
            kakaoBuilder.addText("제천엑스포 홍보앱 설치하기");

            //이미지 가로*세로 사이즈는 80px이상, 이미지 용향은 500kb이하
            String url = "http://www.korean-medicine-expo.kr/home/imgs/contents/symbol02.jpg";
            kakaoBuilder.addImage(url,900,900);

            //앱 실행 버튼 추가
            kakaoBuilder.addAppButton("앱 실행 및 다운로드");

            //메시지 발송송
            kakaoLink.sendMessage(kakaoBuilder, this);
        } catch (KakaoParameterException e){
            e.printStackTrace();
        }

    }


    /*다른 클래스 또는 함수들*/
    //---------어플을 시작하는 동시에 내장DB에 있는 모든 데이터들을 읽어들인다.
    public void ReadDataBase(String table) {
        DatabaseHelpers myDB = new DatabaseHelpers(this);
        Cursor c = myDB.getData(table);
        c.moveToNext();
        ArrayList<DataField> fieldArray = new ArrayList<>();
        do {
            fieldArray.add(new DataField(c.getString(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4),
                    c.getString(5), c.getString(6), c.getString(7), c.getString(8)));
        } while (c.moveToNext());
        c.close();
        myDB.close();
        switch (table)
        {
            case "tour":
                tourmanager.getInstance().setArrayList1(fieldArray);
                break;
            case "motel":
                tourmanager.getInstance().setArrayList2(fieldArray);
                break;
            case "food":
                tourmanager.getInstance().setArrayList3(fieldArray);
                break;
        }

    }
    //----------------------------------------------------------------------------------
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            backPressCloseHandler.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //네비게이션 드러어 클릭시 페이지 엑티비티 활성화
    FragmentTransaction transaction;
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        transaction = getSupportFragmentManager().beginTransaction();
        item.getItemId();
        if ( item.getItemId() == R.id.menu_home) {
            // Handle the camera action
            transaction.replace(R.id.container, fragment1);
        } else if ( item.getItemId() == R.id.menu_ticket) {
            transaction.replace(R.id.container, fragment2);

        } else if ( item.getItemId() == R.id.menu_event) {
            transaction.replace(R.id.container, fragment3);

        } else if ( item.getItemId() == R.id.menu_route) {
            transaction.replace(R.id.container, fragment4);

        } else if ( item.getItemId() == R.id.menu_tour) {
            transaction.replace(R.id.container, fragment5);
        }else if ( item.getItemId() == R.id.menu_setting_1) {
            String tel = "tel:"+ "043-641-7054";
            startActivity(new Intent("android.intent.action.DIAL", Uri.parse(tel)));
            //transaction.replace(R.id.container, fragment5);
        }else if ( item.getItemId() == R.id.menu_setting_2) {
            Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.korean-medicine-expo.kr/home/main.php#none"));
            startActivity(intent2);
            //transaction.replace(R.id.container, fragment5);
        }

        //transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }

    //행사 데이터 리스트 만들기
    public void WriteEventData() {
        fieldArrayList1 = new ArrayList<>();
        fieldArrayList2 = new ArrayList<>();
        fieldArrayList3 = new ArrayList<>();
        fieldArrayList4 = new ArrayList<>();
        fieldArrayList5 = new ArrayList<>();

//        switch (table)
//        {
//            case "공연행사":
                fieldArrayList1.add(new EventField("공연행사", "어린이(가족)", "한방 마술쇼", "엑스포 보조무대", "일반 마술에 한방소품이 가미된 신개념 마술쇼", "기간 중 총 9회", "event1_1"));
                fieldArrayList1.add(new EventField("공연행사", "어린이(가족)", "어린이 한방 인형극 (한방 가족극)", "엑스포 보조무대", "한방바이오, 제천약초 등을 스토리텔링한 역할극", "기간 중 총 8회", "event1_2"));
                fieldArrayList1.add(new EventField("공연행사", "어린이(가족)", "한방 마당극", " ", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "어린이(가족)", "캐릭터 퍼포먼스", "", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "어린이(가족)", "한방버블-벌룬쇼쇼", "엑스 주무대", "어린이 대상으로 인기있는 버블공연과 풍선아트", "기간 중 총 3회", "event1_5"));
                fieldArrayList1.add(new EventField("공연행사", "어린이(가족)", "전국 치어리딩 페스티벌", " ", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "청소년", "도전! 한방 골든벨", "엑스포 보조무대", "제천과 한방바이오에 대한 정보전달을 위한 퀴즈 서바이벌", "기간 중 총 16회", "event1_7"));
                fieldArrayList1.add(new EventField("공연행사", "청소년", "무대를 빌려드립니다", " ", " ", " ", "event1_8"));
                fieldArrayList1.add(new EventField("공연행사", "청소년", "나도 한방 스타", "엑스포 주무대/보조무대", "아마추어 및 인디 공연팀을 만나는 게릴라 버스킹공연", "기간 중 총 9회", "event1_9"));
                fieldArrayList1.add(new EventField("공연행사", "청소년", "10.9 한글날 특별이벤트", " ", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "청소년", "넌버별 퍼포먼스", " ", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "청소년", "국가대표 태권도 시범", " ", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "중/장년층", "제천예술마당", " ", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "중/장년층", "충북 예술단체 공연", " ", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "중/장년층", "박달 콘서트", "엑스포 주무대", "박달가요제 역대 수상자들의 공연", "기간 중 총 2회", "event1_15"));
                fieldArrayList1.add(new EventField("공연행사", "중/장년층", "월드 댄스와 춤의 향연", " ", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "중/장년층", "전통혼례식 공연", " ", " ", " ", " "));
                fieldArrayList1.add(new EventField("공연행사", "중/장년층", "남사당패 줄타기 공연", " ", " ", " ", " "));
                eventManager.getInstance().setArrayList1(fieldArrayList1);
//                break;
//            case "체험행사":
                fieldArrayList2.add(new EventField("체험행사", "어린이(가족)", "<유료 체험> 한방 초콜릿 만들기", "체험이벤트장", "초콜릿에 한방약재를 혼합하여 건강한 초콜릿 만들기", "기간 중 상시 운영", "event2_1"));
                fieldArrayList2.add(new EventField("체험행사", "어린이(가족)", "한방약초 꽃 페이스페인팅 (약초꽃 페이스페인팅)", "체험이벤트장", "11대 약초 설명과 예쁜 페이스 페인팅 체험", "기간 중 상시 운영", "event2_2"));
                fieldArrayList2.add(new EventField("체험행사", "어린이(가족)", "DNA 팔찌 만들기", " ", " ", " ", " "));
                fieldArrayList2.add(new EventField("체험행사", "어린이(가족)", "블록 로봇 체험", " ", " ", " ", " "));
                fieldArrayList2.add(new EventField("체험행사", "어린이(가족)", "약초 화분 만들기", " ", " ", " ", " "));
                fieldArrayList2.add(new EventField("체험행사", "어린이(가족)", "에어바운스 놀이터", "엑스포 어린이공원", "공기주입식 놀이기구에 올라타 뛰어노는 놀이터", "기간 중 상시 운영", "event2_6"));
                fieldArrayList2.add(new EventField("체험행사", "청소년", "한복체험", "체험이벤트장", "한복의 미와 멋을 체험하고 기념활영", "기간 중 상시 운영", "event2_7"));
                fieldArrayList2.add(new EventField("체험행사", "청소년", "전통 문화 체험", "체험이벤트장", "투호, 널뛰기, 제기, 팔씨름 등 전통놀이 촬영", "기간 중 상시 운영", "event2_8"));
                fieldArrayList2.add(new EventField("체험행사", "청소년", "내의원 체험", " ", " ", " ", " "));
                fieldArrayList2.add(new EventField("체험행사", "청소년", "<유료 체험> 한방약초 건강환 만들기", "체험이벤트장", "한방약초를 활용하여 변비, 비만 등에 효과적인 건강환 만들기", "기간 중 상시 운영", "event2_10"));
                fieldArrayList2.add(new EventField("체험행사", "청소년", "사랑의 캘리그라피", " ", " ", " ", " "));
                fieldArrayList2.add(new EventField("체험행사", "청소년", "약초 비누/향기주머니 체험", " ", " ", " ", "event2_12"));
                fieldArrayList2.add(new EventField("체험행사", "중/장년층", "공예품만들기", " ", " ", " ", " "));
                fieldArrayList2.add(new EventField("체험행사", "중/장년층", "고무신 민속화 그리기", " ", " ", " ", " "));
                fieldArrayList2.add(new EventField("체험행사", "중/장년층", "한방 스팀케어", " ", " ", " ", "event2_15"));
                fieldArrayList2.add(new EventField("체험행사", "중/장년층", "힐링 몸펴기 운동", "체험이벤트장", "불균형적 몸을 바로잡고 굳어있는 몸을 펴주는 운동", "기간 중 상시 운영", "event2_16"));
                fieldArrayList2.add(new EventField("체험행사", "중/장년층", "소원 약초 터널", " ", " ", " ", " "));
                fieldArrayList2.add(new EventField("체험행사", "중/장년층", "한방 천연 염색", " ", " ", " ", " "));
                eventManager.getInstance().setArrayList2(fieldArrayList2);
//                break;
//            case "사전행사":
                //eventManager.getInstance().setArrayList3(fieldArrayList3);
//                break;
//            case "공식행사":
                fieldArrayList4.add(new EventField("공식행사", "개막식", "개막식", "엑스포 메인무대", "주제 : 한방바이오산업의 심장! 제천에서 피어난 생명의 꽃" +
                "식전행사 (27분) : 한방바이오 세상으로의 초대공연 (1팀), 내빈착석 및 소개" +
                "공식행사 (33분) : 홍보영상, 개식, 경과보고, 환영사, 축사, 개막 세리머니 (전국 SBS 지역민방 생중계)" +
                "식후행사 (30분) : CJB 방송프로그램 축하공연 (전국 SBS 지역민방 생중계)", "2017년 9월 22일 14시 30분 ~ 16시 00분 (총 90분)", "총 1000명 (초청VIP, 엑스포 관계자/일반 관람객)"));
                fieldArrayList4.add(new EventField("공식행사", "개장식", "개장식", "엑스포 메인게이트", "주제 : 제천에서 한방바이오산업의 답을 찾다" +
                        "식전행사 (10분) : 스토리텔링 형식 길놀이 마당극 [이공기 선생과 약령시장 사람들]" +
                        "공식행사 (25분) : 참석내빈 소개, 개회사, 환영사, 개장 세리머니 (테이프 컷팅)" +
                        "식후행사 (55분) : 참석내빈 엑스포장 투어", "2017년 9월 22일 오전 8시 30분 ~ 10시 (총 90분)", "총 200명 (초청VIP, 엑스포 관계자/일반 관람객))"));

                fieldArrayList4.add(new EventField("공식행사", "폐막식", "폐막식", "엑스포 메인무대 및 만찬회장(추후협의)", "주제 : 세계를 향한 도전 또 다른 비상을 꿈꾸다" +
                        "입장식 (10분) : 조직위 및 자원봉사자, 관계자 입장식" +
                        "공식행사 (20분) : 경과보고, VIP스피치, 조직위와 어린이합창단 합창, 폐회선언" +
                        "식후행사 (60분) : 폐막식 및 종사자 파티", "2017년 10월 10일 17시 00분 ~", "총 1000명 (초청VIP, 엑스포 관계자/일반 관람객))"));
                eventManager.getInstance().setArrayList4(fieldArrayList4);
//                break;
//            case "특별행사":
                // fieldArrayList5.add(new EventField("특별행사", "힐링체험관", " ", " ", " ", " ", " "));
               // eventManager.getInstance().setArrayList5(fieldArrayList5);
//                break;


    }

    //티켓 이미지를 눌렀을때 사이트 연결하기
    public void Ticket_onClick(View view) {
        Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ticket.hanatour.com/Pages/Perf/Detail/Detail.aspx?IdPerf=32336"));
        startActivity(intent2);
    }

    //행사 이미지 클릭시 페이지 전환
    public void ImageonClick(View view)
    {
        Intent intent = new Intent(context, EventClicked.class);
        Intent intent2 = new Intent(context, EventListViewItemClicked.class);

        switch (view.getId())
        {
            case R.id.scrollview_img1: {
                intent.putExtra("table", "공연행사");
                intent.putExtra("age", "어린이(가족)");
                startActivity(intent);
            }
                break;
            case R.id.scrollview_img2: {
                intent.putExtra("table", "공연행사");
                intent.putExtra("age", "청소년");
                startActivity(intent);
            }
                break;
            case R.id.scrollview_img3: {
                intent.putExtra("table", "공연행사");
                intent.putExtra("age", "중/장년층");
                startActivity(intent);
            }
                break;
            case R.id.scrollview_img4: {
                intent.putExtra("table", "체험행사");
                intent.putExtra("age", "어린이(가족)");
                startActivity(intent);
            }
            break;
            case R.id.scrollview_img5: {
                intent.putExtra("table", "체험행사");
                intent.putExtra("age", "청소년");
                startActivity(intent);
            }
            break;
            case R.id.scrollview_img6: {
                intent.putExtra("table", "체험행사");
                intent.putExtra("age", "중/장년층");
                startActivity(intent);
            }
            break;
            case R.id.scrollview_img7: {
                intent2.putExtra("table", "공식행사");
                intent2.putExtra("age", "개막식");
                startActivity(intent2);
            }
            break;
            case R.id.scrollview_img8: {
                intent2.putExtra("table", "공식행사");
                intent2.putExtra("age", "개장식");
                startActivity(intent2);
            }
            break;
            case R.id.scrollview_img9: {
                intent2.putExtra("table", "공식행사");
                intent2.putExtra("age", "폐막식");
                startActivity(intent2);
            }
            break;

            case R.id.img_event5_1: {
                intent2.putExtra("table", "특별행사");
                intent2.putExtra("age", "힐링체험");
                startActivity(intent2);
            }
            break;
            case R.id.img_event5_2: {
                intent2.putExtra("table", "특별행사");
                intent2.putExtra("age", "아시아테라피");
                startActivity(intent2);
            }
            break;



        }


    }

    public void EvnetMap_onClick(View view){
        Intent intent = new Intent(this, ShowEventMap.class);
        startActivity(intent);
    }

    public void GotoPage(View view)
    {
        Intent intent = new Intent(context, EventClicked.class);
        Intent intent2 = new Intent(context, EventListViewItemClicked.class);
        switch (view.getId())
        {
            case R.id.lay_home_event1:
                intent.putExtra("table", "공연행사");
                intent.putExtra("age", "어린이(가족)");
                startActivity(intent);
                break;
            case R.id.lay_home_event2:
                intent.putExtra("table", "체험행사");
                intent.putExtra("age", "어린이(가족)");
                startActivity(intent);
                break;
            case R.id.lay_home_event3:
//                intent2.putExtra("table", "특별행사");
//                startActivity(intent2);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                break;
            case R.id.lay_home_event4:
//                intent2.putExtra("table", "공식행사");
//                startActivity(intent2);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment3).commit();
                break;
            case R.id.lay_tour_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment5).commit();
                break;
            case R.id.lay_tour_2:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment5).commit();
                break;
            case R.id.lay_tour_3:
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment5).commit();
                break;
        }
    }

}
