package figur;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        double a, b, a1, b1, r, r1, area = 0;
        int step = 0,k;
        double[] array  = new double[10];
        double[] array1  = new double[10];
        int j=0;
        for (int i = 0; i < 10; i++) {
            a = Math.random() * 10;   // Основание треугольника
            b = Math.random() * 10;   //Высота треугольника
            a1 = Math.random() * 10;  //Высота прямоугольника
            b1 = Math.random() * 10;  //Ширина прямоугольника
            //r= random.nextInt (100);   //Радиус круга
            r = Math.random() * 10;
            r1 = r;                      //Радиус круга
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
                    area = triangle.area(a, b);
                    System.out.println("Площадь треугольника = " + area);
                    array[j]=Math.rint(100.0 * area) / 100.0;
                    array1[j]=triangle.resize(a,b);
//                    array1[j]=Math.rint(100.0 * area* Math.random()) / 100.0;
                    j++;
                    break;
                case 1:
                    Rectangle rec = new Rectangle();
                    area = rec.area(a1, b1);
                    System.out.println("Площадь прямоугольника = " + area);
                    //System.out.println("Площадь прямоугольника = " + rec.resize(a1, b1));
                    array[j]=Math.rint(100.0 * area) / 100.0;
                    array1[j]=rec.resize(a1,b1);
//                    array1[j]=Math.rint(100.0 * area* Math.random()) / 100.0;
                    j++;
                    break;
                case 2:
                    Circle circle = new Circle();
                    area = circle.area(r, r1);
                    System.out.println("Площадь круга = " + area);
                    array[j]=Math.rint(100.0 * area) / 100.0;
                    array1[j]=circle.resize(r,r1);
//                    array1[j]=Math.rint(100.0 * area* Math.random()) / 100.0;
                    j++;
                    break;
            }
        }
        Arrays.sort( array );
        System.out.print("Расставим площади фигур по возрастанию: " );
        for (k=0;k<10;k++) {
            System.out.print(array[k]+ " ");
        }
        Arrays.sort( array1 );
        System.out.println();
        System.out.print("Расставим площади фигур c рандомно измененным размером по возрастанию: " );
        for (k=0;k<10;k++) {
            System.out.print(array1[k]+ " ");
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