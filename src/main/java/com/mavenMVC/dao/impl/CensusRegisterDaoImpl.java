package com.mavenMVC.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mavenMVC.dao.ICensusRegisterDao;
import com.mavenMVC.entity.CensusRegister;

@Repository
@Transactional
public class CensusRegisterDaoImpl extends GenericDaoHibernate<CensusRegister, Long> implements ICensusRegisterDao {

	public CensusRegisterDaoImpl() {
		super(CensusRegister.class);
	}

	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void upload(CensusRegister censusRegister) {
		censusRegister.setCreateTime(Calendar.getInstance().getTimeInMillis());
		censusRegister.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(censusRegister);
	}

	@Override
	public CensusRegister getByIdNumber(String idNumber) {
		DetachedCriteria query = DetachedCriteria.forClass(CensusRegister.class);
		query.add(Restrictions.eq("identification", idNumber));
		List <CensusRegister>findByCriteria = getHibernateTemplate().findByCriteria(query);
		if (findByCriteria!=null && findByCriteria.size() > 0) {
			return findByCriteria.get(0);
		}
		return null;
	}
	   public void update(CensusRegister censusRegister, Long id) {
		      Query query = this.getSession().createQuery("update CensusRegister set userId=:userId,identification=:identification,name=:name,nickName=:nickName,gender=:gender,birthday=:birthday,ethnic=:ethnic,nativePlace=:nativePlace,marriageType=:marriageType,politicalStatus=:politicalStatus,educationalBackground=:educationalBackground,Religion=:Religion,occupationType=:occupationType,occupation=:occupation,UnitOfService=:UnitOfService,cellPhone=:cellPhone,placeOfDomicile=:placeOfDomicile,placeLocation=:placeLocation,currentResidence=:currentResidence,currentLocation=:currentLocation,populationConsistentIdentifier=:populationConsistentIdentifier,householdNumber=:householdNumber,householdIdentification=:householdIdentification,householdName=:householdName,householdRelation=:householdRelation,householdPhone=:householdPhone,longitude=:longitude,latitude=:latitude where ID=:ID");
		      query.setLong("userId", censusRegister.getUserId().longValue());
		      query.setString("identification", censusRegister.getIdentification());
		      query.setString("name", censusRegister.getName());
		      query.setString("nickName", censusRegister.getNickName());
		      query.setString("gender", censusRegister.getGender());
		      query.setString("birthday", censusRegister.getBirthday());
		      query.setString("ethnic", censusRegister.getEthnic());
		      query.setString("nativePlace", censusRegister.getNativePlace());
		      query.setString("marriageType", censusRegister.getMarriageType());
		      query.setString("politicalStatus", censusRegister.getPoliticalStatus());
		      query.setString("educationalBackground", censusRegister.getEducationalBackground());
		      query.setString("Religion", censusRegister.getReligion());
		      query.setString("occupationType", censusRegister.getOccupationType());
		      query.setString("occupation", censusRegister.getOccupation());
		      query.setString("UnitOfService", censusRegister.getUnitOfService());
		      query.setString("cellPhone", censusRegister.getCellPhone());
		      query.setString("placeOfDomicile", censusRegister.getPlaceOfDomicile());
		      query.setString("placeLocation", censusRegister.getPlaceLocation());
		      query.setString("currentResidence", censusRegister.getCurrentResidence());
		      query.setString("currentLocation", censusRegister.getCurrentLocation());
		      query.setString("populationConsistentIdentifier", censusRegister.getPopulationConsistentIdentifier());
		      query.setString("householdNumber", censusRegister.getHouseholdNumber());
		      query.setString("householdIdentification", censusRegister.getHouseholdIdentification());
		      query.setString("householdName", censusRegister.getHouseholdName());
		      query.setString("householdRelation", censusRegister.getHouseholdRelation());
		      query.setString("householdPhone", censusRegister.getHouseholdPhone());
		      query.setDouble("longitude", censusRegister.getLongitude().doubleValue());
		      query.setDouble("latitude", censusRegister.getLatitude().doubleValue());
		      query.setLong("ID", id.longValue());
		      query.executeUpdate();
		   }
}
