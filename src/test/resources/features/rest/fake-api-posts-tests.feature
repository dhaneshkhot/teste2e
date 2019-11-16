@rest @fake-api @posts
Feature: JSONPlaceholder fake API Tests for /posts endpoint

Scenario: Should get all the posts
  Given I have a /posts API endpoint
  Then I can request GET on /posts endpoint successfully
  Then I see all the posts

Scenario Outline: Should be able to get all the posts for given user
  Given I have a /posts API endpoint with userId as url parameter userId=<userId>
  Then I can request GET on /posts endpoint successfully
  And I see correct number of posts for given user
  Examples:
    |userId|
    |7     |

@bug
Scenario Outline: Should be able to POST a post
  Given I have a /posts API endpoint
  When I request POST on /posts endpoint successfully for <userId> with "<title>", and "<body>"
  Then I get newly created post for <userId> with "<title>", and "<body>"
  And When request GET on /posts/id, I see new post is indeed persisted
  Examples:
    |userId|title     |body      |
    |1     |Test Title| Test Body|

@bug
Scenario Outline: Should be able to update a post
  Given I have a /posts/id API endpoint for an individual id=<id>
  When I request PUT on /posts/id endpoint successfully with <userId>, "<title>", and "<body>"
  Then I get updated post for <id> with updated values <userId>, "<title>", and "<body>"
  And When request GET on /posts/id, I see updated post <id>, <userId>, "<title>", and "<body>" is indeed persisted
  Examples:
    |id|userId|title             |body               |
    |1 |1     |Test Title Updated| Test Body Updated |

@bug
Scenario Outline: Should be able to update a post partially
  Given I have a /posts/id API endpoint for an individual id=<id>
  When I request PATCH on /posts/id endpoint successfully with "<title>"
  Then I get updated post for <id> with updated values for title "<title>"
  And When request GET on /posts/id, I see partially updated post with title "<title>" is indeed persisted
  Examples:
    |id|title                        |
    |6 |Test Title Updated with PATCH|

Scenario Outline: Should be able to get an individual post
  Given I have a /posts/id API endpoint for an individual id=<id>
  When I request GET on /posts/id endpoint successfully
  Then I get and individual post for <id>
  Examples:
    |id|
    |3 |

@bug
Scenario Outline: Should be able to delete an individual post
  Given I have a /posts/id API endpoint for an individual id=<id>
  When I request DELETE on /posts/id endpoint successfully
  Then I should not be able to request GET post for <id>
  Examples:
    |id|
    |4 |

@bug @not-RESTful
Scenario Outline: Should be able to get all the comments on a post
  Given I have a /posts/id/comments API endpoint for an individual id=<id>
  When I request GET on /posts/id/comments endpoint successfully
  Then I should be able to see all the comments for the given post
  Examples:
    |id|
    |8 |
