package com.mine.utils.testutil;

import java.util.Arrays;

/**
 * Created by jiangqd on 2019/3/17.
 */
public class ArrayTest {

    public static void main(String[] args) {
        int[] arr = new int[]{6, 5, 1, 715};
//        maoPao(arr);
//        xuanZe(arr);
        chaRu(arr);
//        kuaiSu(arr,0,arr.length-1);
    }

    /**
     * 冒泡排序
     */
    public static void maoPao(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序
     */
    public static void xuanZe(int[] arr) {
        int index = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[index] > arr[j]) {
                    index = j;
                }
            }
            if (index != i) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序
     */
    public static void chaRu(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            while (i > 0 && arr[i - 1] > temp) {
                arr[i] = arr[i - 1];
                i--;
            }
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 快速排序
     */
    public static void kuaiSu(int[] arr, int left, int right) {
        int temp = 0;
        int left_ = left;
        int right_ = right;
        if (left < right) {
            temp = arr[left];
            while (left != right) {
                while (right > left && arr[right] >= temp) {
                    right--;
                }
                arr[left] = arr[right];
                while (right > left && arr[left] <= temp) {
                    left++;
                }
                arr[right] = arr[left];
            }
            //基准元素归位
            arr[left] = temp;
            //迭代
            kuaiSu(arr, left_, left - 1);
            kuaiSu(arr, left + 1, right_);
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void testKuaiSu(int[] arr, int left, int right) {
        int left_ = left;
        int right_ = right;
        if (left < right) {
            int temp = arr[left];
            while (left != right) {
                while (left < right && arr[left] < arr[right]) {
                    right--;
                }
                arr[left] = arr[right];
                while (left < right && arr[left] < temp) {
                    left++;
                }
                arr[right] = arr[left];
                arr[left] = temp;
            }
            System.out.println(Arrays.toString(arr));
            testKuaiSu(arr, left + 1, right_);
            testKuaiSu(arr, left_, left);
        }
        System.out.println(Arrays.toString(arr));
    }

}
