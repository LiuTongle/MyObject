package com.mavenMVC.service;

import java.util.List;

import com.mavenMVC.entity.Compete;

public interface ICompeteService {
	Compete getById(Long id);

	void saveCompete(Compete Compete);

	boolean collectCompete(Long CompeteId, Long userId);

	boolean competeIfCollect(Long CompeteId, Long userId);

	void updateCompete(Compete Compete);

	boolean ifCompeteExists(Long id);

	List<Compete> searchCompete(String query, int start, Integer offset, List<Long> receivedIds);

	List<Compete> getCompetes(int start, Integer offset, List<Long> receivedIds);

	List<Compete> getCompetesByTab(Integer tab, int start, Integer offset, List<Long> receivedIds);

	List<Compete> getCompetesByCommodity(Long id);

	Integer getCompeteCountByTab(Integer tab);

	List<Compete> getCompetesByCommodity(Long userId, int start, Integer offset, List<Long> receivedIds);
}
