$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("testSnail.feature");
formatter.feature({
  "line": 3,
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
formatter.scenarioOutline({
  "comments": [
    {
      "line": 17,
      "value": "#Максимальное значение в матрице 7х7"
    }
  ],
  "line": 19,
  "name": "3 testMaxElemSnail",
  "description": "",
  "id": "snail;3-testmaxelemsnail",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 18,
      "name": "@SnailSpecial"
    }
  ]
});
formatter.step({
  "line": 20,
  "name": "Размер  матрицы\u003d \u003csize\u003e",
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "Запишем матрицу   в массив",
  "keyword": "When "
});
formatter.step({
  "line": 22,
  "name": "Найдем самой самое большое значение",
  "keyword": "And "
});
formatter.step({
  "line": 23,
  "name": "Максимальное значение \u003cmax\u003e",
  "keyword": "Then "
});
formatter.examples({
  "line": 24,
  "name": "",
  "description": "",
  "id": "snail;3-testmaxelemsnail;",
  "rows": [
    {
      "cells": [
        "size",
        "max"
      ],
      "line": 25,
      "id": "snail;3-testmaxelemsnail;;1"
    },
    {
      "cells": [
        "7",
        "49"
      ],
      "line": 26,
      "id": "snail;3-testmaxelemsnail;;2"
    },
    {
      "cells": [
        "4",
        "16"
      ],
      "line": 27,
      "id": "snail;3-testmaxelemsnail;;3"
    },
    {
      "cells": [
        "8",
        "64"
      ],
      "line": 28,
      "id": "snail;3-testmaxelemsnail;;4"
    }
  ],
  "keyword": "Examples"
});
formatter.scenario({
  "line": 26,
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
      "line": 18,
      "name": "@SnailSpecial"
    }
  ]
});
formatter.step({
  "line": 20,
  "name": "Размер  матрицы\u003d 7",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "Запишем матрицу   в массив",
  "keyword": "When "
});
formatter.step({
  "line": 22,
  "name": "Найдем самой самое большое значение",
  "keyword": "And "
});
formatter.step({
  "line": 23,
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
      "offset": 17
    }
  ],
  "location": "StepTestSnail.размерМатрицы2(int)"
});
formatter.result({
  "duration": 727689552,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив2()"
});
formatter.result({
  "duration": 4305533,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.найдемСамойСамоеБольшоеЗначение()"
});
formatter.result({
  "duration": 91796,
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
  "duration": 4122533,
  "status": "passed"
});
formatter.scenario({
  "line": 27,
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
      "line": 18,
      "name": "@SnailSpecial"
    }
  ]
});
formatter.step({
  "line": 20,
  "name": "Размер  матрицы\u003d 4",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "Запишем матрицу   в массив",
  "keyword": "When "
});
formatter.step({
  "line": 22,
  "name": "Найдем самой самое большое значение",
  "keyword": "And "
});
formatter.step({
  "line": 23,
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
      "offset": 17
    }
  ],
  "location": "StepTestSnail.размерМатрицы2(int)"
});
formatter.result({
  "duration": 280718,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив2()"
});
formatter.result({
  "duration": 509320,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.найдемСамойСамоеБольшоеЗначение()"
});
formatter.result({
  "duration": 61000,
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
  "duration": 259397,
  "status": "passed"
});
formatter.scenario({
  "line": 28,
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
      "line": 18,
      "name": "@SnailSpecial"
    }
  ]
});
formatter.step({
  "line": 20,
  "name": "Размер  матрицы\u003d 8",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 21,
  "name": "Запишем матрицу   в массив",
  "keyword": "When "
});
formatter.step({
  "line": 22,
  "name": "Найдем самой самое большое значение",
  "keyword": "And "
});
formatter.step({
  "line": 23,
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
      "offset": 17
    }
  ],
  "location": "StepTestSnail.размерМатрицы2(int)"
});
formatter.result({
  "duration": 273020,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.запишемМатрицуВМассив2()"
});
formatter.result({
  "duration": 1633379,
  "status": "passed"
});
formatter.match({
  "location": "StepTestSnail.найдемСамойСамоеБольшоеЗначение()"
});
formatter.result({
  "duration": 73437,
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
  "duration": 257621,
  "status": "passed"
});
});