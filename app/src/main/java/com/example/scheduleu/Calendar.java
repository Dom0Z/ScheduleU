package com.example.scheduleu;

import java.io.Serializable;

public class Calendar implements Serializable {
    private final String Date;
    private final boolean button1;
    private final boolean button2;
    private final boolean button3;
    private final boolean button4;
    private final boolean button5;


    public Calendar(String date, boolean button1, boolean button2, boolean button3, boolean button4, boolean button5) {
        Date = date;
        this.button1 = button1;
        this.button2 = button2;
        this.button3 = button3;
        this.button4 = button4;
        this.button5 = button5;
    }
    public String getDate(){
        return Date;
    }

    public boolean isButton1() {
        return button1;
    }
    public boolean isButton2() {
        return button2;
    }
    public boolean isButton3() {
        return button3;
    }
    public boolean isButton4() {
        return button4;
    }
    public boolean isButton5() {
        return button5;
    }
}
