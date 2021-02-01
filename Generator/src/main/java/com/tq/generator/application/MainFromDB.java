package com.tq.generator.application;

import com.tq.generator.invoker.Many2ManyInvoker;
import com.tq.generator.invoker.One2ManyInvoker;
import com.tq.generator.invoker.SingleInvoker;
import com.tq.generator.invoker.base.Invoker;
import com.tq.generator.util.GeneratorUtil;

/**
 * @author Tian Qi
 * @version 1.0
 **/
public class MainFromDB {

    public static void main(String[] args) {
        single();
        //"/PAGES/TP_Pages/TP_button_form.html";
//        System.out.println(GeneratorUtil.generateHtmlFormPage("ww"));
    }

    public static void single() {
        Invoker invoker = new SingleInvoker.Builder()
                .setTableName("DEMO_TEST_TQ_20201225")
                .setClassName("test")
                .setBusinessType("demo")
                .build();
        invoker.execute();
    }

    public static void many2many() {
        Invoker invoker = new Many2ManyInvoker.Builder()
                .setTableName("user")
                .setClassName("User")
                .setParentTableName("role")
                .setParentClassName("Role")
                .setRelationTableName("user_role")
                .setForeignKey("userId")
                .setParentForeignKey("roleId")
                .build();
        invoker.execute();
    }

    public static void one2many() {
        Invoker invoker = new One2ManyInvoker.Builder()
                .setTableName("user")
                .setClassName("User")
                .setParentTableName("office")
                .setParentClassName("Office")
                .setForeignKey("officeId")
                .build();
        invoker.execute();
    }

}
