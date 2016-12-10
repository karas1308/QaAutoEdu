$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("testMatrix.feature");
formatter.feature({
  "line": 3,
  "name": "Matrix",
  "description": "",
  "id": "matrix",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@TEST"
    }
  ]
});
formatter.scenario({
  "line": 5,
  "name": "TestMatix1",
  "description": "",
  "id": "matrix;testmatix1",
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
  "location": "MyStepdefs.inputMatrixSize(String)"
});
formatter.result({
  "duration": 1117880990,
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
  "location": "MyStepdefs.i_looking_for_element(String,String)"
});
formatter.result({
  "duration": 543077,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "6",
      "offset": 24
    }
  ],
  "location": "MyStepdefs.tenth_element_isEquals(String)"
});
formatter.result({
  "duration": 3947233,
  "status": "passed"
});
formatter.uri("testSnail.feature");
formatter.feature({
  "line": 2,
  "name": "Snail",
  "description": "",
  "id": "snail",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "1 Snail",
  "description": "",
  "id": "snail;1-snail",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "dsdf 5 r",
  "keyword": "Given "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
});