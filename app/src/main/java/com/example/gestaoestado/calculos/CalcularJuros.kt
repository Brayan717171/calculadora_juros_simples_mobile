package com.example.gestaoestado.calculos

// Juros simples: J = C * (i / 100) * t
fun calcularJuros(capital: Double, taxa: Double, tempo: Double): Double {
    return capital * (taxa / 100) * tempo
}

// Montante: M = C + J
fun calcularMontante(capital: Double, juros: Double): Double {
    return capital + juros
}
