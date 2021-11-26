package Model;

public class LoginModel {
    private String username;
    private String password;
    private int type;

    LoginModel(){}
    LoginModel(String username, String password, int type){
        this.username = username;
        this.password = password;
        this.type = type;
    }

    String getUsername(){return username;}
    String getPassword(){return password;}
    int getType(){return type;}
    void setUsername(String username){this.username = username;}
    void setPassword(String password){this.password=password;}
    void setType(int type) {this.type = type;}
}
