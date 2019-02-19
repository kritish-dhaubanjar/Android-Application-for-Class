package com.charoniv.jin.charon.calendar;

import java.util.ArrayList;
import java.util.List;

public class KeyEvents {

    private List<String> kartik;
    private List<String> mangsir;
    private List<String> poush;
    private List<String> magh;
    private List<String> falgun;
    private List<String> chaitra;

    public KeyEvents(){
        kartik = new ArrayList<>();
        mangsir = new ArrayList<>();
        poush = new ArrayList<>();
        magh = new ArrayList<>();
        falgun = new ArrayList<>();
        chaitra = new ArrayList<>();

        kartik.add("1-7: Dashain Holidays");
        kartik.add("21-23: Tihar Holidays");
        kartik.add("25: College Day");
        kartik.add("30: Orientation Program for 1st year)");

        mangsir.add("2: Class Start (All Year 1st part)");
        mangsir.add("13-16, 20-23: Educational Tour to Pokhara(BCT/BEX III/I)");
        mangsir.add("23-27: Club Formation");
        mangsir.add("12-13: BCT/BEX IV Project Orientation");
        mangsir.add("24-25: BCT/BEX III Project Orientation");

        poush.add("15-19: BCT/BEX III/IV Proposal Defences");
        poush.add("22: First Assessment");

        magh.add("2: 1st Attendance Publication");
        magh.add("24: Technical Exhibition");
        magh.add("27: Saraswoti Puja");

        falgun.add("5-6: BCT/BEX III/IV Midterm Defence");
        falgun.add("9: End of Classes");
        falgun.add("12: Final Assessment");
        falgun.add("13: Final Attendence Publication");
        falgun.add("20: Maha Shivaratri");
        falgun.add("21-23: Closed Session");

        chaitra.add("1: Final Exam Start(All year 1st part)");
        chaitra.add("6: Holi Purnima");
    }

    public List<String> getEventList(String month){
        switch (month){
            case "Kartik":
                return kartik;
            case "Mangshir":
                return mangsir;
            case "Poush":
                return poush;
            case "Magh":
                return magh;
            case "Falgun":
                return falgun;
            case "Chaitra":
                return chaitra;
        }
        return null;
    }

}
