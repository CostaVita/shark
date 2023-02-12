package com.degma.shark.repository

import com.degma.shark.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String>