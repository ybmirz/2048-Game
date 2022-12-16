# 2048 Maintenance
- Tasked to maintain and update an existing codebase of a 2048 game.
- Specific requirements and documentations are stated within the briefing document. 

Things I just did:
- Removed `module-info.java` from the codebase bc VSC references the library for me (may have to change when compiling for Eclipse) (also because JDK16)

### Changes
**Game Logic**
- Fixed scoring issue (now only adds proper scoring)
- Fixed spawning (only spawn when the board can be moved)
- Fixed additional keys could move the board
- Fixed modified cells (sets the correct cells to be modified)

UPDATED README.md find on the Github