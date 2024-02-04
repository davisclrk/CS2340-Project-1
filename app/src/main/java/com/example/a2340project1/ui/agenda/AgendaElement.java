package com.example.a2340project1.ui.agenda;

import com.example.a2340project1.ui.DynamicElement;

public class AgendaElement extends DynamicElement {
    private String agendaName;
    private String agendaDate;
    private String agendaTime;
    private String agendaClass;

    public AgendaElement(int mainResource, String agendaName,
                        String agendaDate, String agendaTime, String agendaClass) {
        super(mainResource);
        this.agendaName = agendaName;
        this.agendaDate = agendaDate;
        this.agendaTime = agendaTime;
        this.agendaClass = agendaClass;
    }


    public String getAgendaName() {
        return agendaName;
    }

    public void setAgendaName(String agendaName) {
        this.agendaName = agendaName;
    }

    public String getAgendaDate() {
        return agendaDate;
    }

    public void setAgendaDate(String agendaDate) {
        this.agendaDate = agendaDate;
    }

    public String getAgendaTime() {
        return agendaTime;
    }

    public void setAgendaTime(String agendaTime) {
        this.agendaTime = agendaTime;
    }

    public String getAgendaClass() {
        return agendaClass;
    }

    public void setAgendaClass(String agendaClass) {
        this.agendaClass = agendaClass;
    }
}
