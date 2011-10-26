/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iris;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class Utility {

    public static List sortByValueGetKey(final Map m) {
        List keys = new ArrayList();
        keys.addAll(m.keySet());
        Collections.sort(keys, new Comparator() {
            public int compare(Object o1, Object o2) {
                Object v1 = m.get(o1);
                Object v2 = m.get(o2);
                if (v1 == null) {
                    return (v2 == null) ? 0 : 1;
                }
                else if (v1 instanceof Comparable) {
                    return ((Comparable) v1).compareTo(v2);
                }
                else {
                    return 0;
                }
            }
        });
        return keys;
    }

    public static List sortByValueGetValue(final Map m) {
        List keys = new ArrayList();
        keys.addAll(m.values());
        Collections.sort(keys); 
        return keys;
    }

    public static void main(String args[]){
        HashMap<Integer,Float> H = new HashMap<Integer,Float>();
        H.put(Integer.parseInt("123"), Float.parseFloat("0.5"));
        H.put(Integer.parseInt("124"), Float.parseFloat("0.4"));
        H.put(Integer.parseInt("113"), Float.parseFloat("0.3"));
        H.put(Integer.parseInt("323"), Float.parseFloat("0.4"));

        List L = Utility.sortByValueGetKey(H);
        for(int i=0; i<L.size(); ++i){
            System.out.println("ahey "+L.get(i));
        }

        List L2 = Utility.sortByValueGetValue(H);
        for(int i=0; i<L2.size(); ++i){
            System.out.println("ahey "+L2.get(i));
        }

    }
}
