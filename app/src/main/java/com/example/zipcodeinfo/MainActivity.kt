package com.example.zipcodeinfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.zipcodeinfo.presentation.ZipDataScreen
import com.example.zipcodeinfo.presentation.ZipDataViewModel
import com.example.zipcodeinfo.ui.theme.ZipCodeInfoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ZipCodeInfoTheme {
                val viewModel: ZipDataViewModel = hiltViewModel()
                val state by viewModel.zipDataState.collectAsState()
                val onCheckZip = viewModel::loadZipData

                ZipDataScreen(
                    state = state,
                    onZipCheck = onCheckZip
                )
            }
        }
    }
}