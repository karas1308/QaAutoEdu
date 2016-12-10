@TEST

Feature: Matrix

  Scenario: TestMatix1

    Given Input "6" matrix size
    When I looking for "0" "5" element
    Then Tenth element isEquals "6"





