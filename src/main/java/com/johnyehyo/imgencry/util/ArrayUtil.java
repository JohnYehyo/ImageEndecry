package com.johnyehyo.imgencry.util;

/**
 * @description:
 * @author: JohnYehyo
 * @create: 2022-06-30 22:15:47
 */
public class ArrayUtil {
    //二维数组降一维
    public static void  recovery(int arr2[][], int arr1[], int M, int N) {
        int k = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                arr1[k++] = arr2[i][j];
            }
        }

    }

    //一维数组升二维
    public static void  change(int arr1[], int arr2[][], int M, int N) {
        int k = 0;
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                arr2[i][j] = arr1[k++];
            }
        }

    }

    //把二维数组行列倒换
    public static void  arr_change(int arr[][], int temp[][], int M, int N) {
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < N; ++j) {
                temp[j][i] = arr[i][j];
            }
        }
    }

    //把arr1复制给arr2
    public static void  arr_copy(double arr1[], double arr2[], int N) {
        for (int i = 0; i < N; ++i) {
            arr2[i] = arr1[i];
        }
    }
}
