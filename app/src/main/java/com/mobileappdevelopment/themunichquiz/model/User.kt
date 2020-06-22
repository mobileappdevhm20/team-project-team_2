package com.mobileappdevelopment.themunichquiz.model

data class User(val email: String, val score: Int = 0, val shareScore: Boolean = true, val friends: List<String> = emptyList()) {
}