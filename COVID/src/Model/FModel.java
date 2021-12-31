/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ThaiTran
 */
public class FModel {
    int f0;
    int f1;
    int f2;
    int f3;
    int good;

    public FModel() {
        f0=0;
        f1=0;
        f2=0;
        f3=0;
        good=0;
    }

    public FModel(int f0, int f1, int f2, int f3, int good) {
        this.f0 = f0;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.good = good;
    }

    public int getF0() {
        return f0;
    }

    public void setF0(int f0) {
        this.f0 = f0;
    }

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        this.f1 = f1;
    }

    public int getF2() {
        return f2;
    }

    public void setF2(int f2) {
        this.f2 = f2;
    }

    public int getF3() {
        return f3;
    }

    public void setF3(int f3) {
        this.f3 = f3;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    
}
