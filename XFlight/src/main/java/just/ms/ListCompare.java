package just.ms;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * JavaLoopTest
 * 
 * @author www.trinea.cn 2013-10-28
 */
public class ListCompare {

    public static void main(String[] args) {

        System.out.print("compare loop performance of ArrayList");
        loopListCompare(getArrayLists(10000, 100000, 1000000, 9000000));

        System.out.print("\r\n\r\ncompare loop performance of LinkedList");
        loopListCompare(getLinkedLists(100, 1000, 10000, 100000));
    }

    public static List<Integer>[] getArrayLists(int... sizeArray) {
        List<Integer>[] listArray = new ArrayList[sizeArray.length];
        for (int i = 0; i < listArray.length; i++) {
            int size = sizeArray[i];
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < size; j++) {
                list.add(j);
            }
            listArray[i] = list;
        }
        return listArray;
    }

    public static List<Integer>[] getLinkedLists(int... sizeArray) {
        List<Integer>[] listArray = new LinkedList[sizeArray.length];
        for (int i = 0; i < listArray.length; i++) {
            int size = sizeArray[i];
            List<Integer> list = new LinkedList<Integer>();
            for (int j = 0; j < size; j++) {
                list.add(j);
            }
            listArray[i] = list;
        }
        return listArray;
    }

    public static void loopListCompare(List<Integer>... listArray) {
        printHeader(listArray);
        long startTime, endTime;

        // Type 1
        for (int i = 0; i < listArray.length; i++) {
            List<Integer> list = listArray[i];
            startTime = Calendar.getInstance().getTimeInMillis();
            for (Integer j : list) {
                // use j
            }
            endTime = Calendar.getInstance().getTimeInMillis();
            printCostTime(i, listArray.length, "for each", endTime - startTime);
        }

        // Type 2
        for (int i = 0; i < listArray.length; i++) {
            List<Integer> list = listArray[i];
            startTime = Calendar.getInstance().getTimeInMillis();
            // Iterator<Integer> iterator = list.iterator();
            // while(iterator.hasNext()) {
            // iterator.next();
            // }
            for (Iterator<Integer> iterator = list.iterator(); iterator.hasNext();) {
                iterator.next();
            }
            endTime = Calendar.getInstance().getTimeInMillis();
            printCostTime(i, listArray.length, "for iterator", endTime - startTime);
        }

        // Type 3
        for (int i = 0; i < listArray.length; i++) {
            List<Integer> list = listArray[i];
            startTime = Calendar.getInstance().getTimeInMillis();
            for (int j = 0; j < list.size(); j++) {
                list.get(j);
            }
            endTime = Calendar.getInstance().getTimeInMillis();
            printCostTime(i, listArray.length, "for list.size()", endTime - startTime);
        }

        // Type 4
        for (int i = 0; i < listArray.length; i++) {
            List<Integer> list = listArray[i];
            startTime = Calendar.getInstance().getTimeInMillis();
            int size = list.size();
            for (int j = 0; j < size; j++) {
                list.get(j);
            }
            endTime = Calendar.getInstance().getTimeInMillis();
            printCostTime(i, listArray.length, "for size = list.size()", endTime - startTime);
        }

        // Type 5
        for (int i = 0; i < listArray.length; i++) {
            List<Integer> list = listArray[i];
            startTime = Calendar.getInstance().getTimeInMillis();
            for (int j = list.size() - 1; j >= 0; j--) {
                list.get(j);
            }
            endTime = Calendar.getInstance().getTimeInMillis();
            printCostTime(i, listArray.length, "for j--", endTime - startTime);
        }
    }

    static int                 FIRST_COLUMN_LENGTH = 23, OTHER_COLUMN_LENGTH = 12, TOTAL_COLUMN_LENGTH = 71;
    static final DecimalFormat COMMA_FORMAT        = new DecimalFormat("#,###");

    public static void printHeader(List<Integer>... listArray) {
        printRowDivider();
        for (int i = 0; i < listArray.length; i++) {
            if (i == 0) {
                StringBuilder sb = new StringBuilder().append("list size");
                while (sb.length() < FIRST_COLUMN_LENGTH) {
                    sb.append(" ");
                }
                System.out.print(sb);
            }

            StringBuilder sb = new StringBuilder().append("| ").append(COMMA_FORMAT.format(listArray[i].size()));
            while (sb.length() < OTHER_COLUMN_LENGTH) {
                sb.append(" ");
            }
            System.out.print(sb);
        }
        TOTAL_COLUMN_LENGTH = FIRST_COLUMN_LENGTH + OTHER_COLUMN_LENGTH * listArray.length;
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