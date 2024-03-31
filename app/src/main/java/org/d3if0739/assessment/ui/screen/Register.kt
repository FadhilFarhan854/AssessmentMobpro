package org.d3if0739.assessment.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if0739.assessment.Greeting
import org.d3if0739.assessment.R
import org.d3if0739.assessment.navigation.Screen
import org.d3if0739.assessment.ui.theme.AssessmentTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController : NavHostController){
    Scaffold (
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
                title = { Text(text = stringResource(id = R.string.register))},
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    ){
            paddingValues -> paddingValues
        RegisterForm(
            onRegisterPressed = { navController.navigate(Screen.Login.route) },
            onHaveAccount = { navController.navigate(Screen.Login.route) },

            )
    }


}

@Composable
fun RegisterForm(onHaveAccount: () -> Unit,
                 onRegisterPressed: ()-> Unit){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var userame by remember {
        mutableStateOf("")
    }
    var emailErrors  by remember {
        mutableStateOf(false)
    }
    var passwordErrors by remember {
        mutableStateOf(false)
    }
    var usernameErrors by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = {Text("Email")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = emailErrors,
            trailingIcon = { IconPickers(isError = emailErrors, unit =" ") },
            supportingText = { ErrorHints(isError = emailErrors) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = userame,
            onValueChange = {userame = it},
            label = {Text("Username")},
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = usernameErrors,
            trailingIcon = { IconPickers(isError = usernameErrors, unit =" ")},
            supportingText = { ErrorHints(isError = usernameErrors) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = passwordErrors,
            trailingIcon = { IconPickers(isError = passwordErrors, unit =" ")},
            supportingText = { ErrorHints(isError = passwordErrors)}
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                emailErrors = (email == "" || email == "0")
                passwordErrors = (password == "" || password == "0")
                usernameErrors = (userame == "" || email == "0")
                onRegisterPressed()
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.register))
        }

    }
}

@Composable
fun IconPickers(isError: Boolean, unit: String){
    if(isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }else{
        Text(text = unit)
    }
}
@Composable
fun ErrorHints(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}



