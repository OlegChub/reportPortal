@api
Feature: Exact launch info checking

  Background: User is Logged In
    Given user is logged in

  Scenario Outline: Check exact launch defects info
    When user makes request to get info of launch with id 3
    Then user sees that parameter "<infoBlockName>" has value <expectedValue>
    Examples:
      | infoBlockName | expectedValue |
      | productBug    | 4             |
      | toInvestigate | 10            |
      | automationBug | 4             |

  Scenario: Check exact launch product bug quantity
    Given user has initial data
      | launchId | parameter  |
      | 3        | productBug |
    When user makes the request
    Then user sees that parameter "productBug" has value 4

  Scenario: Check exact launch product defect by type
    Given user has data of the defect
      | defectName | quantity |
      | productBug | 4        |
    When user makes request to get info of launch with id 3
    Then user sees that parameter value in response is equal
