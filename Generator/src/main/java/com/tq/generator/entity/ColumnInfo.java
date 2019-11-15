package com.tq.generator.entity;

import com.tq.generator.util.StringUtil;

import java.util.Objects;

/**
 * @author Tian Qi
 * 2019/11/12
 **/
public class ColumnInfo {

    private String columnName; // 列名
    private int type; // 类型代码
    private String typeName;//类型名称
    private String propertyName; // 属性名
    private boolean isPrimaryKey; // 是否主键

    public ColumnInfo() {
    }

    public ColumnInfo(String columnName, int type, String typeName, boolean isPrimaryKey) {
        this.columnName = columnName;
        this.type = type;
        this.typeName = typeName;
        this.propertyName = StringUtil.columnName2PropertyName(columnName);;
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getTypeName() {
        if (Objects.equals("VARCHAR2",typeName)) {
            return "VARCHAR";
        }
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
