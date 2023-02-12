package com.degma.shark.service

import com.degma.shark.converter.UserToUserLeaderDto
import com.degma.shark.dto.UserLeaderDto
import com.degma.shark.model.User
import com.degma.shark.model.UserLeaderItem
import com.degma.shark.model.UserLeaderScore
import com.degma.shark.repository.UserLeaderItemRepository
import com.degma.shark.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserLeaderService(
    val userRepo: UserRepository,
    val userLeaderItemRepo: UserLeaderItemRepository,
    val converter: UserToUserLeaderDto
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun findAll(pageNo: Int, pageSize: Int, orderBy: String): List<UserLeaderDto> {
        val sort = Sort.by("userLeaderScore.score")

        val paging: Pageable = PageRequest.of(
            pageNo, pageSize,
            if ("desc" == orderBy) sort.descending() else sort
        )

        val userLeaderDtos = mutableListOf<UserLeaderDto>()

        findAll(paging)
            .forEach { userLeaderDtos.add(converter.convert(it)) }

        return userLeaderDtos
    }

    @Transactional
    fun updateOrCreate(userLeaderDto: UserLeaderDto) {
        userRepo.findById(userLeaderDto.userId).ifPresentOrElse(
            { update(it, userLeaderDto) },
            { create(userLeaderDto) }
        )
    }

    private fun update(user: User, userLeaderDto: UserLeaderDto) {
        log.info("Update user leader info $userLeaderDto")

        user.nickname = userLeaderDto.nickname
        user.userLeaderScore!!.score = userLeaderDto.score!!

        val deleteItems = user.userLeaderItems.filter { x -> !userLeaderDto.items.contains(x.itemId) }
        user.userLeaderItems.removeAll(deleteItems)
        userLeaderItemRepo.deleteAll(deleteItems)

        val newUserLeaderItems = mutableListOf<UserLeaderItem>()
        userLeaderDto.items.filter { x -> !user.userLeaderItems.any { it.itemId == x } }
            .forEach { newUserLeaderItems.add(UserLeaderItem(user, it)) }
        user.userLeaderItems.addAll(newUserLeaderItems)

        userRepo.save(user)
    }

    private fun create(userLeaderDto: UserLeaderDto) {
        log.info("Create user leader info $userLeaderDto")

        val user = User(userLeaderDto.nickname, userLeaderDto.userId)
        val userLeaderScore = UserLeaderScore(user, userLeaderDto.score!!, userLeaderDto.userId)
        val userLeaderItems = mutableListOf<UserLeaderItem>()
        userLeaderDto.items.forEach { userLeaderItems.add(UserLeaderItem(user, it)) }

        user.userLeaderItems = userLeaderItems
        user.userLeaderScore = userLeaderScore

        userRepo.save(user)
    }

    private fun findAll(pageable: Pageable): List<User> {
        val users = userRepo.findAll(pageable)
        return if (users.hasContent()) users.content else emptyList()
    }
}