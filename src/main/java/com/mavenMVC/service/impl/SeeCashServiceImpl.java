package com.mavenMVC.service.impl;

import com.mavenMVC.dao.ISeeCashDao;
import com.mavenMVC.entity.SeeCash;
import com.mavenMVC.service.ISeeCashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizai on 16/5/24.
 */
@Service("SeeCashServiceImpl")
@Transactional
public class SeeCashServiceImpl implements ISeeCashService{

    @Autowired
    private ISeeCashDao seeCashDao;

    @Override
    public List<SeeCash> getSeeCashByDoctorId(Long doctorId, int start, int offset, List<Long> receivedIds) {
        List<SeeCash> seeCashes = seeCashDao.getSeeCashByDoctorId(doctorId, start, offset, receivedIds);
        if(seeCashes == null){
            return new ArrayList<SeeCash>();
        }
        return seeCashes;
    }

    @Override
    public Integer getTotleSeeCashMoneyByDoctorId(Long doctorId) {
        return seeCashDao.getTotleSeeCashMoneyByDoctorId(doctorId);
    }

    @Override
    public SeeCash getSeeCashById(Long id) {
        return seeCashDao.getSeeCashById(id);
    }

    @Override
    public void saveOrUpdateSeeCash(SeeCash seeCash) {
        seeCashDao.saveOrUpdateSeeCash(seeCash);
    }
}
