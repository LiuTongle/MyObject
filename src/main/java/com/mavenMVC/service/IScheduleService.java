package com.mavenMVC.service;

import com.mavenMVC.entity.Schedule;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface IScheduleService {

    public void saveOrUpdateSchedule(Schedule schedule);

    public List<Schedule> getSchedulesByDoctorAndDay(Long doctorId, String day);

    public Schedule getSchedulesById(Long scheduleId);

    public void deleteSchedules(Schedule schedule);

}
