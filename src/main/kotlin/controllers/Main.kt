package org.example.controllers

import models.Baralla
import models.Carta

fun main() {
    val prova1= Carta("Ors", 8)
    println(prova1.toString())
    val prova2=Carta("Copes", 8)
    println(prova2.toString())
    val prova3=Carta("Espases", 8)
    println(prova3.toString())
    val prova4=Carta("Basts", 8)
    println(prova4.toString())
    var baralla= Baralla()
    baralla.crearBaralla()
    println(baralla.toString())
}