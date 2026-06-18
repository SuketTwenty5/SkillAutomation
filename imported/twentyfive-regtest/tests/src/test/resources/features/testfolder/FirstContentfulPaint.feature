@Performance @FCP
Feature: First Contentful Paint

  Scenario: Measure FCP for quote details page
    Given I open site
    And I perform PG login
    When I measure first contentful paint for 'estimate' details page over 9 runs
    Then median first contentful paint should be less than 1800 ms
