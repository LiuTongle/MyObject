package com.mavenMVC.entity;
// Generated 2016-4-11 17:07:57 by Hibernate Tools 3.2.2.GA


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * FriendShip generated by hbm2java
 */
@Entity
@Table(name = "FriendShip"
        , catalog = "cloudHospital"
)
public class FriendShip implements java.io.Serializable {

    private Long friendshipId;
    private Long friendshipMid;
    private Long friendshipFid;
    private Integer friendshipStatus;
    private Long createTime;
    private Long lastModTime;

    public FriendShip() {
    }

    public FriendShip(Long friendshipMid, Long friendshipFid, Integer friendshipStatus, Long createTime, Long lastModTime) {
        this.friendshipMid = friendshipMid;
        this.friendshipFid = friendshipFid;
        this.friendshipStatus = friendshipStatus;
        this.createTime = createTime;
        this.lastModTime = lastModTime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "FRIENDSHIP_ID", unique = true, nullable = false)
    public Long getFriendshipId() {
        return this.friendshipId;
    }

    public void setFriendshipId(Long friendshipId) {
        this.friendshipId = friendshipId;
    }

    @Column(name = "FRIENDSHIP_MID")
    public Long getFriendshipMid() {
        return this.friendshipMid;
    }

    public void setFriendshipMid(Long friendshipMid) {
        this.friendshipMid = friendshipMid;
    }

    @Column(name = "FRIENDSHIP_FID")
    public Long getFriendshipFid() {
        return this.friendshipFid;
    }

    public void setFriendshipFid(Long friendshipFid) {
        this.friendshipFid = friendshipFid;
    }

    @Column(name = "FRIENDSHIP_STATUS")
    public Integer getFriendshipStatus() {
        return this.friendshipStatus;
    }

    public void setFriendshipStatus(Integer friendshipStatus) {
        this.friendshipStatus = friendshipStatus;
    }

    @Column(name = "CREATE_TIME")
    public Long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Column(name = "LAST_MOD_TIME")
    public Long getLastModTime() {
        return this.lastModTime;
    }

    public void setLastModTime(Long lastModTime) {
        this.lastModTime = lastModTime;
    }


}

