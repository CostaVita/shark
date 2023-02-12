package com.degma.shark.model

import jakarta.persistence.*

@Entity
@Table(name = "USER_LEADER_ITEM")
class UserLeaderItem(
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    var user: User?,

    @Column(name = "ITEM_ID", nullable = false)
    val itemId: Long,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null
)
