package com.example.a2340project1.ui.agenda;

public class ExamElement  extends AgendaElement {
    private String location;

    public ExamElement(int mainResource, String agendaName, String agendaClass, String agendaDate,
                       int agendaMonth, int agendaDay, int agendaYear, int agendaHour,
                       int agendaMinute, int classIndex, String location) {
        super(mainResource, agendaName, agendaClass, agendaDate, agendaMonth, agendaDay, agendaYear,
                agendaHour, agendaMinute, classIndex);
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
