package com.mavenMVC.dao;

import com.mavenMVC.entity.Record;

import java.util.List;

/**
 * Created by lizai on 16/4/11.
 */
public interface IRecordDao {

    public Record getRecordById(Long id);

    public List<Record> getAllRecordsByPatientId(Long patientId, int recordType);

    public Record getLastRecordByPatientId(Long patientId, int recordType);

    public void saveOrUpdateRecord(Record record);
}
