@Rectangle
Feature: Rectangle
#  Площадь прямоугольника 2x3=6
  @Non-static
  Scenario: 1 Area of Rectangle_2_3
	Given Стороны прямоугольника равны "2" и "3"
	Then Площадь прямоугольника равна 6
   #  Площадь прямоугольника 5x6=30
  @Non-static
  Scenario: 2 Area of Rectangle_5_6
	Given Стороны прямоугольника равны "5" и "6"
	Then Площадь такого прямоугольника равна 30
# Смещение начальной точки прямоугольника
  @Non-static
  Scenario: 3 TestMoveRect_10_10
	Given Создадим прямоугольник с начальными координатами 0 10
	And Изменим координату x на 10, y на 10
	When Найдем координаты после смещения
	Then Сравним, равен ли x 10, а y 20
#	Изменение размера сторон прямоугольника
  @Non-static
  Scenario: 4 testResizeRect_5
	Given Стороны прямоугольника равны "2" и "3"
	And Увеличим стороны прямоугольника в 5 раз
	When Найдем размер сторон после изменения
	Then Проверим, равна ли сторона а 10, b 15


