package com.degma.shark.converter

import com.degma.shark.dto.UserLeaderDto
import com.degma.shark.model.User
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class UserToUserLeaderDto : Converter<User, UserLeaderDto> {
    override fun convert(source: User): UserLeaderDto {
        return UserLeaderDto(source.id,
            source.nickname,
            source.userLeaderItems.map { it.itemId },
            source.userLeaderScore!!.score
        )
    }
}