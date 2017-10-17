package com.mavenMVC.service;

import com.mavenMVC.entity.SeeCash;

import java.util.List;

/**
 * Created by lizai on 16/5/23.
 */
public interface ISeeCashService {

    public List<SeeCash> getSeeCashByDoctorId(Long doctorId, int start, int offset, List<Long> receivedIds);

    public Integer getTotleSeeCashMoneyByDoctorId(Long doctorId);

    public SeeCash getSeeCashById(Long id);

    public void saveOrUpdateSeeCash(SeeCash seeCash);

}
