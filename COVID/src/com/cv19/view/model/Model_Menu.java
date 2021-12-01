package com.cv19.view.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {

    private String icon;
    private String name;
    private MenuType type;

    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/com/cv19/icon/" + icon + ".png"));
    }

    public Icon toIconSelected() {
        try {
            return new ImageIcon(getClass().getResource("/com/cv19/icon/" + icon + "_selected.png"));
        } catch (java.lang.NullPointerException e) {
            return toIcon();
        }
        
    }

    public static enum MenuType {
        MENU, EMPTY, EXIT
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public Model_Menu() {
    }

}
