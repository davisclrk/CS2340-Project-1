package com.example.a2340project1.ui.agenda;

import androidx.annotation.NonNull;

import com.example.a2340project1.ui.DynamicElement;

public class AgendaElement extends DynamicElement {
    private String agendaName;
    private int agendaMonth, agendaDay, agendaYear;
    private int agendaHour, agendaMinute;
    private String agendaClass;

    public AgendaElement(int mainResource, String agendaName,
                        int agendaMonth, int agendaDay, int agendaYear, int agendaHour, int agendaMinute, String agendaClass) {
        super(mainResource);
        this.agendaName = agendaName;
        this.agendaMonth = agendaMonth;
        this.agendaDay = agendaDay;
        this.agendaYear = agendaYear;
        this.agendaHour = agendaHour;
        this.agendaMinute = agendaMinute;
        this.agendaClass = agendaClass;
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
}
