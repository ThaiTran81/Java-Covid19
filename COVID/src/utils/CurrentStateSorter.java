/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import Model.CurrentStateModel;
import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author ThaiTran
 */
public class CurrentStateSorter implements Comparator<CurrentStateModel>{
    
    boolean isAsc;
    String rules = "<0<1<2<3<4<5<6<7<8<9<@"+
                "<a,A<á<à<ả<ã<ạ<ă<ắ<ằ<ẳ<ẵ<ặ<â<ấ<ầ<ẩ<ẫ<ậ"+
                "<b<c<d,D<đ,Đ<e<é<è<ẻ<ẽ<ẹ<ê<ế<ề<ể<ễ<ệ<f<g<h"+
                "<i<í<ì<ỉ<ĩ<ị<j<k<l<m<n<o<ó<ò<ỏ<õ<ọ<ô<ố<ồ<ỗ"+
                "<ộ<ơ<ớ<ờ<ở<ỡ<ợ<p<q<r<s<t"+
                "<u<ú<ù<ủ<ũ<ụ<ư<ứ<ừ<ử<ữ"+
                "<v<w<x<y<ý<ỳ<ỷ<ỹ<ỵ<z";
    RuleBasedCollator ru;
    public CurrentStateSorter(boolean isAsc) throws ParseException {
        ru = new RuleBasedCollator(rules);
        this.isAsc = isAsc;
    }
    
    
    @Override
    public int compare(CurrentStateModel o1, CurrentStateModel o2) {
//        Locale vi = new Locale("vi_VN"); 
//        Collator viCollator = Collator.getInstance(vi);
        String name1[] = o1.getFullname().split(" ");
                String name2[] = o2.getFullname().split(" ");

        int i = ru.compare(name1[name1.length-1], name2[name2.length-1]);
        if(!isAsc) i = -i;
        if(i==0) return i=1;
        return i;
    }
}
