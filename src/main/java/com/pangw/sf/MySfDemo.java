package com.pangw.sf;

import java.util.Arrays;

/**
 * @Auther: pangw
 * @Date: 2018/7/24 18:05
 * @Description: java中常用的排序和查找
 * <p>
 * 选择排序、插入排序、shell排序、冒泡排序、快速排序、归并排序
 */
public class MySfDemo {

    public static int[] array = new int[]{12, 2, 3, 11, 1, 8, 22, 15, 9, 32, 24, 55, 19, 53, 75, 21, 1};

    public static void main(String[] args) {


//        selectSort(array);
//        insertSort(array);
//        shellSort(array);
//        bubbleSort(array);
//        quickSort(array);
//        megerSort(array);
    }

    /**
     * @Description: 思路：归并（Merge）排序法是将两个（或两个以上）有序表合并成一个新的有序表，
     * 即把待排序序列分为若干个子序列，每个子序列是有序的。然后再把有序子序列合并为整体有序序列。
     * @param:
     * @return:
     * @date: 2018/7/25 下午5:25
     */
    private static void megerSort(int[] array, int start, int end) {
        int mid = array.length / 2;
        if (end > start) {
            megerSort(array, start, mid);
            megerSort(array, mid + 1, end);
            megerDivide(array, start, mid, end);
        }

    }

    /**
     * @Description: 归并排序拆分
     * @param:
     * @return:
     * @date: 2018/7/25 下午7:09
     */
    private static void megerDivide(int[] array, int start, int mid, int end) {
        int[] news = new int[end - start + 1];

        int s1 = start;
        int s2 = mid + 1;
        int k = 0;
        //两个集合  （s1  mid） (s2 end)

        while (s1 < mid && s2 < end) {
            if (array[s1] < array[s2]) {
                news[k++] = array[s1++];
            } else {
                news[k++] = array[s2++];
            }
        }


    }

    /**
     * @Description: 快速排序
     * @param:
     * @return:
     * @date: 2018/7/25 下午5:07
     */
    private static void quickSort(int[] array) {
        faseSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    /**
     * @Description: 快速排序   快速排序由C. A. R. Hoare在1962年提出。它的基本思想是：通过一趟排序将要排序的数据分割成独立的两部分，
     * 其中一部分的所有数据都比另外一部分的所有数据都要小，
     * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
     * @param:
     * @return:
     * @date: 2018/7/25 下午4:20
     */
    private static void faseSort(int[] array, int start, int end) {
        if (start > end) {
            return;
        } else {
            int a = fast_divide(array, start, end);
            faseSort(array, start, a - 1);
            faseSort(array, a + 1, end);
        }
    }

    /**
     * @Description: 快速排序的递归 分割
     * 以最后一个为基准
     * @param: 数组的源 和开始 结束的 索引
     * @return: 基数的索引
     * @date: 2018/7/25 下午4:40
     */
    private static int fast_divide(int[] array, int s, int e) {
        int base = array[e];
        int start = s;
        int end = e;

        while (start < end) {
            //一直判断 直到有个值比base 大
            while (start < end && array[start] <= base) {
                start++;
            }
            //交换
            if (start < end) {
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }
            while (start < end && array[end] >= base) {
                end--;
            }
            if (start < end) {
                int temp = array[end];
                array[end] = array[start];
                array[start] = temp;
            }
        }
        return s;
    }

    /**
     * @Description:思路：在要排序的一组数中，对当前还未排好序的范围内的全部数，自上而下对相邻的两个数依次进行比较和调整， 让较大的数往下沉，较小的往上冒。
     * 即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
     * @param:
     * @return: [1, 1, 2, 3, 8, 9, 11, 12, 15, 19, 21, 22, 24, 32, 53, 55, 75]
     * @date: 2018/7/25 下午3:58
     */
    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    //交换
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));

    }

    /**
     * @Description:希尔排序
     * @param:
     * @return:
     * @date: 2018/7/25 下午3:52
     */
    private static void shellSort(int[] array) {


    }


    /**
     * @Description: 插入排序
     * 思路：如同玩扑克牌一样，每次摸牌都将它与手中的牌比较，始终将牌放在比它大的牌前面，比它小的牌后面。这样当牌全部摸到手上后，就是一个有序的序列。
     * @param:
     * @return: [1, 1, 2, 3, 8, 9, 11, 12, 15, 19, 21, 22, 24, 32, 53, 55, 75]
     * @date: 2018/7/24 下午6:36
     */
    private static void insertSort(int[] array) {

        int i, j;
        int n = array.length;
        int taget;
        for (i = 1; i < n; i++) {
            j = i;
            taget = array[i];
            while (j > 0 && taget < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = taget;
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * @Description: 选择排序：
     * 思路：在乱序数组中，假设第一位数最小，依次让后面的数与之比较，若遇到比它小的数就交换位置，一趟下来第一个数就是序列中最小的数，然后从第二个数开始重复操作。
     * @param:
     * @return: [1, 1, 2, 3, 8, 9, 11, 12, 15, 19, 21, 22, 24, 32, 53, 55, 75]
     * @date: 2018/7/24 下午6:11
     */
    private static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    //交换位置
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));

    }
}
