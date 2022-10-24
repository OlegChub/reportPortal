Feature: Exact launch info checking

  Background: User is Logged In
    Given use is logged in

  Scenario: Get dashboard name from the project
    When user makes request to get all permitted dashboard resources for the project
    Then user sees dashboard with "DEMO DASHBOARD" name


  Scenario Outline: Check exact launch defects info
    When user makes request to get info of launch with id 3
    Then user sees that parameter "<infoBlockName>" has value <expectedValue>
    Examples:
      | infoBlockName | expectedValue |
      | productBug    | 4             |
      | toInvestigate | 10            |
      | automationBug | 4             |