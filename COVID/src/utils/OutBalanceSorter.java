/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Model.OutBalanceModel;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Comparator;

/**
 *
 * @author ThaiTran
 */
public class OutBalanceSorter implements Comparator<OutBalanceModel> {

    boolean isAsc;
    boolean outBalance;
    String rules = "<0<1<2<3<4<5<6<7<8<9<@"
            + "<a,A<á<à<ả<ã<ạ<ă<ắ<ằ<ẳ<ẵ<ặ<â<ấ<ầ<ẩ<ẫ<ậ"
            + "<b<c<d,D<đ,Đ<e<é<è<ẻ<ẽ<ẹ<ê<ế<ề<ể<ễ<ệ<f<g<h"
            + "<i<í<ì<ỉ<ĩ<ị<j<k<l<m<n<o<ó<ò<ỏ<õ<ọ<ô<ố<ồ<ỗ"
            + "<ộ<ơ<ớ<ờ<ở<ỡ<ợ<p<q<r<s<t"
            + "<u<ú<ù<ủ<ũ<ụ<ư<ứ<ừ<ử<ữ"
            + "<v<w<x<y<ý<ỳ<ỷ<ỹ<ỵ<z";
    RuleBasedCollator ru;

    public OutBalanceSorter(boolean isAsc, boolean outBalance) throws ParseException {
        ru = new RuleBasedCollator(rules);
        this.isAsc = isAsc;
        this.outBalance = outBalance;
    }

    @Override
    public int compare(OutBalanceModel o1, OutBalanceModel o2) {
//        Locale vi = new Locale("vi_VN"); 
//        Collator viCollator = Collator.getInstance(vi);
        int i;
        if (outBalance == true) {
            int v1 = o1.getOutBalance();
            int v2 = o2.getOutBalance();
            i = Integer.compare(v1, v2);
        } else {
            String fullname1[] = o1.getName().split(" ");
            String fullname2[] = o2.getName().split(" ");
            String name1 = fullname1[fullname1.length-1];
            String name2 = fullname2[fullname2.length-1];
            i = ru.compare(name1, name2);
        }

        if (!isAsc) {
            i = -i;
        }
        if (i == 0) {
            return i = 1;
        }
        return i;
    }
}
