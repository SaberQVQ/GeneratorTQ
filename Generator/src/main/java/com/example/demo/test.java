package com.example.demo;

import com.tq.generator.util.StringUtil;

/**
 * @author Tian Qi
 * 2019/11/11
 **/
public class test {

    public static void main(String[] args) {
//       String param = "{\\\"data\":[{\\\"endTime\\\":\\\"2019-11-11 23:59:59\\\",\\\"startTime\\\":\\\"2019-11-11 00:00:00\\\"}]}";
//        String paramAfterFormat = param.replace("\\","");
////        param = StringEscapeUtils.unescapeHtml(param);
//        System.out.println(paramAfterFormat);
//        JSONObject param2 = JSONObject.parseObject(paramAfterFormat);
//        System.out.println(param2);


//        List<userEntity1> u1 = new ArrayList<>();
//        List<userEntity2> u2;
//        userEntity1 u11 = new userEntity1();
//        u11.setId("2");
//        u11.setName("tq1");
//        u11.setPhoneNumber("");
//
//        userEntity1 u12 = new userEntity1();
//        u12.setId("2");
//        u12.setName("tq2");
//        u12.setPhoneNumber("");
//
//        u1.add(u11);
//        u1.add(u12);
//        u2 = copyList(u1, userEntity2.class);
//
//        System.out.println(u2.get(0).getName());
//        System.out.println(u2.get(1).getName());

        String ss = StringUtil.columnName2PropertyName("Test_test_TEST_TT");
        System.out.println(ss);

    }

    public static class userEntity1{
        private String id;
        private String name;
        private String phoneNumber;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    public static class userEntity2{
        private String id;
        private String name;
        private Long phoneNumber;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }


}
