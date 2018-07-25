package com.pangw.sf;

import java.util.Arrays;

/**
 * @Auther: pangw
 * @Date: 2018/7/24 18:05
 * @Description: java中常用的排序和查找
 */
public class MySfDemo {

    public static int[] array = new int[]{12, 2, 3, 11, 1, 8, 22, 15, 9, 32, 24, 55, 19, 53, 75, 21, 1};

    public static void main(String[] args) {


//        selectSort(arrays);
        insertSort(array);
    }

    /**
     * @Description: 插入排序
     * 思路：如同玩扑克牌一样，每次摸牌都将它与手中的牌比较，始终将牌放在比它大的牌前面，比它小的牌后面。这样当牌全部摸到手上后，就是一个有序的序列。
     * @param:
     * @return: [1, 1, 2, 3, 8, 9, 11, 12, 15, 19, 21, 22, 24, 32, 53, 55, 75]
     * @date: 2018/7/24 下午6:36
     */
    private static void insertSort(int[] array) {

        int i,j;
        int n = array.length;
        int taget;
        for (i = 1; i < n; i++) {
            j = i;
            taget = array[i];
            while (j > 0 && taget < array[j - 1]) {
                array[j] = array[j-1];
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
