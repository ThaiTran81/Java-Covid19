package Model;

import java.sql.Date;

public class f_historyModel {
    private String username;
    private String f_kind;
    private java.sql.Date date;

    public f_historyModel() {
    }

    public f_historyModel(String username, String f_kind, Date date) {
        this.username = username;
        this.f_kind = f_kind;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getF_kind() {
        return f_kind;
    }

    public Date getDate() {
        return date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setF_kind(String f_kind) {
        this.f_kind = f_kind;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
