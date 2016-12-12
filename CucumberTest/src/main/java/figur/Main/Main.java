package Main;

import figur.Circle;
import figur.Figures;
import figur.Rectangle;
import figur.Triangle;

public class Main {
    static Figures[] massFigures = new Figures[10];

    public static void main(String[] args) {
        int step = 0;
        int j = 0;
        //Создаем массив рандомных фигур
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
                    massFigures[i] = new Triangle(
                            Math.random() * 10 + 10,
                            Math.random() * 10 + 10,
                            Math.random() * 10 + 10,
                            Math.random() * 10 + 10);
                    break;
                case 1:
                    massFigures[i] = new Rectangle(
                            Math.random() * 10 + 10,
                            Math.random() * 10 + 10,
                            Math.random() * 10 + 10,
                            Math.random() * 10 + 10);
                    break;
                case 2:
                    massFigures[i] = new Circle(
                            Math.random() * 10 + 10,
                            Math.random() * 10 + 10,
                            Math.random() * 10 + 10);
                    break;
            }
        }

//Ресайзим параметры фигур
        for (int i = 0; i < 10; i++) {
            massFigures[i].resize(Math.random());
        }

//Чуть подвинем фигуры
        for (int i = 0; i < 10; i++) {
            massFigures[i].move(Math.random(), Math.random());
        }

        //Сортируем
        for (int i = 9; i >= 0; i--) {
            for (j = 0; j < i; j++) {
                if (massFigures[j].area() < massFigures[j + 1].area()) {
                    Figures buferFigure = massFigures[j];
                    massFigures[j] = massFigures[j + 1];
                    massFigures[j + 1] = buferFigure;
                }
            }
            System.out.println(massFigures[j].getT() + " " +
                    "x= " + Math.rint(massFigures[i].getX() * 100) / 100 + " " +
                    "y= " + Math.rint(massFigures[i].getY() * 100) / 100 + " " +
                    "a= " + Math.rint(massFigures[j].getA() * 100) / 100 + " " +
                    "b= " + Math.rint(massFigures[j].getB() * 100) / 100 + " " +
                    "area= " + massFigures[j].area() + " ");
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