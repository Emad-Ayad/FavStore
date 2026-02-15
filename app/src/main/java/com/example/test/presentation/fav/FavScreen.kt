package com.example.test.presentation.fav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.test.presentation.fav.view_model.FavViewModel
import com.example.test.presentation.fav.view_model.FavFactory
import com.example.test.ui.theme.TestTheme


@Composable
fun FavScreen(navController: NavController,viewModel: FavViewModel = viewModel(
    factory = FavFactory(LocalContext.current.applicationContext))){

    val products = viewModel.products.value
    val error = viewModel.error.observeAsState().value
    val isLoading = viewModel.isLoading.observeAsState().value

    if (isLoading == true){
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator()
        }
    }else if (!error.isNullOrEmpty()){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(error)
        }
    }else{
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(products) { product ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(product.thumbnail),
                        contentDescription = "Product Image",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))

                    Column(modifier = Modifier.width(170.dp)) {
                        Text(text = product.title,fontWeight = FontWeight.Bold)
                        Text(text = product.price.toString())
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            viewModel.deleteFavProduct(product)
                        }) {

                        Text("delete")
                    }

                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val navController = rememberNavController()
    TestTheme {
        FavScreen(navController)
    }
}