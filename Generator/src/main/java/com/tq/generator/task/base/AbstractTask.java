package com.tq.generator.task.base;

import com.tq.generator.entity.ColumnInfo;
import com.tq.generator.util.ConfigUtil;
import com.tq.generator.util.StringUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Author tq
 * Date   2018/4/20
 */
public abstract class AbstractTask implements Serializable {
    protected String tableName;
    protected String className;

    protected String parentTableName;
    protected String parentClassName;

    protected String foreignKey;
    protected String relationalTableName;
    protected String parentForeignKey;

    protected List<ColumnInfo> tableInfos;
    protected List<ColumnInfo> parentTableInfos;

    protected String businessType;

    /**
     * Controller、Service、Dao
     *
     * @param className
     */
    public AbstractTask(String className) {
        this(null, className, null, null, null, null, null, null, null, null);
    }

    /**
     * Controller、Service、Dao
     *
     * @param className
     */
    public AbstractTask(String className, String businessType) {
        this(null, className, null, null, null, null, null, null, null, businessType);
    }

    /**
     * Entity
     *
     * @param className
     * @param parentClassName
     * @param foreignKey
     * @param tableInfos
     */
    public AbstractTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos) {
        this(null, className, null, parentClassName, foreignKey, parentForeignKey, null, tableInfos, null, null);
    }

    public AbstractTask(String className, String parentClassName, String foreignKey, String parentForeignKey, List<ColumnInfo> tableInfos, String businessType) {
        this(null, className, null, parentClassName, foreignKey, parentForeignKey, null, tableInfos, null, businessType);
    }


    /**
     * Mapper
     *
     * @param tableName
     * @param className
     * @param parentTableName
     * @param parentClassName
     * @param foreignKey
     * @param parentForeignKey
     * @param tableInfos
     * @param parentTableInfos
     */
    public AbstractTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos) {
        this(tableName, className, parentTableName, parentClassName, foreignKey, parentForeignKey, relationalTableName, tableInfos, parentTableInfos, null);
    }

    public AbstractTask(String tableName, String className, String parentTableName, String parentClassName, String foreignKey, String parentForeignKey, String relationalTableName, List<ColumnInfo> tableInfos, List<ColumnInfo> parentTableInfos, String businessType) {
        this.tableName = tableName;
        this.className = className;
        this.parentTableName = parentTableName;
        this.parentClassName = parentClassName;
        this.foreignKey = foreignKey;
        this.parentForeignKey = parentForeignKey;
        this.relationalTableName = relationalTableName;
        this.tableInfos = tableInfos;
        this.parentTableInfos = parentTableInfos;

        this.businessType = businessType;
    }

    public abstract void run() throws IOException, TemplateException;

//    @Deprecated
    protected void createFilePathIfNotExists(String filePath) {
//        if (!StringUtil.isBlank(ConfigUtil.getConfiguration().getPackageName())) { // 用户配置了包名，不进行检测
//            return;
//        }
        File file = new File(filePath);
        if (!file.exists()) { // 检测文件路径是否存在，不存在则创建
            file.mkdirs();
            System.out.println("创建文件夹: " + filePath);
        }
    }

}
