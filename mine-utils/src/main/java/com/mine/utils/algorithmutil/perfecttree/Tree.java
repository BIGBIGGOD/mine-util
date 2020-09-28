package com.mine.utils.algorithmutil.perfecttree;

import java.util.Arrays;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 二插最大、最小堆
 * @DATE 2020/9/10 0010 14:54
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class Tree {
    public static void main(String[] args) {
//        int[] arr = {10, 23, 2, 45, 5143, 3, 4, 24};
//        buildMinHeap(arr);
//        int[] arrs = {10, 23, 2, 45, 5143, 3, 4, 24};
//        buildMaxHeap(arrs);
//        System.out.println(Arrays.toString(arr));
        int[] arrss = {10, 23, 2, 45, 5143, 3, 4, 24};
        sort(arrss);
    }

    /**
     * 根据数据以及传入的索引位置往下迭代查找对应最小子节点并返回
     */
    public static int findMin(int[] arr, int index) {
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        if (leftIndex == arr.length - 1) {
            return leftIndex;
        }
        if (leftIndex > arr.length - 1) {
            return index;
        }
        int left = findMin(arr, leftIndex);
        int right = findMin(arr, rightIndex);
        int minIndex = arr[left] - arr[right] < 0 ? leftIndex : rightIndex;
        return arr[index] - arr[minIndex] < 0 ? index : minIndex;
    }

    /**
     * 构建最小堆
     * 从0开始，每一次都往下迭代查找最小值的索引，从而与该初始值交换，构建最小堆
     *
     * @param arr 数组
     */
    public static void buildMinHeap(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = findMin(arr, i);
            int temp = arr[index];
            arr[index] = arr[i];
            arr[i] = temp;
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 构建最小堆
     * 从数组最后一个开始，每一次都往上迭代查找最大值的索引，从而与该初始值交换，构建最小堆
     *
     * @param arr 数组
     */
    public static void buildMaxHeap(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = findMax(arr, i);
            swap(arr, index, i);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 根据数据以及传入的索引位置往上迭代查找对应最大子节点并返回
     */
    public static int findMax(int[] arr, int index) {
        int parentIndex = (index - 1) / 2;
        if (parentIndex != 0) {
            parentIndex = findMax(arr, parentIndex);
        }
        return arr[index] - arr[parentIndex] < 0 ? index : parentIndex;
    }

    /**
     * 交换元素
     */
    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    /**
     * 根据数据以及传入的索引位置往下迭代查找对应最大子节点并返回,构建大根堆
     */
    public static int findMaxDown(int[] arr, int index) {
        int leftIndex = index * 2 + 1;
        int rightIndex = index * 2 + 2;
        if (leftIndex == arr.length - 1) {
            return leftIndex;
        }
        if (leftIndex > arr.length - 1) {
            return index;
        }
        int left = findMaxDown(arr, leftIndex);
        int right = findMaxDown(arr, rightIndex);
        int maxIndex = arr[left] - arr[right] > 0 ? leftIndex : rightIndex;
        return arr[index] - arr[maxIndex] > 0 ? index : maxIndex;
    }


    /**
     * 堆排序
     */
    public static void sort(int[] arr) {
        //1.构建大顶堆，直接从中间点开始遍历（因为中间点正好对应二叉树的最末端子节点，从该子节点开始往上传递最大节点值）
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            //将堆顶元素与末尾元素进行交换
            swap(arr, 0, j);
            //重新对堆进行调整
            adjustHeap(arr, 0, j);
        }

        System.out.println(Arrays.toString(arr));
    }

    /**
     * 构建大顶堆
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素i
        int temp = arr[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //将temp值放到最终的位置
        arr[i] = temp;
    }
}
