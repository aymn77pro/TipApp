@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.tipapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.TextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipapp.ui.theme.TipAppTheme
import java.text.NumberFormat
import androidx.compose.ui.res.painterResource as painterResource1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
                TipTimeScreen()

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipTimeScreen(){

    var numberInput by remember{ mutableStateOf("") }
    val amount = numberInput.toDoubleOrNull() ?: 0.0

    var percentageNum by remember { mutableStateOf("") }
    val amountPercentage = percentageNum.toDoubleOrNull() ?: 15.0


    val tip = tipCalu(amount,amountPercentage)

    Column(
        Modifier.padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.calculate_tip),
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        Spacer(modifier = Modifier.height(16.dp))
        // edit text fun called TextField
        TextNumber(
            value = numberInput,
            onValueChange = {numberInput = it}
        )
        // مسافه بين العناصر
        Spacer(modifier = Modifier.height(24.dp))

        TextPre(
            value = percentageNum,
            onValueChange = {percentageNum = it}
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.tip_amount,tip),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@SuppressLint("UnrememberedMutableState")
@ExperimentalMaterial3Api
@Composable
fun TextNumber(
    value: String,
    onValueChange: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {Text(stringResource(id = R.string.calculate_tip))},
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextPre(
  value : String,
  onValueChange: (String) -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = stringResource(R.string.tip_percentage))
                },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        trailingIcon= { Icon(painter = painterResource1(id = R.drawable.ic_baseline_percent_24), contentDescription ="prsange" )}
        )
}

private fun tipCalu(price:Double,prassing:Double = 15.0):String{
val tip = price * (prassing/100)
   return NumberFormat.getCurrencyInstance().format(tip)

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    TipAppTheme {
    TipTimeScreen()
    }
}

