package com.javarush.test.level04.lesson06.task03;

/* Сортировка трех чисел
Ввести с клавиатуры три числа, и вывести их в порядке убывания.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        int[] a = new int[3];
        a[0] = Integer.parseInt(b.readLine());
        a[1] = Integer.parseInt(b.readLine());
        a[2] = Integer.parseInt(b.readLine());
//        a[0] = 1;
//        a[1] = 8;
//        a[2] = 3;
        for (int i = 0; i < 2; i++) {
            if (a[i]<a[i+1]) {
                int c = a[i];
                a[i] = a[i+1];
                a[i+1] = c;
            }
        }
        for (int i = 0; i < 2; i++) {
            if (a[i]<a[i+1]) {
                int c = a[i];
                a[i] = a[i+1];
                a[i+1] = c;
            }
        }
        for (int i = 0; i < 2; i++) {
            if (a[i]<a[i+1]) {
                int c = a[i];
                a[i] = a[i+1];
                a[i+1] = c;
            }
        }
        for (int i = 0; i < 2; i++) {
            if (a[i]<a[i+1]) {
                int c = a[i];
                a[i] = a[i+1];
                a[i+1] = c;
            }
        }
        System.out.println(a[0]);
        System.out.println(a[1]);
        System.out.println(a[2]);
    }
}
