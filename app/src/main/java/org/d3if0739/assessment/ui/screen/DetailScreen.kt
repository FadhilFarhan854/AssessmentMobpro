package org.d3if0739.assessment.ui.screen

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.d3if0739.assessment.R
import org.d3if0739.assessment.database.KeuanganDb
import org.d3if0739.assessment.util.ViewModelFactory

const val KEY_ID_CATATAN = "idCatatan"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null){
    val context = LocalContext.current
    val db = KeuanganDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val viewModel2: DetailViewModel = viewModel(factory = factory)
    var tanggal by remember { mutableStateOf("") }
    var  jumlah by remember { mutableStateOf("") }
    var jenis by remember { mutableStateOf("") }
    var tanggalError by remember {
        mutableStateOf(false)
    }
    var jumlahError by remember {
        mutableStateOf(false)
    }
    var jenisError by remember {
        mutableStateOf(false)
    }

    var showDilaog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true){
        if(id == null) return@LaunchedEffect
        val data = viewModel2.getData(id) ?: return@LaunchedEffect
        tanggal = data.tanggal
        jumlah = data.jumlah
        jenis = data.jenis
    }
    val radioOption = listOf(
        "Pemasukan",
        "Pengeluaran"

    )

    Scaffold(
        topBar =
        {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {navController.popBackStack()}) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack ,
                            contentDescription = stringResource(R.string.kembali ),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null){
                        Text(text = stringResource(id = R.string.tambah_catatan))
                    }else{
                        Text(text = stringResource(id = R.string.edit_catatan))
                    }

                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        tanggalError = (tanggal == "" || tanggal.length > 10 || tanggal.length<8)
                        jumlahError = (jumlah == "")
                        if(tanggalError || jumlahError) return@IconButton
                        if(id == null){
                            viewModel2.insert(tanggal, jumlah, jenis)
                        }else{
                            viewModel2.update(id, tanggal, jumlah, jenis)
                        }
                        navController.popBackStack() }) {
                        Icon(imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null ){

                        DeleteAction {
                            showDilaog = true
                        }
                        DisplayAlertDialog(
                            openDialog = showDilaog,
                            onDismissRequest = { showDilaog = false }) {
                            showDilaog = false
                            viewModel2.delete(id)
                            navController.popBackStack()
                        }
                    }
                }
            )
        }
    ){
            padding -> FormCatatan(
        tanggal = tanggal,
        onTanggalChange =  {tanggal = it},
        jumlah = jumlah,
        onJumlahChange = {jumlah = it} ,
        jenis = jenis ,
        onJenisChanged = {jenis= it} ,
        radioOption = radioOption,
        modifier = Modifier.padding(padding),
                tanggalError = tanggalError,
                jumlahError = jumlahError
    )
    }


}
@Composable
fun FormCatatan(
    tanggal: String, onTanggalChange: (String) -> Unit,
    jumlah: String, onJumlahChange: (String) -> Unit,
    jenis: String, onJenisChanged: (String) -> Unit,
    radioOption: List<String>,
    modifier: Modifier = Modifier,
    tanggalError: Boolean,
    jumlahError : Boolean

) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        OutlinedTextField(
            value = tanggal,
            onValueChange = { onTanggalChange(it) },
            label = { Text(text = stringResource(R.string.tanggal)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
            ),
            isError = tanggalError,
            trailingIcon = {IconPickerOffline(isError = tanggalError, unit ="" )},
            supportingText ={ ErrorhintTanggalOffline(isError = tanggalError) } ,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = jumlah,
            onValueChange = { onJumlahChange(it) },
            label = { Text(text = stringResource(R.string.jumlah)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
            ),
            isError = tanggalError,
            trailingIcon = {IconPickerOffline(isError = tanggalError, unit ="" )},
            supportingText ={ ErrorhintJumlahOffline(isError = tanggalError) } ,
            modifier = Modifier.fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOption.forEach { text ->
                KelasOpsi(
                    label = text,
                    isSelected = jenis == text,
                    modifier = Modifier
                        .selectable(
                            selected = jenis == text,
                            onClick = { onJenisChanged(text) },
                            role = Role.RadioButton
                        )
                        .padding(16.dp)
                )
            }
        }
    }
}


@Composable
fun KelasOpsi(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isSelected){
            RadioButton(selected = true, onClick = null)
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
        else{
            RadioButton(selected = false, onClick = null)
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun DeleteAction(delete: () -> Unit){
    var expanded by remember {
        mutableStateOf(false)
    }
    IconButton(onClick = { expanded = true }) {
        Icon(imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false }) {

            DropdownMenuItem(text = { Text(text = stringResource(id = R.string.hapus))}, onClick = {
                expanded = false
                delete()})
        }

    }
}
@Composable
fun IconPickerOffline(isError: Boolean, unit: String){
    if(isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }else{
        Text(text = unit)
    }
}

@Composable
fun ErrorhintTanggalOffline(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid2))
    }
}
@Composable
fun ErrorhintJumlahOffline(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}