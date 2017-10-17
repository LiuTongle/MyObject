package com.mavenMVC.dao;

import java.util.List;

import com.mavenMVC.entity.Compete;

/**
 * 
 * @author hcd
 *
 */
public interface ICompeteDao {
	Compete getById(Long id);

	void saveCompete(Compete Compete);

	void updateCompete(Compete Compete);

	boolean ifCompeteExists(Long id);

	List<Compete> searchCompete(String query, int start, Integer offset, List<Long> receivedIds);

	List<Compete> getCompetes(int start, Integer offset, List<Long> receivedIds);

	List<Compete> getCompetesByTab(Integer tab, int start, Integer offset, List<Long> receivedIds);

	List<Compete> getCompetesByCommodity(Long id);

	Long getCompeteCountByTab(Integer tab);

	List<Compete> getCompetesByCommodity(Long userId, int start, Integer offset, List<Long> receivedIds);
}
