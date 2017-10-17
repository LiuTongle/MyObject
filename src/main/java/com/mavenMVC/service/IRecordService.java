package com.mavenMVC.service;

import com.mavenMVC.entity.Record;

import java.util.List;

/**
 * Created by lizai on 15/6/10.
 */
public interface IRecordService {

    public Record getRecordById(Long id);

    public List<Record> getAllRecordsByPatientId(Long patientId,int recordType);

    public Record getLastRecordByPatientId(Long patientId,int recordType);

    public void saveOrUpdateRecord(Record record);

}