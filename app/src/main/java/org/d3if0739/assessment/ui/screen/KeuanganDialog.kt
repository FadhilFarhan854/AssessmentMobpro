package org.d3if0739.assessment.ui.screen

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.d3if0739.assessment.R
import org.d3if0739.assessment.model.Keuangan

@Composable
fun KeuanganDialog(
    bitmap: Bitmap?,
    onDismissRequest: () -> Unit,
    onConfirmation: (String, String, String) -> Unit,

){

    var tanggal by remember {
        mutableStateOf("")
    }
    var jumlah by remember {
        mutableStateOf("")
    }
    var jenis by remember {
        mutableStateOf("")
    }
    var tanggalError by  remember{
        mutableStateOf(false)
    }
    var jenisError by  remember{
        mutableStateOf(false)
    }
    var jumlahError by  remember{
        mutableStateOf(false)
    }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    bitmap = bitmap!!.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
                OutlinedTextField(
                    value = tanggal,
                    onValueChange = {tanggal = it},
                    label ={ Text(text = stringResource(id = R.string.tanggal)) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    isError = tanggalError,
                    trailingIcon = {IconPickerOnline(isError = tanggalError, "" )},
                    supportingText = {ErrorhintTanggal(tanggalError)},
                    modifier = Modifier.padding(top = 8.dp)

                )
                OutlinedTextField(
                    value = jumlah,
                    onValueChange = {jumlah = it},
                    label ={ Text(text = stringResource(id = R.string.jumlah)) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number

                    ),
                    isError = jumlahError,
                    trailingIcon = {IconPickerOnline(isError = jumlahError, "" )},
                    supportingText = { ErrorhintJumlah(jumlahError) },


                )
                OutlinedTextField(
                    value = jenis,
                    onValueChange = {jenis = it},
                    label ={ Text(text = stringResource(id = R.string.jenis)) },
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done
                    ),
                    isError = jenisError,
                    trailingIcon = {IconPickerOnline(isError = jenisError, "" )},
                    supportingText = { ErrorhintJEnis(jenisError) },


                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    OutlinedButton(
                        onClick = {onDismissRequest()},
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = stringResource(R.string.tombol_batal))
                    }
                    OutlinedButton(
                        onClick = {
                            tanggalError = (tanggal == "" || tanggal.length > 10 || tanggal.length < 8)
                            jumlahError = (jumlah == "")
                            jenisError = (jenis == "")
                            if(tanggalError || jenisError || jumlahError) return@OutlinedButton
                            onConfirmation(tanggal, jumlah, jenis) },
                        enabled = tanggal.isNotEmpty() && jumlah.isNotEmpty() && jenis.isNotEmpty(),
                        modifier = Modifier.padding(8.dp))
                        {
                            Text(text = stringResource(R.string.simpan))
                        }


                }
            }
        }
    }
}
@Composable
fun OptionClass(label: String, isSelected: Boolean, modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
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
fun IconPickerOnline(isError: Boolean, unit: String){
    if(isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }else{
        Text(text = unit)
    }
}


@Composable
fun ErrorhintTanggal(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid2))
    }
}
@Composable
fun ErrorhintJumlah(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}
@Composable
fun ErrorhintJEnis(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}