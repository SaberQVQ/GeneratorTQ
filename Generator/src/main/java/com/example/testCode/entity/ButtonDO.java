package com.example.testCode.entity;

import java.io.Serializable;
import java.util.List;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Author tq
 * Date  2019-11-13
 */
public class ButtonDO {

    private String id;

    private String key;

    private String value;

    private String isDeleted;

    private Timestamp createTime;

    private Timestamp updateTime;



    public ButtonDO(){
    }

    public void setId (String id) {this.id = id;} 

    public String getId(){ return id;} 

    public void setKey (String key) {this.key = key;} 

    public String getKey(){ return key;} 

    public void setValue (String value) {this.value = value;} 

    public String getValue(){ return value;} 

    public void setIsDeleted (String isDeleted) {this.isDeleted = isDeleted;} 

    public String getIsDeleted(){ return isDeleted;} 

    public void setCreateTime (Timestamp createTime) {this.createTime = createTime;} 

    public Timestamp getCreateTime(){ return createTime;} 

    public void setUpdateTime (Timestamp updateTime) {this.updateTime = updateTime;} 

    public Timestamp getUpdateTime(){ return updateTime;} 



}