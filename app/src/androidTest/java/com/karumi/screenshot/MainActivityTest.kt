package com.karumi.screenshot

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.rule.IntentsTestRule
import com.karumi.screenshot.di.MainComponent
import com.karumi.screenshot.di.MainModule
import com.karumi.screenshot.model.SuperHero
import com.karumi.screenshot.model.SuperHeroesRepository
import com.karumi.screenshot.ui.view.MainActivity
import it.cosenonjaviste.daggermock.DaggerMockRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.util.*
import java.util.Collections.emptyList

class MainActivityTest : ScreenshotTest() {

    @Rule @JvmField var daggerRule = DaggerMockRule(MainComponent::class.java, MainModule()).set { component ->
        val app = InstrumentationRegistry.getInstrumentation()
                .targetContext
                .applicationContext as SuperHeroesApplication
        app.setComponent(component)
    }

    @Rule @JvmField var activityRule = IntentsTestRule(MainActivity::class.java, true, false)

    @Mock lateinit var repository: SuperHeroesRepository

    @Test fun showsEmptyCaseIfThereAreNoSuperHeroes() {
        givenThereAreNoSuperHeroes()

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test fun showsSuperHeroCaseIfThereIs1SuperHeroe() {
        givenThereAreSomeSuperHeroes(1, true)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test fun showsSuperHerosCaseIfThereIs10SuperHeroes() {
        givenThereAreSomeSuperHeroes(10, true)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    @Test fun showsSuperHerosWithoutAvengerBadgeCaseIfSuperHeroesAreAvengers() {
        givenThereAreSomeSuperHeroes(5, false)

        val activity = startActivity()

        compareScreenshot(activity)
    }

    private fun givenThereAreSomeSuperHeroes(numberOfSuperHeroes: Int, avengers: Boolean): List<SuperHero> {
        val superHeroes = LinkedList<SuperHero>()
        for (i in 0..numberOfSuperHeroes - 1) {
            val superHeroName = "SuperHero - " + i
            val superHeroDescription = "Description Super Hero - " + i
            val superHero = SuperHero(
                    name = superHeroName,
                    avenger = avengers,
                    description =  superHeroDescription)
            superHeroes.add(superHero)
            `when`(repository.getByName(superHeroName)).thenReturn(superHero)
        }
        `when`(repository.all).thenReturn(superHeroes)
        return superHeroes
    }

    private fun givenThereAreNoSuperHeroes() {
        `when`(repository.all).thenReturn(emptyList<SuperHero>())
    }

    private fun startActivity(): MainActivity {
        return activityRule.launchActivity(null)
    }
}