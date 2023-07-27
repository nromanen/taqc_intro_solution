## Requirement for register(login, password) method:

1. If user with this <code> login </code> doesn't exist method should return some random string
2. If user with this <code> login </code>  exist method throws EntityAlreadyExistsException
3. If <code> login </code> is empty or equal to null method throws IllegalArgumentException with message "The login can't be blank"
4. If <code> password </code> doesn't contain at least one number, at least one letter and at least one special characters and at least 8 characters method throws IllegalArgumentException with appropriate message

## Task:
Create at least 8 unit tests for this method according to these requirements.
Some tests should be failed due to incorrect implementation of this method. 