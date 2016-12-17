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
      "line": 3,
      "value": "#  //Проверяем вычисление плодащи круга, радиусом 1"
    }
  ],
  "line": 5,
  "name": "1 AreaCircle_r1",
  "description": "",
  "id": "testcircle;1-areacircle-r1",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "I Create circle with radius 1",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "I calculate area of circle",
  "keyword": "And "
});
formatter.step({
  "line": 8,
  "name": "I check area of circle \"3.14\"",
  "keyword": "When "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 28
    }
  ],
  "location": "StepTestCircle.iCreateCircleWithRadius(int)"
});
formatter.result({
  "duration": 421903732,
  "status": "passed"
});
formatter.match({
  "location": "StepTestCircle.iCalculateAreaOfCircle()"
});
formatter.result({
  "duration": 111340,
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
  "duration": 9464474,
  "status": "passed"
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 9,
      "value": "#\tСмещаем координаты центра на 10"
    }
  ],
  "line": 11,
  "name": "3 MoveCircle_10_10",
  "description": "",
  "id": "testcircle;3-movecircle-10-10",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 10,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 12,
  "name": "I Create circle with coordinates 10 10",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I move center x plus \u003cx\u003e, y plus \u003cy\u003e",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "After move x equals \u003cnewx\u003e, y equals \u003cnewy\u003e",
  "keyword": "Then "
});
formatter.examples({
  "line": 15,
  "name": "",
  "description": "",
  "id": "testcircle;3-movecircle-10-10;",
  "rows": [
    {
      "cells": [
        "x",
        "y",
        "newx",
        "newy"
      ],
      "line": 16,
      "id": "testcircle;3-movecircle-10-10;;1"
    },
    {
      "cells": [
        "10",
        "10",
        "20",
        "20"
      ],
      "line": 17,
      "id": "testcircle;3-movecircle-10-10;;2"
    },
    {
      "cells": [
        "0",
        "0",
        "10",
        "10"
      ],
      "line": 18,
      "id": "testcircle;3-movecircle-10-10;;3"
    },
    {
      "cells": [
        "30",
        "30",
        "40",
        "40"
      ],
      "line": 19,
      "id": "testcircle;3-movecircle-10-10;;4"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 17,
  "name": "3 MoveCircle_10_10",
  "description": "",
  "id": "testcircle;3-movecircle-10-10;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Circle"
    },
    {
      "line": 10,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 12,
  "name": "I Create circle with coordinates 10 10",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I move center x plus 10, y plus 10",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "After move x equals 20, y equals 20",
  "matchedColumns": [
    2,
    3
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "10",
      "offset": 33
    },
    {
      "val": "10",
      "offset": 36
    }
  ],
  "location": "StepTestCircle.iCreateCircleWithCoordinates(int,int)"
});
formatter.result({
  "duration": 414563,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "10",
      "offset": 21
    },
    {
      "val": "10",
      "offset": 32
    }
  ],
  "location": "StepTestCircle.iMoveCenterXPlusYPlus(int,int)"
});
formatter.result({
  "duration": 330466,
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
  "duration": 4476096,
  "status": "passed"
});
formatter.scenario({
  "line": 18,
  "name": "3 MoveCircle_10_10",
  "description": "",
  "id": "testcircle;3-movecircle-10-10;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Circle"
    },
    {
      "line": 10,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 12,
  "name": "I Create circle with coordinates 10 10",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I move center x plus 0, y plus 0",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "After move x equals 10, y equals 10",
  "matchedColumns": [
    2,
    3
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "10",
      "offset": 33
    },
    {
      "val": "10",
      "offset": 36
    }
  ],
  "location": "StepTestCircle.iCreateCircleWithCoordinates(int,int)"
});
formatter.result({
  "duration": 2257000,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 21
    },
    {
      "val": "0",
      "offset": 31
    }
  ],
  "location": "StepTestCircle.iMoveCenterXPlusYPlus(int,int)"
});
formatter.result({
  "duration": 252292,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "10",
      "offset": 20
    },
    {
      "val": "10",
      "offset": 33
    }
  ],
  "location": "StepTestCircle.afterMoveXEqualsYEquals(int,int)"
});
formatter.result({
  "duration": 291378,
  "status": "passed"
});
formatter.scenario({
  "line": 19,
  "name": "3 MoveCircle_10_10",
  "description": "",
  "id": "testcircle;3-movecircle-10-10;;4",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Circle"
    },
    {
      "line": 10,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 12,
  "name": "I Create circle with coordinates 10 10",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "I move center x plus 30, y plus 30",
  "matchedColumns": [
    0,
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "After move x equals 40, y equals 40",
  "matchedColumns": [
    2,
    3
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "10",
      "offset": 33
    },
    {
      "val": "10",
      "offset": 36
    }
  ],
  "location": "StepTestCircle.iCreateCircleWithCoordinates(int,int)"
});
formatter.result({
  "duration": 341126,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "30",
      "offset": 21
    },
    {
      "val": "30",
      "offset": 32
    }
  ],
  "location": "StepTestCircle.iMoveCenterXPlusYPlus(int,int)"
});
formatter.result({
  "duration": 988437,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "40",
      "offset": 20
    },
    {
      "val": "40",
      "offset": 33
    }
  ],
  "location": "StepTestCircle.afterMoveXEqualsYEquals(int,int)"
});
formatter.result({
  "duration": 280126,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 20,
      "value": "#  Проверим, что с увеличением стороны увеличится площадь"
    }
  ],
  "line": 22,
  "name": "4 ResizeCircle_x2",
  "description": "",
  "id": "testcircle;4-resizecircle-x2",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 21,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 23,
  "name": "I Create circle with radius 1",
  "keyword": "Given "
});
formatter.step({
  "line": 24,
  "name": "I change radius of circle by \"2\" times",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "I calculate area of circle",
  "keyword": "When "
});
formatter.step({
  "line": 26,
  "name": "The area is more than 12",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 28
    }
  ],
  "location": "StepTestCircle.iCreateCircleWithRadius(int)"
});
formatter.result({
  "duration": 261175,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 30
    }
  ],
  "location": "StepTestCircle.iChangeRadiusOfCircleByTimes(String)"
});
formatter.result({
  "duration": 197213,
  "status": "passed"
});
formatter.match({
  "location": "StepTestCircle.iCalculateAreaOfCircle()"
});
formatter.result({
  "duration": 65738,
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
  "duration": 367184,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 27,
      "value": "#  //Уменьшим радиус круга"
    }
  ],
  "line": 29,
  "name": "5 ResizeCircle_x09",
  "description": "",
  "id": "testcircle;5-resizecircle-x09",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 28,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 30,
  "name": "I Create circle with radius 1",
  "keyword": "Given "
});
formatter.step({
  "line": 31,
  "name": "I change radius of circle by \"0.9\" times",
  "keyword": "And "
});
formatter.step({
  "line": 32,
  "name": "The area is less than 1",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 28
    }
  ],
  "location": "StepTestCircle.iCreateCircleWithRadius(int)"
});
formatter.result({
  "duration": 285456,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0.9",
      "offset": 30
    }
  ],
  "location": "StepTestCircle.iChangeRadiusOfCircleByTimes(String)"
});
formatter.result({
  "duration": 178854,
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
  "duration": 254068,
  "status": "passed"
});
formatter.uri("testMatrix.feature");
formatter.feature({
  "line": 2,
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
  "line": 4,
  "name": "01 TestMatix1",
  "description": "",
  "id": "matrix;01-testmatix1",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "Input \"6\" matrix size",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "I looking for \"0\" \"5\" element",
  "keyword": "When "
});
formatter.step({
  "line": 7,
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
  "duration": 1598437,
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
  "duration": 438252,
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
  "duration": 271243,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 8,
      "value": "#  //Проверяем, чтоб в матрице максимальный элемент был 9"
    }
  ],
  "line": 9,
  "name": "1 MaxElemMatr_8x8",
  "description": "",
  "id": "matrix;1-maxelemmatr-8x8",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "Input matrix size 8",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "I looking for element more than 9",
  "keyword": "When "
});
formatter.step({
  "line": 12,
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
  "location": "StepTestMatrix.inputSizeMatrix(int)"
});
formatter.result({
  "duration": 296116,
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
  "duration": 224456,
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
  "duration": 360077,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 13,
      "value": "#  //Количество элеметов матрицы"
    }
  ],
  "line": 14,
  "name": "2 CountElemMatr_7x7",
  "description": "",
  "id": "matrix;2-countelemmatr-7x7",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 15,
  "name": "Input matrix size 7",
  "keyword": "Given "
});
formatter.step({
  "line": 16,
  "name": "Count elements",
  "keyword": "And "
});
formatter.step({
  "line": 17,
  "name": "Count of elemets equals 49",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "7",
      "offset": 18
    }
  ],
  "location": "StepTestMatrix.inputSizeMatrix(int)"
});
formatter.result({
  "duration": 301447,
  "status": "passed"
});
formatter.match({
  "location": "StepTestMatrix.countElements()"
});
formatter.result({
  "duration": 564990,
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
  "duration": 244000,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 18,
      "value": "#  Последняя строка матрицы не равна 789"
    }
  ],
  "line": 19,
  "name": "LastLine_789_3x3",
  "description": "",
  "id": "matrix;lastline-789-3x3",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 20,
  "name": "Input matrix size 3",
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "I looking for all elements of line 3",
  "keyword": "When "
});
formatter.step({
  "line": 22,
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
  "duration": 262359,
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
  "duration": 240447,
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
  "duration": 262359,
  "status": "passed"
});
formatter.uri("testPalindrom.feature");
formatter.feature({
  "line": 2,
  "name": "Palindrom",
  "description": "",
  "id": "palindrom",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Palindrom"
    }
  ]
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "#  Проверим, что слово - палиндром"
    }
  ],
  "line": 4,
  "name": "1 testСheckWordTrue",
  "description": "",
  "id": "palindrom;1-testсheckwordtrue",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "Введем текст \"q1qq1q\"",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Узнаем палиндром ли это",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Что-то напишем, если вдруг ошибка",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "q1qq1q",
      "offset": 14
    }
  ],
  "location": "StepTestPalindrom.введемТекст(String)"
});
formatter.result({
  "duration": 382583,
  "status": "passed"
});
formatter.match({
  "location": "StepTestPalindrom.узнаемПалиндромЛиЭто()"
});
formatter.result({
  "duration": 1530922,
  "status": "passed"
});
formatter.match({
  "location": "StepTestPalindrom.чтоТоНапишемЕслиВдругОшибка()"
});
formatter.result({
  "duration": 162864,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 8,
      "value": "#Проверим, что слово не палиндром"
    }
  ],
  "line": 9,
  "name": "2 testСheckWordFalse",
  "description": "",
  "id": "palindrom;2-testсheckwordfalse",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "Введем текст \"Q1qqQ\"",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "Узнаем палиндром ли это",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "Сообщим об ошибке",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Q1qqQ",
      "offset": 14
    }
  ],
  "location": "StepTestPalindrom.введемТекст(String)"
});
formatter.result({
  "duration": 211427,
  "status": "passed"
});
formatter.match({
  "location": "StepTestPalindrom.узнаемПалиндромЛиЭто()"
});
formatter.result({
  "duration": 143912,
  "status": "passed"
});
formatter.match({
  "location": "StepTestPalindrom.сообщимОбОшибке()"
});
formatter.result({
  "duration": 127922,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 13,
      "value": "#\t  Проверим, что фраза палиндром"
    }
  ],
  "line": 14,
  "name": "3 testcheckPhraseTrue",
  "description": "",
  "id": "palindrom;3-testcheckphrasetrue",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 15,
  "name": "Введем фразу-палиндром \"Qwert asd d sa trew q\"",
  "keyword": "Given "
});
formatter.step({
  "line": 16,
  "name": "Узнаем палиндром ли эта фраза",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "Что-то напишем, если вдруг ошибка",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Qwert asd d sa trew q",
      "offset": 24
    }
  ],
  "location": "StepTestPalindrom.введемФразуПалиндром(String)"
});
formatter.result({
  "duration": 225641,
  "status": "passed"
});
formatter.match({
  "location": "StepTestPalindrom.узнаемПалиндромЛиЭтаФраза()"
});
formatter.result({
  "duration": 201951,
  "status": "passed"
});
formatter.match({
  "location": "StepTestPalindrom.чтоТоНапишемЕслиВдругОшибка()"
});
formatter.result({
  "duration": 86466,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 18,
      "value": "#\t  Проверим, что фраза палиндром"
    }
  ],
  "line": 19,
  "name": "4 testcheckPhraseFalse",
  "description": "",
  "id": "palindrom;4-testcheckphrasefalse",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 20,
  "name": "Введем фразу-палиндром \"Qwert asd d sa trew q1\"",
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "Узнаем палиндром ли эта фраза",
  "keyword": "When "
});
formatter.step({
  "line": 22,
  "name": "Сообщим об ошибке",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Qwert asd d sa trew q1",
      "offset": 24
    }
  ],
  "location": "StepTestPalindrom.введемФразуПалиндром(String)"
});
formatter.result({
  "duration": 216165,
  "status": "passed"
});
formatter.match({
  "location": "StepTestPalindrom.узнаемПалиндромЛиЭтаФраза()"
});
formatter.result({
  "duration": 150427,
  "status": "passed"
});
formatter.match({
  "location": "StepTestPalindrom.сообщимОбОшибке()"
});
formatter.result({
  "duration": 76398,
  "status": "passed"
});
formatter.uri("testRectangle.feature");
formatter.feature({
  "line": 2,
  "name": "Rectangle",
  "description": "",
  "id": "rectangle",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Rectangle"
    }
  ]
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "#  Площадь прямоугольника 2x3\u003d6"
    }
  ],
  "line": 5,
  "name": "1 Area of Rectangle_2_3",
  "description": "",
  "id": "rectangle;1-area-of-rectangle-2-3",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 4,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 6,
  "name": "Стороны прямоугольника равны \"2\" и \"3\"",
  "keyword": "Given "
});
formatter.step({
  "line": 7,
  "name": "Площадь прямоугольника равна 6",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 30
    },
    {
      "val": "3",
      "offset": 36
    }
  ],
  "location": "StepTestRect.стороныПрямоугольникаРавныИ(String,String)"
});
formatter.result({
  "duration": 2012407,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "6",
      "offset": 29
    }
  ],
  "location": "StepTestRect.площадьПрямоугольникаРавна(int)"
});
formatter.result({
  "duration": 297893,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 8,
      "value": "#  Площадь прямоугольника 5x6\u003d30"
    }
  ],
  "line": 10,
  "name": "2 Area of Rectangle_5_6",
  "description": "",
  "id": "rectangle;2-area-of-rectangle-5-6",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 9,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 11,
  "name": "Стороны прямоугольника равны \"5\" и \"6\"",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "Площадь такого прямоугольника равна 30",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "5",
      "offset": 30
    },
    {
      "val": "6",
      "offset": 36
    }
  ],
  "location": "StepTestRect.стороныПрямоугольникаРавныИ(String,String)"
});
formatter.result({
  "duration": 274204,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "30",
      "offset": 36
    }
  ],
  "location": "StepTestRect.площадьТакогоПрямоугольникаРавна(int)"
});
formatter.result({
  "duration": 272427,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 13,
      "value": "# Смещение начальной точки прямоугольника"
    }
  ],
  "line": 15,
  "name": "3 TestMoveRect_10_10",
  "description": "",
  "id": "rectangle;3-testmoverect-10-10",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 14,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "Создадим прямоугольник с начальными координатами 0 10",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "Изменим координату x на 10, y на 10",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "Найдем координаты после смещения",
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "Сравним, равен ли x 10, а y 20",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 49
    },
    {
      "val": "10",
      "offset": 51
    }
  ],
  "location": "StepTestRect.создадимПрямоугольникСНачальнымиКоординатами(double,double)"
});
formatter.result({
  "duration": 571505,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "10",
      "offset": 24
    },
    {
      "val": "10",
      "offset": 33
    }
  ],
  "location": "StepTestRect.изменимКоординатуXНаYНа(int,int)"
});
formatter.result({
  "duration": 3767785,
  "status": "passed"
});
formatter.match({
  "location": "StepTestRect.найдемКоординатыПослеСмещения()"
});
formatter.result({
  "duration": 115485,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "10",
      "offset": 20
    },
    {
      "val": "20",
      "offset": 28
    }
  ],
  "location": "StepTestRect.сравнимРавенЛиXАY(int,int)"
});
formatter.result({
  "duration": 308553,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 20,
      "value": "#\tИзменение размера сторон прямоугольника"
    }
  ],
  "line": 22,
  "name": "4 testResizeRect_5",
  "description": "",
  "id": "rectangle;4-testresizerect-5",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 21,
      "name": "@Non-static"
    }
  ]
});
formatter.step({
  "line": 23,
  "name": "Стороны прямоугольника равны \"2\" и \"3\"",
  "keyword": "Given "
});
formatter.step({
  "line": 24,
  "name": "Увеличим стороны прямоугольника в 5 раз",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "Найдем размер сторон после изменения",
  "keyword": "When "
});
formatter.step({
  "line": 26,
  "name": "Проверим, равна ли сторона а 10, b 15",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "2",
      "offset": 30
    },
    {
      "val": "3",
      "offset": 36
    }
  ],
  "location": "StepTestRect.стороныПрямоугольникаРавныИ(String,String)"
});
formatter.result({
  "duration": 270650,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "5",
      "offset": 34
    }
  ],
  "location": "StepTestRect.увеличимСтороныПрямоугольникаВРаз(int)"
});
formatter.result({
  "duration": 11243541,
  "status": "passed"
});
formatter.match({
  "location": "StepTestRect.найдесРазмерСторонПослеИзменения()"
});
formatter.result({
  "duration": 97127,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "10",
      "offset": 29
    },
    {
      "val": "15",
      "offset": 35
    }
  ],
  "location": "StepTestRect.проверимРавнаЛиСторонаАB(double,double)"
});
formatter.result({
  "duration": 376068,
  "status": "passed"
});
formatter.uri("testSnail.feature");
formatter.feature({
  "line": 2,
  "name": "Snail",
  "description": "",
  "id": "snail",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@Snail"
    }
  ]
});
formatter.scenario({
  "comments": [
    {
      "line": 3,
      "value": "#Элемент [0][4] матрицы размером 5х5 \u003d 25"
    }
  ],
  "line": 4,
  "name": "1 testElem1and5nail",
  "description": "",
  "id": "snail;1-testelem1and5nail",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 5,
  "name": "Размер матрицы 5",
  "keyword": "Given "
});
formatter.step({
  "line": 6,
  "name": "Запишем матрицу в массив",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Проверим, равен ли последний элемент в перввой строке 25",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "5",
      "offset": 15
    }
  ],
  "location": "StepTestSnail.размерМатрицы(int)"
});
formatter.result({
  "duration": 383767,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив()"
});
formatter.result({
  "duration": 1743533,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "25",
      "offset": 54
    }
  ],
  "location": "StepTestSnail.проверимРавенЛиПоследнийЭлементВПерввойСтроке(int)"
});
formatter.result({
  "duration": 326913,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 8,
      "value": "#Элемент [5][5] матрицы размером 6х6 \u003d 31"
    }
  ],
  "line": 9,
  "name": "2 testElem6and6Snail",
  "description": "",
  "id": "snail;2-testelem6and6snail",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "Размер матрицы 6",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "Запишем матрицу в массив",
  "keyword": "When "
});
formatter.step({
  "line": 12,
  "name": "Проверим, равен ли последний элемент в последней строке 31",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "6",
      "offset": 15
    }
  ],
  "location": "StepTestSnail.размерМатрицы(int)"
});
formatter.result({
  "duration": 249330,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив()"
});
formatter.result({
  "duration": 451874,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "31",
      "offset": 56
    }
  ],
  "location": "StepTestSnail.проверимРавенЛиПоследнийЭлементВПоследнейСтроке(int)"
});
formatter.result({
  "duration": 305593,
  "status": "passed"
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 13,
      "value": "#Максимальное значение в матрице 7х7"
    }
  ],
  "line": 15,
  "name": "3 testMaxElemSnail",
  "description": "",
  "id": "snail;3-testmaxelemsnail",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 14,
      "name": "@SnailSpecial"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "Размер матрицы \u003csize\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "Запишем матрицу в массив",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "Найдем самой самое большое значение",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "Максимальное значение \u003cmax\u003e",
  "keyword": "Then "
});
formatter.examples({
  "line": 20,
  "name": "",
  "description": "",
  "id": "snail;3-testmaxelemsnail;",
  "rows": [
    {
      "cells": [
        "size",
        "max"
      ],
      "line": 21,
      "id": "snail;3-testmaxelemsnail;;1"
    },
    {
      "cells": [
        "7",
        "49"
      ],
      "line": 22,
      "id": "snail;3-testmaxelemsnail;;2"
    },
    {
      "cells": [
        "4",
        "16"
      ],
      "line": 23,
      "id": "snail;3-testmaxelemsnail;;3"
    },
    {
      "cells": [
        "8",
        "64"
      ],
      "line": 24,
      "id": "snail;3-testmaxelemsnail;;4"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 22,
  "name": "3 testMaxElemSnail",
  "description": "",
  "id": "snail;3-testmaxelemsnail;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Snail"
    },
    {
      "line": 14,
      "name": "@SnailSpecial"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "Размер матрицы 7",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "Запишем матрицу в массив",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "Найдем самой самое большое значение",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "Максимальное значение 49",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "7",
      "offset": 15
    }
  ],
  "location": "StepTestSnail.размерМатрицы(int)"
});
formatter.result({
  "duration": 305000,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив()"
});
formatter.result({
  "duration": 651456,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.найдемСамойСамоеБольшоеЗначение()"
});
formatter.result({
  "duration": 136214,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "49",
      "offset": 22
    }
  ],
  "location": "StepTestSnail.максимальноеЗначение(int)"
});
formatter.result({
  "duration": 329874,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "3 testMaxElemSnail",
  "description": "",
  "id": "snail;3-testmaxelemsnail;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Snail"
    },
    {
      "line": 14,
      "name": "@SnailSpecial"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "Размер матрицы 4",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "Запишем матрицу в массив",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "Найдем самой самое большое значение",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "Максимальное значение 16",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "4",
      "offset": 15
    }
  ],
  "location": "StepTestSnail.размерМатрицы(int)"
});
formatter.result({
  "duration": 252883,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив()"
});
formatter.result({
  "duration": 281903,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.найдемСамойСамоеБольшоеЗначение()"
});
formatter.result({
  "duration": 221495,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "16",
      "offset": 22
    }
  ],
  "location": "StepTestSnail.максимальноеЗначение(int)"
});
formatter.result({
  "duration": 255253,
  "status": "passed"
});
formatter.scenario({
  "line": 24,
  "name": "3 testMaxElemSnail",
  "description": "",
  "id": "snail;3-testmaxelemsnail;;4",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@Snail"
    },
    {
      "line": 14,
      "name": "@SnailSpecial"
    }
  ]
});
formatter.step({
  "line": 16,
  "name": "Размер матрицы 8",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 17,
  "name": "Запишем матрицу в массив",
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "Найдем самой самое большое значение",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "Максимальное значение 64",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "8",
      "offset": 15
    }
  ],
  "location": "StepTestSnail.размерМатрицы(int)"
});
formatter.result({
  "duration": 419893,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив()"
});
formatter.result({
  "duration": 50933212,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.найдемСамойСамоеБольшоеЗначение()"
});
formatter.result({
  "duration": 90612,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "64",
      "offset": 22
    }
  ],
  "location": "StepTestSnail.максимальноеЗначение(int)"
});
formatter.result({
  "duration": 233340,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 25,
      "value": "#//Проверяем, что нет нулевых значaxений"
    }
  ],
  "line": 26,
  "name": "4 testZeroElemSnail",
  "description": "",
  "id": "snail;4-testzeroelemsnail",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 27,
  "name": "Размер матрицы 8",
  "keyword": "Given "
});
formatter.step({
  "line": 28,
  "name": "Запишем матрицу в массив",
  "keyword": "When "
});
formatter.step({
  "line": 29,
  "name": "Проверим, что в матрице нет нулей",
  "keyword": "And "
});
formatter.step({
  "line": 30,
  "name": "Поорем, если 0 в матрице есть",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "8",
      "offset": 15
    }
  ],
  "location": "StepTestSnail.размерМатрицы(int)"
});
formatter.result({
  "duration": 240446,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив()"
});
formatter.result({
  "duration": 621252,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.проверимЧтоВМатрицеНетНулей()"
});
formatter.result({
  "duration": 101272,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 13
    }
  ],
  "location": "StepTestSnail.пооремЕслиВМатрицеЕсть(int)"
});
formatter.result({
  "duration": 264136,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 31,
      "value": "#Сумма элементов матрицы"
    }
  ],
  "line": 32,
  "name": "5 testSumElemSnail",
  "description": "",
  "id": "snail;5-testsumelemsnail",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 33,
  "name": "Размер матрицы 4",
  "keyword": "Given "
});
formatter.step({
  "line": 34,
  "name": "Запишем матрицу в массив",
  "keyword": "And "
});
formatter.step({
  "line": 35,
  "name": "Просуммируем элеменнты матрицы",
  "keyword": "When "
});
formatter.step({
  "line": 36,
  "name": "Сумма элементов равна 136",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "4",
      "offset": 15
    }
  ],
  "location": "StepTestSnail.размерМатрицы(int)"
});
formatter.result({
  "duration": 258213,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив()"
});
formatter.result({
  "duration": 265320,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.просуммируемЭлеменнтыМатрицы()"
});
formatter.result({
  "duration": 101272,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "136",
      "offset": 22
    }
  ],
  "location": "StepTestSnail.суммаЭлементовРавна(int)"
});
formatter.result({
  "duration": 302039,
  "status": "passed"
});
});