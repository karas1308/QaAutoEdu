@Circle
Feature: TestCircle
#  //Проверяем вычисление плодащи круга, радиусом 1
  @Non-static
  Scenario: 1 AreaCircle_r1
	Given I Create circle with radius 1
	And I calculate area of circle
	When I check area of circle "3.14"
#	Смещаем координаты центра на 10
  @Non-static
  Scenario Outline: 3 MoveCircle_10_10
	Given I Create circle with coordinates 10 10
	And I move center x plus <x>, y plus <y>
	Then After move x equals <newx>, y equals <newy>
	Examples:
	  | x  | y  | newx | newy |
	  | 10 | 10 | 20   | 20   |
	  | 0  | 0  | 10   | 10   |
	  | 30 | 30 | 40   | 40   |
#  Проверим, что с увеличением стороны увеличится площадь
  @Non-static
  Scenario: 4 ResizeCircle_x2
	Given I Create circle with radius 1
	And I change radius of circle by "2" times
	When I calculate area of circle
	Then The area is more than 12
#  //Уменьшим радиус круга
  @Non-static
  Scenario: 5 ResizeCircle_x09
	Given I Create circle with radius 1
	And I change radius of circle by "0.9" times
	Then The area is less than 1
