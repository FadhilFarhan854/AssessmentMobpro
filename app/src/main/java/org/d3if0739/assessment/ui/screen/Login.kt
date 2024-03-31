package org.d3if0739.assessment.ui.screen

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.d3if0739.assessment.Greeting
import org.d3if0739.assessment.R
import org.d3if0739.assessment.navigation.Screen
import org.d3if0739.assessment.ui.theme.AssessmentTheme
import java.lang.Error


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen( navController : NavHostController ){
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.login)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    ){
           paddingValues -> paddingValues
        val context = LocalContext.current
        LoginForm(
            onLoginPressed = { navController.navigate(Screen.MainScreen.route) },
            onDontHaveAccount = { navController.navigate(Screen.Register.route) },

        )
    }


}

@Composable
fun LoginForm(
              onDontHaveAccount: () -> Unit,
              onLoginPressed: ()-> Unit
              ) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember {
        mutableStateOf(false)
    }
    var passwordError by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            isError = emailError,
            trailingIcon = { IconPicker(isError = emailError, unit =" ")},
            supportingText = { ErrorHint(isError = emailError)}

        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            isError = passwordError,
            trailingIcon = { IconPicker(isError = passwordError, unit = "" )},
            supportingText = { ErrorHint(isError = passwordError)}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                    emailError = (email == "" || email == "0")
                    passwordError = (password == "" || password == "0")
                    if (emailError || passwordError) return@Button
                    onLoginPressed()
                      },


            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(stringResource(R.string.akun))
        ClickableText(
            text = AnnotatedString(stringResource(R.string.register)),
            onClick = {
                // Handle click event
                onDontHaveAccount()
            }
        )
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String){
    if(isError){
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }else{
        Text(text = unit)
    }
}
@Composable
fun ErrorHint(isError: Boolean){
    if(isError){
        Text(text = stringResource(R.string.input_invalid))
    }
}




