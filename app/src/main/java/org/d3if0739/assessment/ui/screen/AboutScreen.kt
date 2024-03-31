package org.d3if0739.assessment.ui.screen

import android.content.Context
import android.content.Intent

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if0739.assessment.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController){



    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                   IconButton(onClick = { navController.popBackStack() }) {
                       Icon(imageVector = Icons.Filled.ArrowBack,
                           contentDescription = stringResource(id = R.string.kembali),
                           tint = MaterialTheme.colorScheme.primary
                       )
                   }
                },

                title = { Text(text = stringResource(id = R.string.tentang_aplikasi )) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )

            )
        }

    ) {
        paddingValues -> paddingValues
        ScreenAbout()



    }
    
    
}

@Composable
fun ScreenAbout(){
    val context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = painterResource(id = R.drawable.output),
            contentDescription = "This is raster image",
            modifier = Modifier.padding(top = 75.dp),
        )
        Text(text = stringResource(id = R.string.aboutApp),
            modifier = Modifier

                .padding(16.dp)
        )
        Button( onClick = { shareData(
            context = context,
            message = "https://github.com/FadhilFarhan854/AssessmentMobpro"
        ) },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)

            )  {
            Text(text = stringResource(id = R.string.bagikan))
        }




    }

}

private fun shareData(context: Context, message: String){
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager)!= null){
        context.startActivity(shareIntent)
    }
}





