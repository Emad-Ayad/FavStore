package com.example.test.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.test.presentation.Screens


@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        Button(onClick = { navController.navigate(Screens.Products.route)
        },
            content = { Text(text = "Go to Products Screen") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = { navController.navigate(Screens.Fav.route)},
            content = { Text(text = "Go to Fav Screen") }
        )




    }
}