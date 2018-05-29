package com.full1.finaljecheon;

import java.util.ArrayList;

/**
 * Created by Full1 on 2017-07-26.
 */
//데이터 저장 필드들의 리스트 집합
public class DataManager {
    private static DataManager _Instance;
    int count;
    ArrayList<DataField> arrayList1 = new ArrayList<>();
    ArrayList<DataField> arrayList2 = new ArrayList<>();
    ArrayList<DataField> arrayList3 = new ArrayList<>();

    public static DataManager get_Instance() {
        return _Instance;
    }

    public static void set_Instance(DataManager _Instance) {
        DataManager._Instance = _Instance;
    }

    public ArrayList<DataField> getArrayList1() {
        return arrayList1;
    }

    public void setArrayList1(ArrayList<DataField> arrayList1) {
        this.arrayList1 = arrayList1;
    }

    public ArrayList<DataField> getArrayList2() {
        return arrayList2;
    }

    public void setArrayList2(ArrayList<DataField> arrayList2) {
        this.arrayList2 = arrayList2;
    }

    public ArrayList<DataField> getArrayList3() {
        return arrayList3;
    }

    public void setArrayList3(ArrayList<DataField> arrayList3) {
        this.arrayList3 = arrayList3;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static DataManager getInstance() {
        if (_Instance == null)
            _Instance = new DataManager();
        return _Instance;
    }
}