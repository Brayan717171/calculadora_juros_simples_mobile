package com.example.gestaoestado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestaoestado.juros.JurosScreen
import com.example.gestaoestado.juros.JurosScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                // Instância da ViewModel (sobrevive à rotação de tela)
                val jurosScreenViewModel: JurosScreenViewModel = viewModel()
                JurosScreen(jurosScreenViewModel)
            }
        }
    }
}