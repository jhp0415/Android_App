package com.full1.finaljecheon;

import java.util.ArrayList;

/**
 * Created by Full1 on 2017-07-31.
 */

public class EventManager {
    private static EventManager _Instance;
    ArrayList<EventField> arrayList1 = new ArrayList<>();
    ArrayList<EventField> arrayList2 = new ArrayList<>();
    ArrayList<EventField> arrayList3 = new ArrayList<>();
    ArrayList<EventField> arrayList4 = new ArrayList<>();
    ArrayList<EventField> arrayList5 = new ArrayList<>();
    public static EventManager get_Instance() {
        return _Instance;
    }

    public static void set_Instance(EventManager _Instance) {
        EventManager._Instance = _Instance;
    }

    public ArrayList<EventField> getArrayList1() {return arrayList1;}

    public void setArrayList1(ArrayList<EventField> arrayList1) {this.arrayList1 = arrayList1;}

    public ArrayList<EventField> getArrayList2() {return arrayList2;}

    public void setArrayList2(ArrayList<EventField> arrayList2) {this.arrayList2 = arrayList2;}

    public ArrayList<EventField> getArrayList3() {return arrayList3;}

    public void setArrayList3(ArrayList<EventField> arrayList3) {this.arrayList3 = arrayList3;}

    public ArrayList<EventField> getArrayList4() {return arrayList4;}

    public void setArrayList4(ArrayList<EventField> arrayList4) {this.arrayList4 = arrayList4;}

    public ArrayList<EventField> getArrayList5() {return arrayList5;}

    public void setArrayList5(ArrayList<EventField> arrayList5) {this.arrayList5 = arrayList5;}

    public static EventManager getInstance() {
        if (_Instance == null)
            _Instance = new EventManager();
        return _Instance;
    }
}
