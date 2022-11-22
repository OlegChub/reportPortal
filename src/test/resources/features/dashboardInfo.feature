@api
Feature: Dashboard info checking

  Background: User is Logged In
    Given user is logged in

  Scenario: Get dashboard name from the project
    When user makes request to get all permitted dashboard resources for the project
    Then user sees dashboard with "DEMO DASHBOARD" name