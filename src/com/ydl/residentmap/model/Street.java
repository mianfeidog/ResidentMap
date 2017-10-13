package com.ydl.residentmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 街道
 */
@Entity
@Table(name = "street")
public class Street implements Serializable {
    private static final long serialVersionUID = 8556304036066494898L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    //后勤
    @Column(name = "service")
    private String service;

    //治安
    @Column(name = "security")
    private String security;

    //社保
    @Column(name = "social_security")
    private String socialSecurity;

    //社区建设
    @Column(name = "community_build")
    private String communityBuild;

    //街道建设
    @Column(name = "street_build")
    private String streetBuild;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getSocialSecurity() {
        return socialSecurity;
    }

    public void setSocialSecurity(String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }

    public String getCommunityBuild() {
        return communityBuild;
    }

    public void setCommunityBuild(String communityBuild) {
        this.communityBuild = communityBuild;
    }

    public String getStreetBuild() {
        return streetBuild;
    }

    public void setStreetBuild(String streetBuild) {
        this.streetBuild = streetBuild;
    }
}
