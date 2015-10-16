package com.javarush.test.level03.lesson12.home02;

/* Я не хочу изучать Java, я хочу большую зарплату
Вывести на экран десять раз надпись «Я не хочу изучать Java, я хочу большую зарплату»
*/

import java.io.Console;
import java.util.*;
public class Solution
{
    public static class Cat {
        public static int counter = 0;
        public void createCat() {
            counter++;
        }
        public int getCount() {
            return counter;
        }
    }
    public static void main(String[] args)
    {
//        Напишите тут ваш код
        System.out.println("Я не хочу изучать Java, я хочу большую зарплату");System.out.println("Я не хочу изучать Java, я хочу большую зарплату");System.out.println("Я не хочу изучать Java, я хочу большую зарплату");System.out.println("Я не хочу изучать Java, я хочу большую зарплату");System.out.println("Я не хочу изучать Java, я хочу большую зарплату");System.out.println("Я не хочу изучать Java, я хочу большую зарплату");System.out.println("Я не хочу изучать Java, я хочу большую зарплату");System.out.println("Я не хочу изучать Java, я хочу большую зарплату");System.out.println("Я не хочу изучать Java, я хочу большую зарплату");System.out.println("Я не хочу изучать Java, я хочу большую зарплату");
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        System.out.println(name);

        Cat c = new Cat();
        c.createCat();
        c.createCat();
        System.out.println(c.getCount());

    }
}
