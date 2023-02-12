package com.degma.shark.repository

import com.degma.shark.model.UserLeaderScore
import org.springframework.data.jpa.repository.JpaRepository

interface UserLeaderScoreRepository : JpaRepository<UserLeaderScore, String>