# 2048 Maintenance
Name: Mirza Hizriyan Nubli Hidayat
ID: 20310915

**Javadocs located** at `/javadoc/`

- Tasked to maintain and update an existing codebase of a 2048 game.
- Specific requirements and documentations are stated within the briefing document. 

Things I just did:
- Removed `module-info.java` from the codebase bc VSC references the library for me (may have to change when compiling for Eclipse) (also because JDK16)

### Compile
There's two ways you can do this, either *download the packaged zip* and run the `launch.sh` within the `bin` folder of the package, or `git clone` this repository and run the following command:
```bash
mvn clean javafx:run
```
or *downloading the package zip* [here](https://github.com/ybmirz/COMP2042_CW_hfymh3/releases/tag/v1.0), and running the following command in the directory using `powershell`:
```bash
launch
```

### Changes
**Game Logic**
- Fixed scoring issue (now only adds proper scoring)
- Fixed spawning (only spawn when the board can be moved)
- Fixed additional keys could move the board
- Fixed modified cells (sets the correct cells to be modified)

**Packages**
- Restructured the entire project into more meaningful maintainable packages
- Tried to implement an MVC Design Pattern
- Main running of the file is done mainly in the Controller files, with the usage of other classes to be model and logic

**Additional Features**
- Overlay EndGame Scene
- Difficulty (Grid Size) setting by the User
- MenuBar to choose Save File
- Save scores to a text file, using an Account system
- Themes choosing using CSS (not fully implemented)

**Not implemented Features**
- Sound (not enough time, I'm working on my time management skills)
- Sound level (related to the above)
- Win condition (small prompt to show continue or not when reaching 2048)
- Spawning random `2s` and `4s` when reaching specific cell numbers
- Using a build manager like gradle or maven
- Creating JUnit tests
> Most of this are doable, and I have a concept in my head, was not able to set off the time to implement

### Directory tree
**To show new java classes**
```bash
├───.vscode
│       launch.json
│       settings.json
├───bin
│   └───out
├───javadoc
│   └─── ----JavaDoc files----
└───src
    └───main
        ├───java
        │   │   module-info.java
        │   └───com
        │       └───example
        │           └───demo
        │               │   EndGame.java
        │               │   GameScene.java
        │               │   Main.java
        │               │   UserSettings.java -- new java file--
        │               │
        │               ├───Controller
        │               │       GameController.java -- new java file--
        │               │       LeaderboardController.java -- new java file--
        │               │       MenuController.java -- new java file--
        │               │       ReturningController.java -- new java file--
        │               │       SettingsController.java -- new java file--
        │               │
        │               │───Dialogs
        │               │       SaveAccount.fxml -- new java file--
        │               │       SaveAccount.java -- new java file--
        │               │
        │               └──Objects
        │                       Account.java
        │                       AccountCell.java
        │                       Cell.java
        │                       TextMaker.java
        │               
        └───resources
            └───com
                └───example
                    └───demo
                        │   logo.png
                        │
                        ├───Scenes
                        │       GameScene.fxml --custom fxml--
                        │       Leaderboard.fxml --custom fxml--
                        │       Menu.fxml --custom fxml--
                        │       SettingsScene.fxml --custom fxml--
                        │
                        ├───Images
                        │       2048 3x3.jpg
                        │       2048 4x4.png
                        │       2048 5x5.jpg
                        │
                        └───Themes
                                original.css
```

