package just.ms;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * JavaLoopTest
 * 
 * @author www.trinea.cn 2013-10-28
 */
public class HashMapLoopTest {

    public static void main(String[] args) {
        System.out.print("compare loop performance of HashMap");
        loopMapCompare(getHashMaps(10000, 100000, 1000000, 2000000));
    }

    public static Map<String, String>[] getHashMaps(int... sizeArray) {
        Map<String, String>[] mapArray = new HashMap[sizeArray.length];
        for (int i = 0; i < sizeArray.length; i++) {
            int size = sizeArray[i];
            Map<String, String> map = new HashMap<String, String>();
            for (int j = 0; j < size; j++) {
                String s = Integer.toString(j);
                map.put(s, s);
            }
            mapArray[i] = map;
        }
        return mapArray;
    }

    public static void loopMapCompare(Map<String, String>[] mapArray) {
        printHeader(mapArray);
        long startTime, endTime;

        // Type 1
        for (int i = 0; i < mapArray.length; i++) {
            Map<String, String> map = mapArray[i];
            startTime = Calendar.getInstance().getTimeInMillis();
            for (Entry<String, String> entry : map.entrySet()) {
                entry.getKey();
                entry.getValue();
            }
            endTime = Calendar.getInstance().getTimeInMillis();
            printCostTime(i, mapArray.length, "for each entrySet", endTime - startTime);
        }

        // Type 2
        for (int i = 0; i < mapArray.length; i++) {
            Map<String, String> map = mapArray[i];
            startTime = Calendar.getInstance().getTimeInMillis();
            Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                entry.getKey();
                entry.getValue();
            }
            endTime = Calendar.getInstance().getTimeInMillis();
            printCostTime(i, mapArray.length, "for iterator entrySet", endTime - startTime);
        }

        // Type 3
        for (int i = 0; i < mapArray.length; i++) {
            Map<String, String> map = mapArray[i];
            startTime = Calendar.getInstance().getTimeInMillis();
            for (String key : map.keySet()) {
                map.get(key);
            }
            endTime = Calendar.getInstance().getTimeInMillis();
            printCostTime(i, mapArray.length, "for each keySet", endTime - startTime);
        }

        // Type 4
        for (int i = 0; i < mapArray.length; i++) {
            Map<String, String> map = mapArray[i];
            startTime = Calendar.getInstance().getTimeInMillis();
            Set<Entry<String, String>> entrySet = map.entrySet();
            for (Entry<String, String> entry : entrySet) {
                entry.getKey();
                entry.getValue();
            }
            endTime = Calendar.getInstance().getTimeInMillis();
            printCostTime(i, mapArray.length, "for entrySet=entrySet()", endTime - startTime);
        }
    }

    static int                 FIRST_COLUMN_LENGTH = 23, OTHER_COLUMN_LENGTH = 12, TOTAL_COLUMN_LENGTH = 71;
    static final DecimalFormat COMMA_FORMAT        = new DecimalFormat("#,###");

    public static void printHeader(Map... mapArray) {
        printRowDivider();
        for (int i = 0; i < mapArray.length; i++) {
            if (i == 0) {
                StringBuilder sb = new StringBuilder().append("map size");
                while (sb.length() < FIRST_COLUMN_LENGTH) {
                    sb.append(" ");
                }
                System.out.print(sb);
            }

            StringBuilder sb = new StringBuilder().append("| ").append(COMMA_FORMAT.format(mapArray[i].size()));
            while (sb.length() < OTHER_COLUMN_LENGTH) {
                sb.append(" ");
            }
            System.out.print(sb);
        }
        TOTAL_COLUMN_LENGTH = FIRST_COLUMN_LENGTH + OTHER_COLUMN_LENGTH * mapArray.length;
        printRowDivider();
    }

    public static void printRowDivider() {
        System.out.println();
        StringBuilder sb = new StringBuilder();
        while (sb.length() < TOTAL_COLUMN_LENGTH) {
            sb.append("-");
        }
        System.out.println(sb);
    }

    public static void printCostTime(int i, int size, String caseName, long costTime) {
        if (i == 0) {
            StringBuilder sb = new StringBuilder().append(caseName);
            while (sb.length() < FIRST_COLUMN_LENGTH) {
                sb.append(" ");
            }
            System.out.print(sb);
        }

        StringBuilder sb = new StringBuilder().append("| ").append(costTime).append(" ms");
        while (sb.length() < OTHER_COLUMN_LENGTH) {
            sb.append(" ");
        }
        System.out.print(sb);

        if (i == size - 1) {
            printRowDivider();
        }
    }
}