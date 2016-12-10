$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("test.feature");
formatter.feature({
  "line": 3,
  "name": "Matr",
  "description": "",
  "id": "matr",
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
  "name": "1 TestMatix",
  "description": "",
  "id": "matr;1-testmatix",
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
  "duration": 1574966909,
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
  "duration": 411601,
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
  "duration": 4310271,
  "status": "passed"
});
});