/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package iris;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author User
 */
public class GlobalVariable {
    public static HashMap<Integer, Document> Doc;
    public static List<Integer> searchResultDocument = null;
    public static List<Float> searchResultSimilarity = null;
    public static String queryString;
}
