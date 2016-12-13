@Circle
Feature: TestCircle

#  //Проверяем вычисление плодащи круга, радиусом 1
  Scenario: 1 AreaCircle_r1
	Given I calculate area of circle
	When I check area of circle "3.14"

#	Для радиуса =0 не прилетает null
  Scenario: 2 AreaCircle_notNull
	Given I calculate area of circle1
	When I check area of circle1 null

#	Смещаем координаты центра на 10
  Scenario: 3 MoveCircle_10_10
	Given I move center  x plus 10, y plus 10
	When  After move x equals 20, y equals 20

#  Проверим, что с увеличением стороны увеличится площадь
  Scenario: 4 ResizeCircle_x2
	Given I Increased radius of circle by 2 times
	When I calculate area of circle with new radius
	Then The area is more than 12

#  //Уменьшим радиус круга
  Scenario: 5 ResizeCircle_x09
	Given I decreased radius of circle by "0.9" times
	Then The area is less than 1
