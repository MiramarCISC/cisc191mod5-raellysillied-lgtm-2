[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=23532808)
# Recursion and Algorithms Lab

This GitHub Classroom lab is for **Module 5: Recursion + Algorithms**.

You will implement recursive and iterative solutions using a **game server theme**:
- recursive and iterative binary search on sorted match IDs
- recursive and iterative flood-fill / connected tile counting on a map
- recursive tournament bracket search with a helper method

## Learning Goals

By the end of this lab, you should be able to:
1. Identify the **base case** and **recursive case** in a recursive method
2. Use a **helper method** to simplify recursion
3. Compare recursion and iteration on the same problem
4. Explain when recursion is a natural fit and when a loop is simpler

## Your Tasks

Implement the TODO methods in `GameAlgorithms.java`:

1. `findMatchRecursive(int[] sortedMatchIds, int target)`
2. `findMatchIterative(int[] sortedMatchIds, int target)`
3. `countConnectedTilesRecursive(char[][] map, int startRow, int startCol)`
4. `countConnectedTilesIterative(char[][] map, int startRow, int startCol)`
5. `containsMatch(BracketNode root, String target)` using a recursive helper

## Rules

- Do not change method names or parameter lists.
- Do not edit the tests.
- Keep all code in the package `edu.sdccd.cisc191`.
- For the recursive methods, make sure each recursive call moves toward a base case.
- For map traversal, mark visited tiles so you do not count the same tile twice.

## Running the Lab

### Compile and test with Maven
```bash
mvn test
```

## Suggested Work Order

1. Read the tests first.
2. Implement iterative binary search.
3. Implement recursive binary search.
4. Implement recursive bracket search with a helper method.
5. Implement recursive tile counting.
6. Implement iterative tile counting using a stack.

## Reflection Questions

Answer these in comments at the top of `GameAlgorithms.java` or in your submission notes:
1. What is the base case for your recursive binary search?
2. Why is recursion natural for the bracket tree?
3. Why might the iterative tile-counting method be safer on a very large map?
4. Which problems in this lab felt more natural with loops, and which felt more natural with recursion?
