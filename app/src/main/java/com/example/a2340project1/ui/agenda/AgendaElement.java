package com.example.a2340project1.ui.agenda;

import com.example.a2340project1.ui.DynamicElement;

import java.util.Comparator;

public class AgendaElement extends DynamicElement {
    private String agendaName;
    private String agendaDate;
    private int agendaMonth, agendaDay, agendaYear;
    private int agendaHour, agendaMinute;
    private String agendaClass;

    private int classIndex;

    public AgendaElement(int mainResource, String agendaName,

                         String agendaClass, String agendaDate, int agendaMonth, int agendaDay,
                         int agendaYear, int agendaHour, int agendaMinute, int classIndex) {
        super(mainResource);
        this.agendaName = agendaName;
        this.agendaMonth = agendaMonth;
        this.agendaDay = agendaDay;
        this.agendaYear = agendaYear;
        this.agendaHour = agendaHour;
        this.agendaMinute = agendaMinute;
        this.agendaClass = agendaClass;
        this.agendaDate = agendaDate;
        this.classIndex = classIndex;
    }

    public String getAgendaName() {
        return agendaName;
    }

    public void setAgendaName(String agendaName) {
        this.agendaName = agendaName;
    }

    public String getAgendaClass() {
        return agendaClass;
    }

    public void setAgendaClass(String agendaClass) {
        this.agendaClass = agendaClass;
    }

    public int getAgendaMonth() {
        return agendaMonth;
    }

    public void setAgendaMonth(int agendaMonth) {
        this.agendaMonth = agendaMonth;
    }

    public int getAgendaDay() {
        return agendaDay;
    }

    public void setAgendaDay(int agendaDay) {
        this.agendaDay = agendaDay;
    }

    public int getAgendaYear() {
        return agendaYear;
    }

    public void setAgendaYear(int agendaYear) {
        this.agendaYear = agendaYear;
    }

    public int getAgendaHour() {
        return agendaHour;
    }

    public void setAgendaHour(int agendaHour) {
        this.agendaHour = agendaHour;
    }

    public int getAgendaMinute() {
        return agendaMinute;
    }

    public void setAgendaMinute(int agendaMinute) {
        this.agendaMinute = agendaMinute;
    }

    public String getAgendaDate() {
        return agendaDate;
    }

    public void setAgendaDate(String agendaDate) {
        this.agendaDate = agendaDate;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public static Comparator<AgendaElement> dateSort = new Comparator<AgendaElement>() {
        @Override
        public int compare(AgendaElement o1, AgendaElement o2) {
            return o1.getAgendaClass().compareTo(o2.getAgendaClass());
        }
    };

}
