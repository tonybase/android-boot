package wiki.tony.andboot.data.entity;

/**
 * Created by Tony on 3/10/16.
 */
public class User {

    private String login;
    private int contributions;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getContributions() {
        return contributions;
    }

    public void setContributions(int contributions) {
        this.contributions = contributions;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                '}';
    }
}
