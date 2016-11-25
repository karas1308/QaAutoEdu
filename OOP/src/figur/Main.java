package figur;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        double area = 0;
        int step = 0, k;
        double[] array = new double[10];
        double[] array1 = new double[10];
        int j = 0;
        for (int i = 0; i < 10; i++) {
            double rand = Math.random() * 10;
            if (rand < 4) {
                step = 0;
            }
            if (rand > 3 && rand < 7) {
                step = 1;
            }
            if (rand > 6) {
                step = 2;
            }
            switch (step) {
                case 0:
                    Triangle triangle = new Triangle();
                    area = triangle.area();
                    array[j] = Math.rint(100.0 * area) / 100.0;
                    array1[j] = triangle.resize();
                    j++;
                    break;
                case 1:
                    Rectangle rec = new Rectangle();
                    area = rec.area();
                    array[j] = Math.rint(100.0 * area) / 100.0;
                    array1[j] = rec.resize();
                    j++;
                    break;
                case 2:
                    Circle circle = new Circle();
                    area = circle.area();
                    array[j] = Math.rint(100.0 * area) / 100.0;
                    array1[j] = circle.resize();
                    j++;
                    break;
            }
        }

        System.out.print("Получился вот такой вот массивчик из площадей фигур: ");
        for (k = 0; k < 10; k++) {
            System.out.print(array[k] + " ");
        }

        Arrays.sort(array);
        System.out.println();
        System.out.print("Расставим площади фигур по возрастанию: ");
        for (k = 0; k < 10; k++) {
            System.out.print(array[k] + " ");
        }

        Arrays.sort(array1);
        System.out.println();
        System.out.print("Расставим площади фигур c рандомно измененным размером по возрастанию: ");
        for (k = 0; k < 10; k++) {
            System.out.print(array1[k] + " ");
        }
    }
}


//"В отдельном пакете figur нужно создать структуру классов и имплементировать необходимые методы.
//        Должен быть базовый абстрактный класс и его наследники.
//        Все функции должны быть реализованы.
//        В классе Main с главным методом main должно быть реализовано задание.
//
//        Описание класов:
//        Создать структуру классов для круга, квадрата и треугольника. Каждый из них должен иметь следующие свойства и методы
//        координаты
//        - функцию перемещения
//        - функцию расчета площади
//        - функцию изменения размера
//
//        Задание
//        В функции main должен быть рализован следующие пункты:
//        - создания массива рандомных фигур размером в 10 элементов.
//        - изменение размера для каждой фигуры из массива рандомным коофициентом от 0 до 1
//        - сортировка фигур по площади
//        "