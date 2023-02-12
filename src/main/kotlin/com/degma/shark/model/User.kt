package com.degma.shark.model

import jakarta.persistence.*
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "USER_INFO")
class User(
    @Column(name = "NICKNAME")
    var nickname: String,

    @Id
    @Column(name = "ID")
    var id: String,

    @UpdateTimestamp
    @Column(name = "UPDATE_DATE_TIME", nullable = false)
    var updateDateTime: LocalDateTime? = null,

    @OneToMany(
        mappedBy = "user",
        cascade = [CascadeType.ALL]
    )
    var userLeaderItems: MutableList<UserLeaderItem> = mutableListOf(),

    @OneToOne(
        mappedBy = "user",
        cascade = [CascadeType.ALL]
    )
    @PrimaryKeyJoinColumn
    var userLeaderScore: UserLeaderScore? = null
)
