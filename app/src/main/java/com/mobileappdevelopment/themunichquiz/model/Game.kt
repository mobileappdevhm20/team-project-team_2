package com.mobileappdevelopment.themunichquiz.model

data class Game(val questions: List<Int>, val playerOne: String, val playerTwo: String, val currentQuestion: Int = 0) {
}