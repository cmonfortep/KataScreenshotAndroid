package com.karumi.screenshot

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.view.LayoutInflater
import com.karumi.screenshot.model.SuperHero
import com.karumi.screenshot.ui.presenter.SuperHeroesPresenter
import com.karumi.screenshot.ui.view.SuperHeroViewHolder
import org.junit.Test
import org.mockito.Mockito.mock

class SuperHeroViewHolderTest : ScreenshotTest() {

    @Test fun showsAnySuperHero() {
        val superHero = givenASuperHero()
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test fun showsAnyAvenger() {
        val superHero = givenAnAvenger()
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test fun showsAnySuperHeroWithLongName() {
        val superHero = givenASuperHeroWithALongName()
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    @Test fun showsAnySuperHeroWithLongDescription() {
        val superHero = givenASuperHeroWithALongDescription()
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)

        compareScreenshot(holder, R.dimen.super_hero_row_height)
    }

    private fun givenASuperHeroViewHolder(): SuperHeroViewHolder {
        val context = getInstrumentation().targetContext
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.super_hero_row, null, false)
        return SuperHeroViewHolder(view, mock<SuperHeroesPresenter>(SuperHeroesPresenter::class.java))
    }

    private fun givenASuperHeroWithALongDescription(): SuperHero {
        return SuperHero(
                name = "Super Hero Name",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                        "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                        "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                        "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                        "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                        "mollit anim id est laborum.",
                avenger = false)
    }

    private fun givenASuperHeroWithALongName(): SuperHero {
        return SuperHero(name = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor " +
                "incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation " +
                "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in " +
                "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. " +
                "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt " +
                "mollit anim id est laborum.",
                description =  "Description Super Hero",
                avenger = false)
    }

    private fun givenAnAvenger(): SuperHero {
        return SuperHero(
                name = "Super Hero Name",
                description = "Super Hero Description",
                avenger = true)
    }

    private fun givenASuperHero(): SuperHero {
        return SuperHero(name = "Super Hero Name",
                description = "Super Hero Description",
                avenger = false)
    }
}