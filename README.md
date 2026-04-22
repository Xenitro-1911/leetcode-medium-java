# leetcode-medium-java

A structured collection of LeetCode Medium problem solutions written in Java, built as part of active DSA practice for CV and interview preparation.

---

## Structure

```
src/
├── main/java/problems/
│   └── p002_add_two_numbers/
│       ├── Solution.java   # Clean Java solution
│       └── Notes.md        # Approach, complexity, alternatives
└── test/java/problems/
    └── p002_add_two_numbers/
        └── SolutionTest.java  # JUnit 5 unit tests
```

Each problem folder contains:
- **Solution.java** — clean, commented solution with time/space complexity in Javadoc
- **Notes.md** — written breakdown of the approach, complexity analysis, and alternatives considered
- **SolutionTest.java** — JUnit 5 tests covering standard and edge cases

---

## Problems Solved (10)

| # | Problem | Topic |
|---|---------|-------|
| 002 | Add Two Numbers | Linked List |
| 003 | Longest Substring Without Repeating Characters | Sliding Window |
| 005 | Longest Palindromic Substring | Dynamic Programming |
| 006 | Zigzag Conversion | String |
| 007 | Reverse Integer | Math |
| 008 | String to Integer (atoi) | String/Parsing |
| 011 | Container With Most Water | Two Pointers |
| 012 | Integer to Roman | Math/String |
| 015 | 3Sum | Two Pointers |
| 016 | 3Sum Closest | Two Pointers |

---

## How to Run

**Requirements:** Java 17+, Maven 3.x

```bash
# Run all tests
mvn test

# Run a specific problem's tests
mvn test -Dtest=SolutionTest
```

Or open in IntelliJ IDEA and right-click any test → Run.

---

## Tech Stack

- **Language:** Java
- **Testing:** JUnit Jupiter (JUnit 5)
- **Build:** Maven
- **IDE:** IntelliJ IDEA
