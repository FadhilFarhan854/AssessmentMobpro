package org.d3if0739.assessment.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.d3if0739.assessment.R
import org.d3if0739.assessment.model.Keuangan
import org.d3if0739.assessment.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController){

    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.financial)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }) {
                        Icon(imageVector = Icons.Outlined.Info ,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

            )
        },

    ){
        paddingValues -> paddingValues
        ScreenContent(/*viewModel = viewModel*/)
    }

}

private fun getJenis(isPemasukan: Boolean): String {
    return if (isPemasukan) {
        "pemasukan"
    } else {
        "pengeluaran"
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun ScreenContent(/*viewModel: MainViewModel*/){
//    val data = viewModel.data
    val context  = LocalContext.current
    var tanggal by rememberSaveable {mutableStateOf("") }
    var jumlah by rememberSaveable {mutableStateOf("") }
    var jenis by rememberSaveable {mutableStateOf("") }
    val datas: MutableList<Keuangan> by rememberSaveable {
        mutableStateOf(mutableListOf())
    }
    var tanggalError by remember {
        mutableStateOf(false)
    }
    var jumlahError by remember {
        mutableStateOf(false)
    }

    val inputOptions = listOf(
        stringResource(id = R.string.pemasukan),
        stringResource(id = R.string.pengeluaran)
    )
    var option by rememberSaveable {
        mutableStateOf(inputOptions[0])
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 50.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally


    ) {
        OutlinedTextField(
            value = tanggal,
            onValueChange = {tanggal = it},
            label = { Text(text = stringResource(R.string.tanggal))},
            singleLine = true,
            placeholder = { Text("DDMMYYYY")},
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            isError = tanggalError,
            trailingIcon = { IconPickerMain(isError = tanggalError, unit = "" ) },
            supportingText = { ErrorHintTanggal(isError = tanggalError) }
        )
        OutlinedTextField(
            value = jumlah,
            onValueChange = {jumlah = it},
            label = { Text(text = stringResource(R.string.jumlah))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth(),
            isError = jumlahError,
            trailingIcon = { IconPickerMain(isError = jumlahError, unit = "" ) },
            supportingText = { ErrorHintJumlah(isError = jumlahError) }
        )
        Row (
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){
            inputOptions.forEach{
                text -> InputOption(
                label = text,
                isSelected = option == text,
                modifier = Modifier
                    .selectable(
                        selected = option == text,
                        onClick = { option = text },
                        role = Role.RadioButton
                    )
                    .weight(1f)
                    .padding(16.dp)
            )
            }
        }
        Button(
            onClick = {
                tanggalError = (tanggal == "" || tanggal == "0" || tanggal.length !=8)
                jumlahError = (jumlah == "" || jumlah == "0")
                if (jumlahError || tanggalError) return@Button
                jenis = getJenis( option == inputOptions[0] )
                val jenisValue = getJenis( option == inputOptions[0])
//                viewModel.addData(tanggal, jenisValue, jumlah)
                datas.add(Keuangan(tanggal, jenisValue, jumlah))
                tanggal = ""
                jumlah = ""


            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),

        ) {
            Text(text = stringResource(R.string.tambah))
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            items(datas) {
                ListItem(keuangan = it){
                    val msg = "share this note ?"

                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

                }
                Divider()
            }

        }


    }
}
@Composable
fun InputOption(label :String, isSelected: Boolean, modifier: Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){
        RadioButton(selected = isSelected, onClick = null )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(start = 8.dp)
        )

    }
}
@Composable
fun ListItem(keuangan: Keuangan, onClick: ()-> Unit){
    Column (modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = keuangan.tanggal,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold

        )
        Text(
            text = keuangan.jenis,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,

        )
        Text(
            text = keuangan.jumlah,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
@Composable
fun IconPickerMain(isError: Boolean, unit: String){
    if(isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }else{
        Text(text = unit)
    }
}
@Composable
fun ErrorHintTanggal(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid2))
    }
}
@Composable
fun ErrorHintJumlah(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}







