$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("testCircle.feature");
formatter.feature({
  "line": 2,
  "name": "TestCircle",
  "description": "",
  "id": "testcircle",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Circle"
    }
  ]
});
formatter.scenario({
  "comments": [
    {
      "line": 4,
      "value": "#  //Проверяем вычисление плодащи круга, радиусом 1"
    }
  ],
  "line": 5,
  "name": "1 AreaCircle_r1",
  "description": "",
  "id": "testcircle;1-areacircle-r1",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 6,
  "name": "I calculate area of circle",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I check area of circle \"3.14\"",
  "keyword": "When "
});
formatter.match({
  "location": "StepTestCircle.iCalculateAreaOfCircle()"
});
formatter.result({
  "duration": 596322826,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3.14",
      "offset": 24
    }
  ],
  "location": "StepTestCircle.iCheckAreaOfCircle(String)"
});
formatter.result({
  "duration": 12759065,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 9,
      "value": "#\tДля радиуса \u003d0 не прилетает null"
    }
  ],
  "line": 10,
  "name": "2 AreaCircle_notNull",
  "description": "",
  "id": "testcircle;2-areacircle-notnull",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 11,
  "name": "I calculate area of circle1",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "I check area of circle1 null",
  "keyword": "When "
});
formatter.match({
  "location": "StepTestCircle.iCalculateAreaOfCircle1()"
});
formatter.result({
  "duration": 264728,
  "status": "passed"
});
formatter.match({
  "location": "StepTestCircle.iCheckAreaOfCircle1()"
});
formatter.result({
  "duration": 127922,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 14,
      "value": "#\tСмещаем координаты центра на 10"
    }
  ],
  "line": 15,
  "name": "3 MoveCircle_10_10",
  "description": "",
  "id": "testcircle;3-movecircle-10-10",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 16,
  "name": "I move center  x plus 10, y plus 10",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "After move x equals 20, y equals 20",
  "keyword": "When "
});
formatter.match({
  "arguments": [
    {
      "val": "10",
      "offset": 22
    },
    {
      "val": "10",
      "offset": 33
    }
  ],
  "location": "StepTestCircle.iMoveCenterXPlusYPlus(int,int)"
});
formatter.result({
  "duration": 2325106,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "20",
      "offset": 20
    },
    {
      "val": "20",
      "offset": 33
    }
  ],
  "location": "StepTestCircle.afterMoveXEqualsYEquals(int,int)"
});
formatter.result({
  "duration": 3573533,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 19,
      "value": "#  Проверим, что с увеличением стороны увеличится площадь"
    }
  ],
  "line": 20,
  "name": "4 ResizeCircle_x2",
  "description": "",
  "id": "testcircle;4-resizecircle-x2",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 21,
  "name": "I Increased radius of circle by 2 times",
  "keyword": "Given "
});
formatter.step({
  "line": 22,
  "name": "I calculate area of circle with new radius",
  "keyword": "When "
});
formatter.step({
  "line": 23,
  "name": "The area is more than 12",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 32
    }
  ],
  "location": "StepTestCircle.iIncreasedRadiusOfCircleByTimes(int)"
});
formatter.result({
  "duration": 458389,
  "status": "passed"
});
formatter.match({
  "location": "StepTestCircle.iCalculateAreaOfCircleWithNewRadius()"
});
formatter.result({
  "duration": 213203,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "12",
      "offset": 22
    }
  ],
  "location": "StepTestCircle.theAreaIsMoreThan(int)"
});
formatter.result({
  "duration": 302631,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 25,
      "value": "#  //Уменьшим радиус круга"
    }
  ],
  "line": 26,
  "name": "5 ResizeCircle_x09",
  "description": "",
  "id": "testcircle;5-resizecircle-x09",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 27,
  "name": "I decreased radius of circle by \"0.9\" times",
  "keyword": "Given "
});
formatter.step({
  "line": 28,
  "name": "The area is less than 1",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "0.9",
      "offset": 33
    }
  ],
  "location": "StepTestCircle.iDecreasedRadiusOfCircleByTimes(String)"
});
formatter.result({
  "duration": 1591330,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 22
    }
  ],
  "location": "StepTestCircle.theAreaIsLessThan(int)"
});
formatter.result({
  "duration": 485039,
  "status": "passed"
});
formatter.uri("testMatrix.feature");
formatter.feature({
  "line": 3,
  "name": "Matrix",
  "description": "",
  "id": "matrix",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Matrix"
    }
  ]
});
formatter.scenario({
  "line": 5,
  "name": "01 TestMatix1",
  "description": "",
  "id": "matrix;01-testmatix1",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "Input \"6\" matrix size",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I looking for \"0\" \"5\" element",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "Tenth element isEquals \"6\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "6",
      "offset": 7
    }
  ],
  "location": "StepTestMatrix.inputMatrixSize(String)"
});
formatter.result({
  "duration": 1721028,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 15
    },
    {
      "val": "5",
      "offset": 19
    }
  ],
  "location": "StepTestMatrix.i_looking_for_element(String,String)"
});
formatter.result({
  "duration": 424038,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "6",
      "offset": 24
    }
  ],
  "location": "StepTestMatrix.tenth_element_isEquals(String)"
});
formatter.result({
  "duration": 303223,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 11,
      "value": "#  //Проверяем, чтоб в матрице максимальный элемент был 9"
    }
  ],
  "line": 12,
  "name": "1 MaxElemMatr_8x8",
  "description": "",
  "id": "matrix;1-maxelemmatr-8x8",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 13,
  "name": "Input matrix size 8",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "I looking for element more than 9",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "Alarm because I found elenemt more than 9",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "8",
      "offset": 18
    }
  ],
  "location": "StepTestMatrix.inputMatrixSize(int)"
});
formatter.result({
  "duration": 567359,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "9",
      "offset": 32
    }
  ],
  "location": "StepTestMatrix.iLookingForElementMoreThan(int)"
});
formatter.result({
  "duration": 222679,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "9",
      "offset": 40
    }
  ],
  "location": "StepTestMatrix.alarmBecauseIFoundElenemtMoreThan(int)"
});
formatter.result({
  "duration": 294932,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 17,
      "value": "#  //Количество элеметов матрицы"
    }
  ],
  "line": 18,
  "name": "2 CountElemMatr_7x7",
  "description": "",
  "id": "matrix;2-countelemmatr-7x7",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 19,
  "name": "Input size 7",
  "keyword": "Given "
});
formatter.step({
  "line": 20,
  "name": "Count elements",
  "keyword": "And "
});
formatter.step({
  "line": 21,
  "name": "Count of elemets equals 49",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "7",
      "offset": 11
    }
  ],
  "location": "StepTestMatrix.inputSize(int)"
});
formatter.result({
  "duration": 452466,
  "status": "passed"
});
formatter.match({
  "location": "StepTestMatrix.countElements()"
});
formatter.result({
  "duration": 90612,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "49",
      "offset": 24
    }
  ],
  "location": "StepTestMatrix.countOfElemetsEquals(int)"
});
formatter.result({
  "duration": 12711687,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 23,
      "value": "#  Последняя строка матрицы не равна 789"
    }
  ],
  "line": 24,
  "name": "LastLine_789_3x3",
  "description": "",
  "id": "matrix;lastline-789-3x3",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 25,
  "name": "Input size matrix 3",
  "keyword": "Given "
});
formatter.step({
  "line": 26,
  "name": "I looking for all elements of line 3",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "Last line equals \"789\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 18
    }
  ],
  "location": "StepTestMatrix.inputSizeMatrix(int)"
});
formatter.result({
  "duration": 366592,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3",
      "offset": 35
    }
  ],
  "location": "StepTestMatrix.iLookingForAllElementsOfLine(int)"
});
formatter.result({
  "duration": 273019,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "789",
      "offset": 18
    }
  ],
  "location": "StepTestMatrix.lastLineEquals(String)"
});
formatter.result({
  "duration": 344087,
  "status": "passed"
});
});