package com.example.gestaoestado.juros

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestaoestado.components.CaixaDeEntrada
import com.example.gestaoestado.components.CardResultado

private val Roxo = Color(0xFF7B33BE)

@Composable
fun JurosScreen(jurosScreenViewModel: JurosScreenViewModel) {

    // Estados observáveis vindos da ViewModel
    val capital by jurosScreenViewModel.capital.observeAsState("")
    val taxa by jurosScreenViewModel.taxa.observeAsState("")
    val tempo by jurosScreenViewModel.tempo.observeAsState("")
    val juros by jurosScreenViewModel.juros.observeAsState(0.0)
    val montante by jurosScreenViewModel.montante.observeAsState(0.0)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F4FF))
    ) {
        // Faixa roxa do topo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
                .background(Roxo)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Calculadora Juros Simples",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F4F6)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Dados do investimento",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Roxo
                    )

                    CaixaDeEntrada(
                        label = "Valor investimento",
                        placeholder = "0.0",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = capital,
                        onValueChange = { jurosScreenViewModel.onCapitalChange(it) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    CaixaDeEntrada(
                        label = "Taxa de juros mensal",
                        placeholder = "0.0",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = taxa,
                        onValueChange = { jurosScreenViewModel.onTaxaChange(it) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    CaixaDeEntrada(
                        label = "Período em meses",
                        placeholder = "0",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = tempo,
                        onValueChange = { jurosScreenViewModel.onTempoChange(it) },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Button(
                        onClick = { jurosScreenViewModel.calcular() },
                        colors = ButtonDefaults.buttonColors(containerColor = Roxo),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp)
                    ) {
                        Text(text = "CALCULAR")
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            CardResultado(juros = juros, montante = montante)
        }
    }
}
