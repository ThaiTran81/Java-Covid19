/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import Model.FModel;
import static java.lang.String.format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ThaiTran
 */
public class SortByValue {

    public static Map<String, FModel>
            valueSort(final Map<String, FModel> map, int column, boolean asc) {
        // Static Method with return type Map and
        // extending comparator class which compares values
        // associated with two keys
        Comparator<String> valueComparator = new Comparator<String>() {

            // return comparison results of values of
            // two keys
            public int compare(String k1, String k2) {
                int comp = -1;
                if (column <= 0) {
                    try {
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = format.parse(k1);
                        Date date2 = format.parse(k2);

                        if (date1.compareTo(date2) >= 0) {
                            comp = 1;
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(SortByValue.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (column == 1) {
                    if (map.get(k1).getF0() >= map.get(k2).getF0()) {
                        comp = 1;
                    }
                }
                if (column == 2) {
                    if (map.get(k1).getF1() >= map.get(k2).getF1()) {
                        comp = 1;
                    }
                }
                if (column == 3) {
                    if (map.get(k1).getF2() >= map.get(k2).getF2()) {
                        comp = 1;
                    }
                }
                if (column == 4) {
                    if (map.get(k1).getF3() >= map.get(k2).getF3()) {
                        comp = 1;
                    }
                }
                if (column >= 5) {
                    if (map.get(k1).getGood() >= map.get(k2).getGood()) {
                        comp = 1;
                    }
                }
                if (!asc) {
                    comp = comp * (-1);
                }
                return comp;
            }

        };

        // SortedMap created using the comparator
        Map<String, FModel> sorted = new TreeMap<String, FModel>(valueComparator);

        sorted.putAll(map);

        return sorted;
    }
}
