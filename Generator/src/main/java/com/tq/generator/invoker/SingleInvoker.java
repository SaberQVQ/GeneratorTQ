package com.tq.generator.invoker;

import com.tq.generator.invoker.base.AbstractBuilder;
import com.tq.generator.invoker.base.AbstractInvoker;
import com.tq.generator.invoker.base.Invoker;
import com.tq.generator.util.GeneratorUtil;
import com.tq.generator.util.StringUtil;

import java.sql.SQLException;

/**
 * Author tq
 * Date   2018/9/5
 */
public class SingleInvoker extends AbstractInvoker {

    @Override
    protected void getTableInfos() throws SQLException {
        tableInfos = connectionUtil.getMetaData(tableName);
    }

    @Override
    protected void initTasks() {
        taskQueue.initSingleTasks(className, tableName, tableInfos, businessType);
    }

    public static class Builder extends AbstractBuilder {
        private SingleInvoker invoker = new SingleInvoker();

        public Builder setTableName(String tableName) {
            invoker.setTableName(tableName);
            return this;
        }

        public Builder setClassName(String className) {
            invoker.setClassName(className);
            return this;
        }

        public Builder setBusinessType(String businessType) {
            invoker.setBusinessType(businessType);
            return this;
        }

        @Override
        public Invoker build() {
            if (!isParamtersValid()) {
                return null;
            }
            return invoker;
        }

        @Override
        public void checkBeforeBuild() throws Exception {
            if (StringUtil.isBlank(invoker.getTableName())) {
                throw new Exception("Expect table's name, but get a blank String.");
            }
            if (StringUtil.isBlank(invoker.getClassName())) {
                invoker.setClassName(GeneratorUtil.generateClassName(invoker.getTableName()));
            }
        }
    }

}
