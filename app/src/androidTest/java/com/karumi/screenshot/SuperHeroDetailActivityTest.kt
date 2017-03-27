package com.karumi.screenshot

import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import com.karumi.screenshot.di.MainComponent
import com.karumi.screenshot.di.MainModule
import com.karumi.screenshot.model.SuperHero
import com.karumi.screenshot.model.SuperHeroesRepository
import com.karumi.screenshot.ui.view.SuperHeroDetailActivity
import it.cosenonjaviste.daggermock.DaggerMockRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString

class SuperHeroDetailActivityTest : ScreenshotTest() {

    @Rule @JvmField var daggerRule = DaggerMockRule(MainComponent::class.java, MainModule()).set { component ->
        val app = InstrumentationRegistry.getInstrumentation()
                .targetContext
                .applicationContext as SuperHeroesApplication
        app.setComponent(component)
    }

    @Rule @JvmField var activityRule = ActivityTestRule(SuperHeroDetailActivity::class.java, true, false)

    @Mock lateinit var repository: SuperHeroesRepository

    @Test
    fun showsAnySuperHero() {
        val superHero = givenAnySuperHero()

        val activity = startActivity(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsAnyAvenger() {
        val superHero = givenAnyAvenger()

        val activity = startActivity(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsAnySuperHeroWithLongName() {
        val superHero = givenAnySuperHeroWithLongName()

        val activity = startActivity(superHero)

        compareScreenshot(activity)
    }

    @Test
    fun showsAnySuperHeroWithLongDescription() {
        val superHero = givenAnySuperHeroWithLongDesciption()

        val activity = startActivity(superHero)

        compareScreenshot(activity)
    }

    private fun givenAnySuperHero(): SuperHero {
        val superHero = SuperHero(
                name = "SuperHero - 0",
                description = "Best SuperHero")

        `when`(repository.getByName(anyString())).thenReturn(superHero)
        return superHero
    }

    private fun givenAnyAvenger(): SuperHero {
        val superHero = SuperHero(name = "Avenger - 0",
                avenger = true,
                description = "Best avenger")

        `when`(repository.getByName(anyString())).thenReturn(superHero)
        return superHero
    }

    private fun givenAnySuperHeroWithLongName(): SuperHero {
        val superHero = SuperHero(
                name = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                        + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
                        + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
                        + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                        + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
                        + "mollit anim id est laborum.",
                description = "Best SuperHero")

        `when`(repository.getByName(anyString())).thenReturn(superHero)
        return superHero
    }

    private fun givenAnySuperHeroWithLongDesciption(): SuperHero {
        val superHero = SuperHero(
                name = "Avenger - 0",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt "
                        + "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation "
                        + "ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in "
                        + "reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. "
                        + "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt "
                        + "mollit anim id est laborum.")

        `when`(repository.getByName(anyString())).thenReturn(superHero)
        return superHero
    }

    private fun startActivity(superHero: SuperHero): SuperHeroDetailActivity {
        val intent = Intent()
        intent.putExtra("super_hero_name_key", superHero.name)
        return activityRule.launchActivity(intent)
    }
}