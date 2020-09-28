package com.company.aplana;

/**
 *  На входе имеется файл, заполненный словами. Необходимо считать все слова из файла, отсортировать их в
 *  алфавитном порядке и вывести на экран. Посчитать, сколько раз встречается в файле каждое из слов и вывести
 *  статистику на экран.
 * Найти слово, встречающееся максимальное число раз в файле и его частоту и вывести на экран.
 *     Замечания:
 * -В случае нескольких слов с максимальной частотой выводить необходимо все.
 * -Предусмотреть, что слова могут быть разделены не только пробелами, но и знаками препинания, табуляции и переноса строки, все эти символы не должны учитываться.
 * -Допускается только один полный обход файла.
 * -Максимально предусмотреть возможные исключения.
 * - (*) Предусмотреть ввод пользователем пути к файлу, как абсолютного, так и относительного.
 */

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите путь к файлу и формат(если файл в корне, про просто имя и формат: ");
        // "words.txt"
        String path = sc.next();

        sorted(path);
        howRepeat(path);
        maxRepeat(path);

        sc.close();
    }

    static String reader(String path) throws NullPointerException {
        String words = null;
        try(FileReader reader = new FileReader(path))
        {
            char[] buf = new char[4096];
            int c;
            while((c = reader.read(buf))>0){
                if(c < 4096){
                    buf = Arrays.copyOf(buf, c);
                }
            }
            words = new String(buf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    static String[] splitWords(String path) {
        String[] arrWords = reader(path).split("[\\s.,?]+");
        return arrWords;
    }

    static void sorted (String path) {
        TreeSet<String> ts = new TreeSet<>();
        for (String s : splitWords(path)) {
            ts.add(s);
        }
        System.out.print("Отсортированные по алфавиту: ");
        System.out.println(ts);
    }

    static void  howRepeat (String path) {
        Map<String,Integer> hashMap = new HashMap<>();
        for (String s : splitWords(path)) {
            hashMap.put(s, hashMap.get(s) == null ? 1 : hashMap.get(s) + 1);
        }
        System.out.print("Количество повторений слов в файле: ");
        System.out.println(hashMap);

    }

    static void maxRepeat(String path) {
        Map<String,Integer> hashMap = new HashMap<>();
        for (String s : splitWords(path)) {
            hashMap.put(s, hashMap.get(s) == null ? 1 : hashMap.get(s) + 1);
        }

        Integer maxValue = 0;
        for (Integer value : hashMap.values()) {
            if (value > maxValue) {
                maxValue = value;
            }
        }

        for (Map.Entry<String, Integer> c : hashMap.entrySet()) {
            if (c.getValue() == maxValue) System.out.println(c.getKey() + " - " + c.getValue() + " раза.");
        }
    }
}
