package vn.com.stanford.je1121.springboot_je1121_thymeleaf.model;

public class Messages {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Messages(String name) {
        this.name = name;
    }

    private String name;
}
