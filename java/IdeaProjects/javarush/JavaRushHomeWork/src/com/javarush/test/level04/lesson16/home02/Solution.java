package com.javarush.test.level04.lesson16.home02;

/* Среднее такое среднее
Ввести с клавиатуры три числа, вывести на экран среднее из них. Т.е. не самое большое и не самое маленькое.
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution
{
    public static void main(String[] args)   throws Exception
    {
        //Напишите тут ваш код
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(b.readLine());
        int d = Integer.parseInt(b.readLine());
        int c = Integer.parseInt(b.readLine());
        if (a<d && a>=c || a<c && a>=d ) System.out.println(a);
        if (c<d && c>=a || c<a && c>=d ) System.out.println(c);
        if (d<a && d>=c || d<c && d>=a ) System.out.println(d);

    }

}
