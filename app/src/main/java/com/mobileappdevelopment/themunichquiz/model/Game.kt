package com.mobileappdevelopment.themunichquiz.model

data class Game(val questions: List<Int>, val playerOne: String, val playerOneScore: Int = 0, val playerTwo: String, val playerTwoScore: Int = 0, val currentQuestion: Int = 0) {
}