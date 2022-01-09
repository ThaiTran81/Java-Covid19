package com.Model;

import java.sql.Timestamp;

public class f_historyModel {
    private String username;
    private String f_kind;
    private java.sql.Timestamp date;
    private String relatedUser;
    private String quarantine;

    public f_historyModel() {
    }

    public f_historyModel(String username, String f_kind, Timestamp date) {
        this.username = username;
        this.f_kind = f_kind;
        this.date = date;
    }

    public f_historyModel(String username, String f_kind, String quarantine, Timestamp date) {
        this.username = username;
        this.f_kind = f_kind;
        this.quarantine = quarantine;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public String getF_kind() {
        return f_kind;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setF_kind(String f_kind) {
        this.f_kind = f_kind;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getRelatedUser() {
        return relatedUser;
    }

    public void setRelatedUser(String relatedUser) {
        this.relatedUser = relatedUser;
    }

    public String getQuarantine() {
        return quarantine;
    }

    public void setQuarantine(String quarantine) {
        this.quarantine = quarantine;
    }


}
