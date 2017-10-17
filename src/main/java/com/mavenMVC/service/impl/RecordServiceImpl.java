package com.mavenMVC.service.impl;

import com.mavenMVC.dao.IRecordDao;
import com.mavenMVC.entity.Record;
import com.mavenMVC.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizai on 16/5/9.
 */

@Service("RecordServiceImpl")
@Transactional
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private IRecordDao iRecordDao;

    @Override
    public Record getRecordById(Long id) {
        return iRecordDao.getRecordById(id);
    }

    @Override
    public List<Record> getAllRecordsByPatientId(Long patientId,int recordType) {
        List<Record> records = iRecordDao.getAllRecordsByPatientId(patientId, recordType);
        if(records!=null){
            return records;
        }
        return new ArrayList<Record>();
    }

    @Override
    public Record getLastRecordByPatientId(Long patientId,int recordType) {
        return iRecordDao.getLastRecordByPatientId(patientId, recordType);
    }

    @Override
    public void saveOrUpdateRecord(Record record) {
        iRecordDao.saveOrUpdateRecord(record);
    }
}
