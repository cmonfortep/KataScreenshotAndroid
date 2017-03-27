package com.karumi.screenshot.model


data class SuperHero(val name: String, val photo: String? = null,
                     val avenger: Boolean = false, val description: String) {

    fun isAvenger(): Boolean = avenger
}