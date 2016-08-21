package cn.swu.edu.opensource.openswu_webapi_jersey.quitnet;

/**
 * Created by 张孟尧 on 2016/5/28.
 *
 * a POJO json pattern that jersey will receive from the client post.
 */
public class QuitNetParam {
    public String username;
    public String password;
    public Long date;

    public Long getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "username : " + this.getUsername() +
                " password : " + this.getPassword() +
                " date : " + this.getDate();
    }
}
