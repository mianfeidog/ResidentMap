package com.ydl.residentmap.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 帮扶记录
 * Created by 小强 on 2017/7/19.
 */
@Entity
@Table(name = "aid_record")
public class AidRecord {
    @Id
    private Long id;
    //标题
    @Column(name = "title")
    private String title;
    //内容
    @Column(name = "content")
    private String content;
    //创建时间
    @Column(name = "created_at")
    private Date createdAt;
    //创建人
    @Column(name = "created_by")
    private Long createdBy;
    //帮扶对象
    @Column(name = "resident_id")
    private Long residentId;
    //现场照片
    @Column(name = "img_path")
    private String imgPath;
    //帮扶日期
    @Column(name="aid_date")
    private Date aidDate;
    //图片list 现场照片
    @Transient
    private List<String> imgList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getResidentId() {
        return residentId;
    }

    public void setResidentId(Long residentId) {
        this.residentId = residentId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }

    public Date getAidDate() {
        return aidDate;
    }

    public void setAidDate(Date aidDate) {
        this.aidDate = aidDate;
    }
}
