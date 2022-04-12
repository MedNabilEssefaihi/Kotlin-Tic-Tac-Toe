import board.Board

fun main() {
    val game = Board()
    var isPlayerGame: String

    do {
        game.printBoard()
        while (!game.isGameOver) {
            game.showPlayer()
            val input = readLine()!!
            game.playPiece(input.toInt())
        }

        println("Do you want to player again ? \n(1) for Yes\n")
        isPlayerGame = readLine()!!

        if (isPlayerGame == "1") {
            game.reSetGame()
        }
    } while (isPlayerGame == "1")

    game.exitGame()
    println("Thank you for playing TIC TAC TOE Bye bye ^^")
}

