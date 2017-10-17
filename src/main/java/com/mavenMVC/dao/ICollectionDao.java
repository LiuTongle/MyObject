package com.mavenMVC.dao;

import com.mavenMVC.entity.Collection;

import java.util.List;

/**
 * Created by lizai on 16/5/23.
 */
public interface ICollectionDao {

    public List<Collection> getCollectionsByUserIdAndType(Long userId, Integer type, Integer start, Integer offset, List<Long> receiveIds);

    public Collection getCollectByArticleIdAndUserId(Long articleId, Long userId);

    public List<Long> getCollectArticleIds(Long userId);

    public List<Long> getCollectDoctorIds(Long userId);

    public void saveOrUpdateCollection(Collection collection);

    public void removeCollection(Collection collection);

}
