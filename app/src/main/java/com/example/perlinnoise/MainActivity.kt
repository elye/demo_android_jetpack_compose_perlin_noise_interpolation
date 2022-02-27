package com.example.perlinnoise


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.perlinnoise.MainDestinations.CHILD_APP_TYPE
import com.example.perlinnoise.MainDestinations.CHILD_SCREEN
import com.example.perlinnoise.MainDestinations.MAIN_SCREEN
import com.example.perlinnoise.ui.theme.PerlinNoiseTheme

const val WIDTH = 200
const val BOX_SIZE = 32

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PerlinNoiseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    NavGraph()
}


enum class PERLIN_APP_TYPE(val value: String, val func: @Composable () -> Unit) {
    PERLIN_NOISE_ART("Perlin Art", { PerlinNoiseArt() }),
    PERLIN_INTERPOLATOR("Perlin Interpolator", { PerlinInterpolator() }),
    MULTI_INTERPOLATOR("Multi Interpolator", { MultiInterpolator() }),
}

@Composable
fun NavGraph(startDestination: String = MAIN_SCREEN) {
    val navController = rememberNavController()
    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MAIN_SCREEN) {
            MainScreen(actions)
        }
        composable(
            "$CHILD_SCREEN/{$CHILD_APP_TYPE}",
            arguments = listOf(navArgument(CHILD_APP_TYPE) {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            ChildScreen(arguments.getString(CHILD_APP_TYPE))
        }
    }
}

class MainActions(navController: NavHostController) {
    val mainScreen: () -> Unit = {
        navController.navigate(MAIN_SCREEN)
    }
    val animationScreen: (String) -> Unit = { setting ->
        navController.navigate("$CHILD_SCREEN/$setting")
    }
}


@Composable
fun MainScreen(actions: MainActions) {
    @Composable
    fun ColumnScope.MyButton(
        title: PERLIN_APP_TYPE
    ) {
        Button(
            onClick = { actions.animationScreen(title.value) },
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
                .fillMaxSize()
        ) {
            Text(title.value)
        }
    }

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            enumValues<PERLIN_APP_TYPE>().forEach {
                MyButton(it)
            }
        }
    }
}

@Composable
fun ChildScreen(animationSetting: String?) {
    enumValues<PERLIN_APP_TYPE>().first { it.value == animationSetting }.func.invoke()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PerlinNoiseTheme {
        Greeting("Android")
    }
}
