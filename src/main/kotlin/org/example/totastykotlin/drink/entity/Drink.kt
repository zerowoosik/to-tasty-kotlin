package org.example.totastykotlin.drink.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.example.totastykotlin.domain.common.BaseEntity
import org.example.totastykotlin.drink.enums.DrinkType

@Entity
class Drink(
    val name: String,

    @Enumerated(EnumType.STRING)
    val drinkType: DrinkType,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    val id: Long? = null,

    val drinkImageUrl: String? = null

) : BaseEntity()