package com.mobileappdevelopment.themunichquiz.model

data class Game(val questions: List<Int> = emptyList(), val playerOne: Player = Player(), val playerTwo: Player = Player()) {
}