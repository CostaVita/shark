package com.degma.shark.repository

import com.degma.shark.model.UserLeaderItem
import org.springframework.data.jpa.repository.JpaRepository

interface UserLeaderItemRepository : JpaRepository<UserLeaderItem, Long>