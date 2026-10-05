package com.example.gestaoestado.juros

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gestaoestado.calculos.calcularJuros
import com.example.gestaoestado.calculos.calcularMontante

class JurosScreenViewModel : ViewModel() {

    // Privadas (underscore notation): só a ViewModel altera
    private val _capital = MutableLiveData<String>("")
    private val _taxa = MutableLiveData<String>("")
    private val _tempo = MutableLiveData<String>("")
    private val _juros = MutableLiveData<Double>(0.0)
    private val _montante = MutableLiveData<Double>(0.0)

    // Públicas (somente leitura): a View observa por aqui
    val capital: LiveData<String> = _capital
    val taxa: LiveData<String> = _taxa
    val tempo: LiveData<String> = _tempo
    val juros: LiveData<Double> = _juros
    val montante: LiveData<Double> = _montante

    fun onCapitalChange(novoValor: String) {
        _capital.value = novoValor
    }

    fun onTaxaChange(novoValor: String) {
        _taxa.value = novoValor
    }

    fun onTempoChange(novoValor: String) {
        _tempo.value = novoValor
    }

    // Regra de negócio: LiveData é sempre T?, então tratamos os nulos/vazios
    fun calcular() {
        val capital = _capital.value?.toDoubleOrNull() ?: return
        val taxa = _taxa.value?.toDoubleOrNull() ?: return
        val tempo = _tempo.value?.toDoubleOrNull() ?: return

        val jurosCalculado = calcularJuros(capital, taxa, tempo)
        _juros.value = jurosCalculado
        _montante.value = calcularMontante(capital, jurosCalculado)
    }
}
