package com.degma.shark.model

import jakarta.persistence.*

@Entity
@Table(name = "USER_LEADER_SCORE")
class UserLeaderScore(
    @OneToOne(cascade = [CascadeType.ALL])
    @MapsId
    @JoinColumn(name = "USER_ID")
    val user: User,

    @Column(name = "SCORE", nullable = false, columnDefinition = "NUMERIC(16,2)")
    var score: Double,

    @Id
    @Column(name = "USER_ID")
    var userId: String
)