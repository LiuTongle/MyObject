package com.mavenMVC.dao;

import com.mavenMVC.entity.Schedule;

import java.util.List;

/**
 * Created by lizai on 16/5/23.
 */
public interface IScheduleDao {

    public void saveOrUpdateSchedule(Schedule schedule);

    public List<Schedule> getSchedulesByDoctorIdAndDay(Long doctorId, String day);

    public Schedule getScheduleById(Long scheduleId);

    public void deleteSchedule(Schedule schedule);

}
