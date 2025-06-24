package com.example.actnturn

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.actnturn.ui.theme.ActnTurnTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var dataStoreManager: DataStoreManager

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("GEN", "defaultOnCreate")

        dataStoreManager = DataStoreManager(context = applicationContext)

        Log.d("GEN", "created dataStore")

        enableEdgeToEdge()
        Log.d("GEN", "edge to edge")

        setContent {
            ActnTurnTheme {
                Log.d("GEN", "themed")
                Scaffold(
                    topBar = {
                        TopAppBar(
                        title = {Text("Act 'n' Turn")}
                    )
                    },
                ) {
                        innerPadding -> // padding calculated by scaffold
                    // it doesn't have to be a column,
                    // can be another component that accepts a modifier with padding
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = innerPadding) // padding applied here
                    ) {
                        // all content should be here
                        Log.d("GEN", "start creating content")
                        ServiceSwitch(viewModel)
                        Log.d("GEN", "finish creating content")
                    }
                }
            }
        }
    }
}

data class ToggleableInfo (
    val isChecked: Boolean,
    val text: String,
)

@Composable
fun ServiceSwitch(viewModel: MainViewModel) {
    val switchState = viewModel.serviceSwitch.collectAsState(false)

    Log.d("GEN", "recomposed")

    Row (verticalAlignment = Alignment.CenterVertically) {
        Text (text = "Service running")
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = switchState.value,
            // Set isChecked to current value
            onCheckedChange = {
                viewModel.saveServiceSwitch(!switchState.value)
                Log.d("GEN", "compose change ${!switchState.value}")
            }
        )
    }
}