# Dragons of Mugloar

This project uses https://www.dragonsofmugloar.com/doc/ API documentation and builds the game which solves messages and tries to win

## Tools
- Java 17
- Spring 3.2.0
- Gradle

## Installation

Clone the project. Open a terminal/command prompt, navigate to project's root directory, and run the following command:

```bash
./gradlew bootRun
```

## Solution
The goal of this solution is to navigate through the available messages (tasks), identify the most relevant message, and solve it iteratively until the required points for winning are achieved. The selection of the "best" message is determined by a custom point system devised during the preparation phase.

## Workflow
**1.Message Retrieval:** The application fetches all possible messages (tasks) from the game server.

**2. Relevance Assessment:** Each received message is converted into a custom format, known as CustomMessage, and assigned points based on a personalized point system. This system is designed to evaluate the significance of each message in the context of the game.

**3. Best Message Selection:** The application identifies the message with the highest point as the "best" message for solving. This ensures a strategic approach to task prioritization.

**4. Message Solving:** The chosen message is solved, contributing to the overall score and progressing towards the win condition.

**5. Shopping:** It is important to buy different items from shop, when you have enough golds and don't have enough reputation

**6. Iteration:** Steps 1-5 are repeated until the required points for winning are achieved.

Custom Point System:
The custom point system is a proprietary scoring mechanism created during the preparation phase. It assigns points to each message based on various criteria, such as the nature of the task, potential rewards, and strategic importance in the game context. The higher the points, the more significant the message is considered in the decision-making process.

## What could be improved

- To investigate (to play) more connection between shop items and message types. Especially about when to buy expensive items
- Maybe to try to solve more risky tasks (?) with higher rewards, again after "proper" shopping
- More tests could be added (about RestTemplate, different game scenarios etc.)
- Command line part could be improved.

