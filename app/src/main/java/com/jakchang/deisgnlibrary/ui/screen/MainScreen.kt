package com.jakchang.deisgnlibrary.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jakchang.deisgnlibrary.ui.screen.chiptextfield.ChipTextFieldScreen
import com.jakchang.deisgnlibrary.ui.screen.fadingedgescroll.FadingEdgeScrollScreen
import com.jakchang.deisgnlibrary.ui.screen.smarttab.SmartTabScreen

@Composable
fun NavHostScreen(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "main"
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("main") {
            MainScreen(navController = navController)
        }
        composable(PageType.SmartTab.name) {
            SmartTabScreen()
        }
        composable(PageType.ChipTextField.name) {
            ChipTextFieldScreen()
        }
        composable(PageType.FadingEdgeScroll.name) {
            FadingEdgeScrollScreen()
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            NavigationText(
                text = PageType.SmartTab.name
            ) {
                navController.navigate(PageType.SmartTab.name)
            }
        }
        item {
            NavigationText(
                text = PageType.ChipTextField.name
            ) {
                navController.navigate(PageType.ChipTextField.name)
            }
        }
        item {
            NavigationText(
                text = PageType.FadingEdgeScroll.name
            ) {
                navController.navigate(PageType.FadingEdgeScroll.name)
            }
        }
    }
}

@Composable
fun NavigationText(
    text: String,
    navigateClickListener: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clickable {
                    navigateClickListener.invoke()
                },
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}