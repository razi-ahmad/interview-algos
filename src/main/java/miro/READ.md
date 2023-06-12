# Miro Game Manager (Approved)

#### Description

Implement the following methods from the MiroGameManager class:

- MiroGameManager() - Initializes the MiroGameManager object.

- bool login(int userId) - Inserts the userId of the user into the pool if not present. Returns true  if the user was not already logged in, false otherwise.
- bool logout(int userId) - Removes the userId from the pool if present. Returns true  if the user was logged in, false otherwise.
- int getRandomUserId() - Returns a random userId from the current pool. Each id must have the same probability of being returned. In case there are no users in the pool, return -1

### Example

```java
MiroGameManager miroGameManager = new MiroGameManager();

miroGameManager.login(1); // Logs in user 1 to the board. Returns true login was successful.

miroGameManager.logout(2); // Returns false as user 2 is not logged in.

miroGameManager.login(2); // Logs in user 2 to the board, returns true. Pool now contains users [1,2].

miroGameManager.getRandomUserId(); // getRandomUser() should return either 1 or 2 randomly.

miroGameManager.logout(1); // Logs out user 1 from the board, returns true. Pool now contains user [2].

miroGameManager.login(2); // 2 was already logged in, so return false.

miroGameManager.getRandomUserId(); // Since user 2 is the only currently logged in user, getRandomUser() will always return 2.
```