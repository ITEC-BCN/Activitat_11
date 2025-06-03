package models

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class BarallaTest {

    private lateinit var baralla: Baralla

    @BeforeEach
    fun setup() {
        baralla = Baralla()
        baralla.crearBaralla()
    }

    //crearBaralla()
    @Test
    fun testCrearBaralla_creates48Cards() {
        baralla.crearBaralla()
        val niceOutput = baralla.niceNtidy()
        assertTrue(niceOutput.contains("Ors"))
        assertEquals(12, niceOutput.lines().filter { it.isNotBlank() }.size)
    }

    @Test
    fun testCrearBaralla_resetsPileAndMunt() {
        baralla.barrejar()
        val beforePileSize = baralla.repartirCartes(3)?.size ?: 0
        baralla.crearBaralla()
        val newPileCards = baralla.repartirCartes(12)
        assertEquals(12, newPileCards?.size)
        assertNotEquals(beforePileSize, newPileCards?.size)
    }

    //Aquest test falla perque per alguna raó, el mètode niceNtidy() fa el doble de separació la segona vegada.
    @Test
    fun testCrearBaralla_repeatedCallsProduceSameDeck() {
        baralla.crearBaralla()
        val firstDeck = baralla.niceNtidy()
        println(firstDeck)
        baralla.crearBaralla()
        val secondDeck = baralla.niceNtidy()
        println(secondDeck)
        assertEquals(firstDeck, secondDeck)
    }


    //barrejar()
    @Test
    fun testBarrejar_changesOrder() {
        val original = baralla.toString()
        baralla.barrejar()
        val shuffled = baralla.toString()
        assertNotEquals(original, shuffled)
    }

    @Test
    fun testBarrejar_pileSizeRemainsSame() {
        val sizeBefore = baralla.repartirCartes(5)?.size?.let { 48 - it } ?: 48
        baralla.barrejar()
        val sizeAfter = baralla.repartirCartes(5)?.size?.let { 48 - it } ?: 48
        assertEquals(sizeBefore, sizeAfter)
    }

    @Test
    fun testBarrejar_multipleShufflesProduceDifferentOrder() {
        baralla.barrejar()
        val firstShuffle = baralla.toString()
        baralla.barrejar()
        val secondShuffle = baralla.toString()
        assertNotEquals(firstShuffle, secondShuffle)
    }


    //repartirCartes()
    @Test
    fun testRepartirCartes_returnsNullIfNotEnoughCards() {
        baralla.repartirCartes(48) // Use all cards
        val cartes = baralla.repartirCartes(1) // None left
        assertNull(cartes)
    }

    @Test
    fun testRepartirCartes_returnsCorrectNumber() {
        val cartes = baralla.repartirCartes(10)
        assertNotNull(cartes)
        assertEquals(10, cartes!!.size)
    }

    @Test
    fun testRepartirCartes_pileReducesCorrectly() {
        val sizeBefore = baralla.pilaSize()
        baralla.repartirCartes(7)
        val sizeAfter = baralla.pilaSize()
        assertEquals(sizeBefore - 7, sizeAfter)
    }


    //seguentCarta()
    @Test
    fun testSeguentCarta_returnsNullIfEmptyPile() {
        baralla.repartirCartes(48) // Exhaust pile
        val carta = baralla.seguentCarta()
        assertNull(carta)
    }

    @Test
    fun testSeguentCarta_returnsCardAndRemovesFromPile() {
        val sizeBefore = baralla.pilaSize()
        val carta = baralla.seguentCarta()
        val sizeAfter = baralla.pilaSize()
        assertNotNull(carta)
        assertEquals(sizeBefore - 1, sizeAfter)
    }

    @Test
    fun testSeguentCarta_multipleCallsReducePile() {
        val initialSize = baralla.pilaSize()
        repeat(5) { baralla.seguentCarta() }
        assertEquals(initialSize - 5, baralla.pilaSize())
    }


    //demanarCartes()
    @Test
    fun testDemanarCartes_returnsNullIfNotEnoughCards() {
        baralla.repartirCartes(47) // Leave 1 card
        val cartes = baralla.demanarCartes(2, emptyList())
        assertNull(cartes)
    }

    @Test
    fun testDemanarCartes_addsReturnedCardsToMunt() {
        val retornades = listOf(Carta("Ors", 1))
        baralla.demanarCartes(1, retornades)
        assertTrue(baralla.veureMunt().containsAll(retornades))
    }

    @Test
    fun testDemanarCartes_returnsCorrectNumberOfCards() {
        val cartes = baralla.demanarCartes(3, emptyList())
        assertNotNull(cartes)
        assertEquals(3, cartes!!.size)
    }


    //veureMunt()
    @Test
    fun testVeureMunt_returnsEmptyIfNoCards() {
        val munt = baralla.veureMunt()
        assertTrue(munt.isEmpty())
    }

    @Test
    fun testVeureMunt_containsReturnedCards() {
        val retornades = listOf(Carta("Basts", 5))
        baralla.demanarCartes(1, retornades)
        val munt = baralla.veureMunt()
        assertTrue(munt.containsAll(retornades))
    }

    @Test
    fun testVeureMunt_returnsCopyNotReference() {
        val munt = baralla.veureMunt()
        val sizeBefore = munt.size
        // Try to modify returned list if possible (depending on implementation)
        if (munt is MutableList) munt.clear()
        val sizeAfter = baralla.veureMunt().size
        assertEquals(sizeBefore, sizeAfter)
    }


    //niceNtidy()
    @Test
    fun testNiceNtidy_returnsNonEmptyString() {
        val output = baralla.niceNtidy()
        assertTrue(output.isNotBlank())
    }

    @Test
    fun testNiceNtidy_containsCorrectNumberOfLines() {
        val output = baralla.niceNtidy()
        assertEquals(12, output.lines().filter { it.isNotBlank() }.size)
    }

    @Test
    fun testNiceNtidy_containsAllSuits() {
        val output = baralla.niceNtidy()
        assertTrue(output.contains("Ors") && output.contains("Copes") && output.contains("Espases") && output.contains("Basts"))
    }


    //toString()
    @Test
    fun testToString_containsSeparators() {
        val output = baralla.toString()
        assertTrue(output.contains("|"))
    }

    @Test
    fun testToString_containsNewlines() {
        val output = baralla.toString()
        assertTrue(output.contains("\n"))
    }

    @Test
    fun testToString_returnsNonEmptyString() {
        val output = baralla.toString()
        assertTrue(output.isNotBlank())
    }
}