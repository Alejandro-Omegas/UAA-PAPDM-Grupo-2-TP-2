package com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.MainActivity
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.R
import com.alejandroarriola.uaa_papdm_grupo_2_tp_2.ui.navigation.NavDestino

object HomeDestino: NavDestino {
    override val ruta = "home"
    override val titulo = R.string.app_name
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeSreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { exitApp() },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.exits_the_app)
                )
            }
        }
    ) {
        Column(
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            modifier = Modifier
        ) {
            Text(text = stringResource(R.string.app_name))

            Button(
                onClick = { /*TODO*/ }
            ) {
                Text(text = stringResource(R.string.ver_stock))
            }

            OutlinedButton(
                onClick = { /*TODO*/ }
            ) {
                Text(text = stringResource(R.string.about))
            }
        }
    }
}

fun exitApp() {
    val activity = MainActivity()
    activity.finish()
    System.exit(0)
}

@Preview(showBackground = true)
@Composable
fun HomeSreenPreview() {
    HomeSreen()
}
