@rest @fake-api @comments
Feature: JSONPlaceholder fake API Tests for /comments endpoint

Scenario: Should get all the comments
  Given I have a /comments API endpoint
  Then I can request GET on /comments endpoint successfully
  Then I see all the comments

Scenario Outline: Should be able to get all the comments for given post
  Given I have a /comments API endpoint with postId as url parameter postId=<postId>
  Then I can request GET on /comments endpoint successfully
  And I see correct number of comments for given post
  Examples:
    |postId|
    |8     |