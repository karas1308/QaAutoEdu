@Circle
Feature: TestCircle

#  //Проверяем вычисление плодащи круга, радиусом 1
  @Non-static
  Scenario: 1 AreaCircle_r1
	Given I calculate area of circle
	When I check area of circle "3.14"

#	Для радиуса =0 не прилетает null
  @Non-static
  Scenario: 2 AreaCircle_notNull
	Given I calculate area of circle1
	When I check area of circle1 null

#	Смещаем координаты центра на 10
  @Non-static
  Scenario Outline: 3 MoveCircle_10_10
	Given I move center  x plus <x>, y plus <y>
	When  After move x equals <newx>, y equals <newy>
	Examples:
	  |x |y  |newx|newy|
	  |10|10 |20  |20  |
	  |0 |0  |10  |10  |
	  |30|30 |40  |40  |

#  Проверим, что с увеличением стороны увеличится площадь
  @Non-static
  Scenario: 4 ResizeCircle_x2
	Given I Increased radius of circle by 2 times
	When I calculate area of circle with new radius
	Then The area is more than 12

#  //Уменьшим радиус круга
  @Non-static
  Scenario: 5 ResizeCircle_x09
	Given I decreased radius of circle by "0.9" times
	Then The area is less than 1
