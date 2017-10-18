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

import com.mavenMVC.dao.IFloatingPopulationDao;
import com.mavenMVC.entity.CensusRegister;
import com.mavenMVC.entity.FloatingPopulation;
@Repository
public class FloatingPopulationDaoImpl extends GenericDaoHibernate<FloatingPopulation, Long> implements IFloatingPopulationDao {

	public FloatingPopulationDaoImpl() {
		super(FloatingPopulation.class);
	}
	
	@Override
	@Autowired
	@Qualifier("sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public void upload(FloatingPopulation floatingPopulation) {
		floatingPopulation.setCreateTime(Calendar.getInstance().getTimeInMillis());
		floatingPopulation.setLastModTime(Calendar.getInstance().getTimeInMillis());
		saveOrUpdate(floatingPopulation);
	}

	@Override
	public FloatingPopulation getByIdNumber(String idNumber) {
		DetachedCriteria query = DetachedCriteria.forClass(FloatingPopulation.class);
		query.add(Restrictions.eq("identification", idNumber));
		List <FloatingPopulation> findByCriteria = getHibernateTemplate().findByCriteria(query);
		if (findByCriteria!=null && findByCriteria.size() > 0) {
			return findByCriteria.get(0);
		}
		return null;
	}

	   public void update(FloatingPopulation floatingPopulation, Long id) {
	      Query query = this.getSession().createQuery("update FloatingPopulation set userId=:userId,identification=:identification,name=:name,nickName=:nickName,gender=:gender,birthday=:birthday,ethnic=:ethnic,nativePlace=:nativePlace,marriageType=:marriageType,politicalStatus=:politicalStatus,educationalBackground=:educationalBackground,Religion=:Religion,occupationType=:occupationType,UnitOfService=:UnitOfService,cellPhone=:cellPhone,placeOfDomicile=:placeOfDomicile,placeLocation=:placeLocation,currentResidence=:currentResidence,currentLocation=:currentLocation,flowReason=:flowReason,idType=:idType,idNumber=:idNumber,dateOfRegistration=:dateOfRegistration,idExpirationDate=:idExpirationDate,longitude=:longitude,latitude=:latitude where ID=:ID");
	      query.setLong("userId", floatingPopulation.getUserId().longValue());
	      query.setString("identification", floatingPopulation.getIdentification());
	      query.setString("name", floatingPopulation.getName());
	      query.setString("nickName", floatingPopulation.getNickName());
	      query.setString("gender", floatingPopulation.getGender());
	      query.setString("birthday", floatingPopulation.getBirthday());
	      query.setString("ethnic", floatingPopulation.getEthnic());
	      query.setString("nativePlace", floatingPopulation.getNativePlace());
	      query.setString("marriageType", floatingPopulation.getMarriageType());
	      query.setString("politicalStatus", floatingPopulation.getPoliticalStatus());
	      query.setString("educationalBackground", floatingPopulation.getEducationalBackground());
	      query.setString("Religion", floatingPopulation.getReligion());
	      query.setString("occupationType", floatingPopulation.getOccupationType());
	      query.setString("UnitOfService", floatingPopulation.getUnitOfService());
	      query.setString("cellPhone", floatingPopulation.getCellPhone());
	      query.setString("placeOfDomicile", floatingPopulation.getPlaceOfDomicile());
	      query.setString("placeLocation", floatingPopulation.getPlaceLocation());
	      query.setString("currentResidence", floatingPopulation.getCurrentResidence());
	      query.setString("currentLocation", floatingPopulation.getCurrentLocation());
	      query.setString("flowReason", floatingPopulation.getFlowReason());
	      query.setString("idType", floatingPopulation.getIdType());
	      query.setString("idNumber", floatingPopulation.getIdNumber());
	      query.setString("dateOfRegistration", floatingPopulation.getDateOfRegistration());
	      query.setString("idExpirationDate", floatingPopulation.getIdExpirationDate());
	      query.setDouble("longitude", floatingPopulation.getLongitude().doubleValue());
	      query.setDouble("latitude", floatingPopulation.getLatitude().doubleValue());
	      query.setLong("ID", id.longValue());
	      query.executeUpdate();
	   }
}
