package com.degma.shark.controller

import com.degma.shark.dto.UserLeaderDto
import com.degma.shark.service.UserLeaderService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/leaderboard")
class UserLeaderController(val userLeaderService: UserLeaderService) {

    @GetMapping
    fun findAllByPage(
        @RequestParam(defaultValue = "0") pageNo: Int,
        @RequestParam(defaultValue = "15") pageSize: Int,
        @RequestParam(defaultValue = "ask") orderBy: String
    ): List<UserLeaderDto> = userLeaderService.findAll(pageNo, pageSize, orderBy)

    @PostMapping
    fun update(
        @Valid
        @RequestBody userLeaderDto: UserLeaderDto
    ) = userLeaderService.updateOrCreate(userLeaderDto)
}