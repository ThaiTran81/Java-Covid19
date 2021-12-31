/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Model.NecessityModel;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Comparator;

/**
 *
 * @author ThaiTran
 */
public class NecessitySorter implements Comparator<NecessityModel> {

    boolean isAsc;
    boolean price;
    boolean consumption;
    String rules = "<0<1<2<3<4<5<6<7<8<9<@"
            + "<a,A<á<à<ả<ã<ạ<ă<ắ<ằ<ẳ<ẵ<ặ<â<ấ<ầ<ẩ<ẫ<ậ"
            + "<b<c<d,D<đ,Đ<e<é<è<ẻ<ẽ<ẹ<ê<ế<ề<ể<ễ<ệ<f<g<h"
            + "<i<í<ì<ỉ<ĩ<ị<j<k<l<m<n<o<ó<ò<ỏ<õ<ọ<ô<ố<ồ<ỗ"
            + "<ộ<ơ<ớ<ờ<ở<ỡ<ợ<p<q<r<s<t"
            + "<u<ú<ù<ủ<ũ<ụ<ư<ứ<ừ<ử<ữ"
            + "<v<w<x<y<ý<ỳ<ỷ<ỹ<ỵ<z";
    RuleBasedCollator ru;

    public NecessitySorter(boolean isAsc, boolean price, boolean consumption) throws ParseException {
        ru = new RuleBasedCollator(rules);
        this.isAsc = isAsc;
        this.price = price;
        this.consumption = consumption;
    }

    @Override
    public int compare(NecessityModel o1, NecessityModel o2) {
//        Locale vi = new Locale("vi_VN"); 
//        Collator viCollator = Collator.getInstance(vi);
        int i;
        if (price == true) {
            int v1 = o1.getPrice();
            int v2 = o2.getPrice();
            i = Integer.compare(v1, v2);

        } else if (consumption == true) {
            int v1 = o1.getConsume();
            int v2 = o2.getConsume();
            i = Integer.compare(v1, v2);
        } else {
            String name1 = o1.getName();
            String name2 = o2.getName();
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
