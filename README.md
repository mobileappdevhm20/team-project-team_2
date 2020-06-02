# The Munich Quiz
[![Build Status](https://travis-ci.com/mobileappdevhm20/team-project-team_2.svg?token=5JBSrPqszVfeAgbcyYMP&branch=master)](https://travis-ci.com/mobileappdevhm20/team-project-team_2)

## Description
Want to have fun with your friends during the lockdown? Download "The Munich Quiz" and compete in answering questions about Munich online!

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/mobileappdevhm20/team-project-team_2.git
```

## Version control flow
We loosely use the "[Git Feature Branch Workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow)" approach: master is the release branch - it should always be releasable, and only merged into when we have tested and verified that everything works and is good to go. 

Features, bugfixes and other taskas are don as branches off of master, then merged back into master via a Pull Request. For every Pull Request to be merged at least 2 members have to accept it and all checks have to pass.

Keep commit atomic and self-explanatory, use rebase (git rebase master) to clean up out of date branches before merging back into master.

## Run Tests
To run tests use the follofing command:
```bash
./gradlew connectedAndroidTest
```

## Roadmap
* Sprint 1: Implement design, tests and basic navigation
* Sprint 2: Implement backend, game logic and connect app to backend
* Sprint 3: Bug fixes and final pollish

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Authors and acknowledgment
* [lukasf98](https://github.com/lukasf98)
* [tobiasnitzl](https://github.com/tobiasnitzl)
* [fredrikholmberg](https://github.com/fredrikholmberg)
* [HoMaze](https://github.com/HoMaze)