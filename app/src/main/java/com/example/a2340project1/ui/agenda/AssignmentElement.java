package com.example.a2340project1.ui.agenda;
/**
 * Stores data about assignment elements dynamically added to the agenda fragment.
 * Inherits from the AgendaElement class
 *
 * @author davisclrk
 * @version 1.0
 */
public class AssignmentElement extends AgendaElement {

    public AssignmentElement(int mainResource, String agendaName, String agendaClass, String agendaDate,

                             int agendaMonth, int agendaDay, int agendaYear, int agendaHour, int agendaMinute,
                             int classIndex) {
        super(mainResource, agendaName, agendaClass, agendaDate, agendaMonth, agendaDay, agendaYear,
                agendaHour, agendaMinute, classIndex);
    }
}
