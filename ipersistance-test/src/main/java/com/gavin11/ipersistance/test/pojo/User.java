package com.gavin11.ipersistance.test.pojo;

/**
 * @author Gavin <br>
 * @version 1.0 <br>
 * @project lg-study
 * @package com.gavin11.ipersistance.test.pojo
 * @description User <br>
 * @date 2021/4/2 0:39 <br>
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String birthday;

    public User() {
    }

    public User(int id, String username, String password, String birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
