package com.enterprise.dialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.enterprise.dialog.ui.theme.DialogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val activityFinisher = { this.finish() }

        setContent {
            DialogTheme {

                DialogApp(activityFinisher = activityFinisher)

            }
        }
    }
}

@Composable
fun DialogApp(activityFinisher: () -> Unit) {

    var isDialogVisible = rememberSaveable { mutableStateOf(false) }

    if(isDialogVisible.value){

        DialogOfApp(isDialogVisible = isDialogVisible,
            activityFinisher = activityFinisher)

    }

    Scaffold(modifier = Modifier.systemBarsPadding().fillMaxSize()) { innerPadding ->

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(innerPadding).fillMaxSize()){

            Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                onClick = {

                    isDialogVisible.value = true

                }) {

                Text(text = stringResource(id = R.string.show_exit_dialog))

            }

        }


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogOfApp(isDialogVisible: MutableState<Boolean>, activityFinisher: () -> Unit) {

    BasicAlertDialog(onDismissRequest = {
        // Dismiss the dialog when the user clicks outside the dialog or on the back button.
        //isDialogVisible.value = false
    }){

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentHeight()
                .wrapContentWidth().
                background(color = Color.White, RoundedCornerShape(size = 15.dp))
                    .padding(15.dp)){

            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()){

                Icon(imageVector = Icons.Default.Info,
                    contentDescription
                    = stringResource(id = R.string.dialog_title_icon_content_description),
                    tint = Color.Blue)

                Spacer(modifier = Modifier.width(5.dp))

                Text(text = stringResource(id = R.string.dialog_title))


            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = stringResource(id = R.string.dialog_message))

            Spacer(modifier = Modifier.height(15.dp))

            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()){

                Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
                    modifier = Modifier.weight(1F),
                    onClick = {

                        isDialogVisible.value = false

                        activityFinisher()

                    }) {

                    Text(text = stringResource(id = R.string.dialog_positive_button_text))

                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.weight(1F),
                    onClick = {

                        isDialogVisible.value = false

                    }) {

                    Text(text = stringResource(id = R.string.dialog_negative_button_text))

                }

            }

        }

    }

}

