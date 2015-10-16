package com.javarush.test.level04.lesson06.task02;

/* Максимум четырех чисел
Ввести с клавиатуры четыре числа, и вывести максимальное из них.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args) throws Exception
    {
        //Напишите тут ваш код
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        int m1 = Integer.parseInt(b.readLine());
        int m2 = Integer.parseInt(b.readLine());
        int m3 = Integer.parseInt(b.readLine());
        int m4 = Integer.parseInt(b.readLine());
        int ma1 = m1>m2?m1:m2;
        int ma2 = m3>m4?m3:m4;
        System.out.println(ma1>ma2?ma1:ma2);
    }
}
