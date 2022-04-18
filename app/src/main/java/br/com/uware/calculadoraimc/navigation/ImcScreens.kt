package br.com.uware.calculadoraimc.navigation

import java.lang.IllegalArgumentException


/**
 * ImcScreens
 * Routes to app screens.
 * @author Rodrigo Leutz
 */
enum class ImcScreens {
    SplashScreen,
    HomeScreen,
    ImcScreen,
    SavedScreen,
    DetailScreen;
    companion object {
        fun fromRoute(route: String?): ImcScreens =
            when(route?.substringBefore("/")){
                SplashScreen.name -> SplashScreen
                HomeScreen.name -> HomeScreen
                ImcScreen.name -> ImcScreen
                SavedScreen.name -> SavedScreen
                DetailScreen.name -> DetailScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}