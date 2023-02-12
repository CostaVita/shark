package com.degma.shark.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UserLeaderDto(
    @field:NotBlank
    val userId: String,
    @field:NotBlank
    val nickname: String,
    val items: List<Long> = mutableListOf(),
    @field:NotNull
    val score: Double?
)
