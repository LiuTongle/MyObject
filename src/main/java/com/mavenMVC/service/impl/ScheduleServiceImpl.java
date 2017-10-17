package com.mavenMVC.service.impl;

import com.mavenMVC.dao.IDoctorDao;
import com.mavenMVC.dao.IScheduleDao;
import com.mavenMVC.entity.Schedule;
import com.mavenMVC.service.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
@Service("ScheduleServiceImpl")
@Transactional
public class ScheduleServiceImpl implements IScheduleService {

    @Autowired
    private IDoctorDao doctorDao;

    @Autowired
    private IScheduleDao scheduleDao;


    @Override
    public void saveOrUpdateSchedule(Schedule schedule) {
        scheduleDao.saveOrUpdateSchedule(schedule);
    }

    @Override
    public List<Schedule> getSchedulesByDoctorAndDay(Long doctorId, String day) {
        return scheduleDao.getSchedulesByDoctorIdAndDay(doctorId,day);
    }

    @Override
    public Schedule getSchedulesById(Long scheduleId) {
        return scheduleDao.getScheduleById(scheduleId);
    }

    @Override
    public void deleteSchedules(Schedule schedule) {
        scheduleDao.deleteSchedule(schedule);
    }
}
