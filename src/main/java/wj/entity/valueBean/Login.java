package wj.entity.valueBean;

public class Login {
    private String userInput;
    private String password;

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userInput='" + userInput + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
