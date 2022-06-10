package br.com.danilomr.java11.service;

public class TestNestMates {

    public String publicMethod() {
        return "this is public";
    }

    private String privateMethod() {
        return "this is private";
    }

    class InnerTestNestMates {
        public String innerPublic() {
            return privateMethod();
        }
    }
}
