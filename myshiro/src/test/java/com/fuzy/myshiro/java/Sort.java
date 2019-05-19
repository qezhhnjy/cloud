package com.fuzy.myshiro.java;


import org.junit.Test;

import java.util.Arrays;

/**
 * @author fuzy
 * create time 18-11-14-上午9:58
 */
public class Sort {

    public static final int[] FIRST = {19, 3, 16, 5, 21, 1, 15, 29, 8, 72, 30, 2, 45};

    /**
     * 直接插入排序
     * 取第一个数字为标准值，从第二个数字开始遍历，将该值跟已经排好的数组从最大到最小比较。
     * 如果比数组中的值小，则讲排好的数组该位后移一位，直到要排序的数字大于数组中的某位。
     * 将该数字插入该位置。
     * ③复杂度
     * 如果碰见一个和插入元素相等的，那么插入元素把想插入的元素放在相等元素的后面，所以插入排序是稳定的。
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     */
    @Test
    public void directInsert() {
        int[] a = FIRST.clone();
        for (int i = 1; i < a.length; i++) {
            int temp = a[i];
            int j;
            for (j = i - 1; j >= 0 && a[j] > temp; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
        System.out.println(Arrays.toString(a));
    }

    /**
     * 二分插入排序
     * ① 基本思想：
     * 二分法插入排序的思想和直接插入一样，只是找合适的插入位置的方式不同，这里是按二分法找到合适的位置，可以减少比较的次数。
     */
    @Test
    public void twoInsert() {
        
    }


}
