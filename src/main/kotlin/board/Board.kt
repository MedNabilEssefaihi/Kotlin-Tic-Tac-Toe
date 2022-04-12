package board

class Board {
    private val boardSize: Int = 3
    private val empty: String = "___"
    private var moveCount = 1
    private var player = "X"
    private var playerXScore = 0
    private var playerOScore = 0
    private var boardShape = Array(boardSize) {
        Array(boardSize) { empty }
    }
    var isGameOver: Boolean = false
    private val position = listOf(
        0 to 0,
        0 to 0,
        0 to 1,
        0 to 2,
        1 to 0,
        1 to 1,
        1 to 2,
        2 to 0,
        2 to 1,
        2 to 2,
    )


    private fun reSetBoard() {
        boardShape = Array(boardSize) {
            Array(boardSize) { empty }
        }
    }

    fun reSetGame() {
        reSetBoard()
        moveCount = 1
        player = "X"
        isGameOver = false
    }

    fun printBoard() {
        boardShape.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, column ->
                if (boardShape[rowIndex][columnIndex] == empty) {
                    val boxBoardNumber = rowIndex * boardSize + columnIndex + 1
                    print("| $boxBoardNumber |")
                } else {
                    print("| $column |")
                }
            }
            println() // print a new row in new line
        }
        println() // input stand be in new line
    }

    fun playPiece(piece: Int) {
        if (piece > 9) {
            println("****** The position $piece is invalid ******")
            return
        }

        val (row, column) = position[piece]
        if (!isPositionValid(row, column)) {
            println("****** The position $piece is already taken ******")
            return
        }

        boardShape[row][column] = player
        printBoard()
        moveCount++

        checkWinningMove()

    }

    private fun isPositionValid(row: Int, column: Int): Boolean = boardShape[row][column] == empty

    fun showPlayer() {
        player = if (moveCount % 2 == 0) "O" else "X"
        println("Player $player is your turn now, please choose a number")
    }

    private fun checkWinningMove() {
        var winning = false

        // check row
        if (!winning) {
            for (row in 0 until boardSize - 1) {
                var checkRow = false
                for (column in 0 until boardSize - 1) {
                    if (
                        boardShape[row][column] != empty &&
                        boardShape[row][column] == boardShape[row][column + 1]
                    ) {
                        checkRow = true
                    } else {
                        checkRow = false
                        break
                    }
                }
                if (checkRow) {
                    winning = true
                    break
                }
            }
        }
        // check column
        if (!winning) {
            for (column in 0 until boardSize - 1) {
                var checkColumn = false
                for (row in 0 until boardSize - 1) {
                    if (
                        boardShape[row][column] != empty &&
                        boardShape[row][column] == boardShape[row + 1][column]
                    ) {
                        checkColumn = true
                    } else {
                        checkColumn = false
                        break
                    }
                }
                if (checkColumn) {
                    winning = true
                    break
                }
            }
        }

        // check other
        if (!winning) {
            winning =
                boardShape[0][0] != empty && boardShape[0][0] == boardShape[1][1] && boardShape[0][0] == boardShape[2][2]

        }
        if (!winning) {
            winning =
                boardShape[2][0] != empty && boardShape[2][0] == boardShape[1][1] && boardShape[2][0] == boardShape[0][2]
        }

        if (winning) {
            isGameOver = true
            incrementScore()
            println("Game over! the winner is $player")
            println("Score:\nPlayer X: $playerXScore\nPlayer O: $playerOScore")
        }
        if (moveCount > 9) {
            isGameOver = true
            println("Game over! the winner and no body win!")
        }
    }

    private fun incrementScore() {
        if (player == "X") playerXScore++
        else playerOScore++
    }

    fun exitGame() {
        playerXScore = 0
        playerOScore = 0
    }
}