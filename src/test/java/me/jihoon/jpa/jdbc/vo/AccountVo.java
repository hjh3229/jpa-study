package me.jihoon.jpa.jdbc.vo;

public class AccountVo {
    private Integer id;
    private String username;
    private String password;

    public AccountVo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AccountVo() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
