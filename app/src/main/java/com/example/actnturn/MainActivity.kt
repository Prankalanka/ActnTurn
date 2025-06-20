package com.example.actnturn

import android.content.Context
import android.os.Bundle
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

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    private lateinit var dataStoreManager: DataStoreManager

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataStoreManager = DataStoreManager(context = applicationContext)

        enableEdgeToEdge()
        setContent {
            ActnTurnTheme {
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
                        ServiceSwitch(viewModel)
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
    val switch = viewModel.serviceSwitch.collectAsState()

    Row (verticalAlignment = Alignment.CenterVertically) {
        Text (text = switch.value.text)
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = switch.value.isChecked,
            // Set isChecked to current value
            onCheckedChange = {viewModel.toggleServiceSwitch()}
        )
    }
}