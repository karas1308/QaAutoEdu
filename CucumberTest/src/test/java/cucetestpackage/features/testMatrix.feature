@Matrix

Feature: Matrix

  Scenario: 01 TestMatix1

	Given Input "6" matrix size
	When I looking for "0" "5" element
	Then Tenth element isEquals "6"

	#  //Проверяем, чтоб в матрице максимальный элемент был 9
  Scenario: 1 MaxElemMatr_8x8
	Given Input matrix size 8
	When I looking for element more than 9
	Then Alarm because I found elenemt more than 9

#  //Количество элеметов матрицы
  Scenario: 2 CountElemMatr_7x7
	Given Input size 7
	And Count elements
	Then Count of elemets equals 49

#  Последняя строка матрицы не равна 789
  Scenario: LastLine_789_3x3
  Given Input size matrix 3
  When I looking for all elements of line 3
  Then Last line equals "789"

