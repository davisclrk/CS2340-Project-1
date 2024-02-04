package com.example.a2340project1.ui.agenda;

public class ExamElement  extends AgendaElement {
    private String location;

    public ExamElement(int mainResource, String agendaName, int agendaMonth, int agendaDay, int agendaYear, int agendaHour, int agendaMinute, String agendaClass, String location) {
        super(mainResource, agendaName, agendaMonth, agendaDay, agendaYear, agendaHour, agendaMinute, agendaClass);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
