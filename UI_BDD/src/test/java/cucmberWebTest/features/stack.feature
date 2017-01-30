@Stack
Feature: TestStack

  @Stack
  Scenario: 001 testNumbFeatured
    Given Найдем количество на табе Featured
    Then Проверим, что количество Featured больше 300
    And Закроем браузер после теста

  @Stack
  Scenario: 002 testSocNetButton
    Given Откроем страницу авторизации SignUp
    Then Проверим, что на странице есть кнопки Google и Facebook
    And Закроем браузер после теста

  @Stack
  Scenario: 003 testTopQuestionsToday
    Given Найдем количество вопросов на главной
    When Откроем случайный вопрос
    Then Проверим, что он был задан сегодня
    And Закроем браузер после теста

  @Stack
  Scenario: 004 test60KSalary
    Given Найдем зарплаты в предложениях о работе на главной
    Then Проверим, что зп указана и больше 60000 долларов
    And Закроем браузер после теста

  @Stack
  Scenario: 005 userInfo
    Given Откроем страницу User
    Then Проверим, что на каждой из первых 10 старниц количество пользователей с тегом java > 30 %
    And Закроем браузер после теста
