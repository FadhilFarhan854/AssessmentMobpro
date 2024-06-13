package org.d3if0739.assessment.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import org.d3if0739.assessment.R
import org.d3if0739.assessment.model.Hewan
import org.d3if0739.assessment.network.KeuanganApi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnlineModeScreen( navController: NavHostController){
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name )) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    ){
            paddingValues -> ScreenContent(Modifier.padding(paddingValues));
    }


}

@Composable
fun ScreenContent(modifier : Modifier){
    val viewModel: OnlineViewModel = viewModel()
    val data by viewModel.data
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxSize()
            .padding(4.dp),
        columns = GridCells.Fixed(2)
    )
    {
        items(data){ListItem(hewan = it)}
    }


}

@Composable
fun ListItem(hewan: Hewan){
    Box(
        modifier = Modifier
            .padding(4.dp)
            .border(1.dp, Color.Gray),
        contentAlignment = Alignment.BottomCenter
    ){
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current).data(KeuanganApi.getHewanUrl(hewan.imageId)).crossfade(true).build(),
            contentDescription = stringResource(R.string.gambar, hewan.nama),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth().
                padding(4.dp).background(Color(0f,0f,0f,0.5f)).padding(4.dp)


//                .background(Color(red = 0,green = 0, blue =0, alpha = 0.5f))
        ) {
            Text(
                text = hewan.nama,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = hewan.namaLatin,
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}