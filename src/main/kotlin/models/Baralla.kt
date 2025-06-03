package models

import kotlin.random.Random

class Baralla() {
    private var baralla= mutableListOf<Carta>()
    private  var pila= mutableListOf<Carta>()
    private  var munt= mutableListOf<Carta>()

    /**
     * Crea una baralla espanyola de 48 cartes i l'assigna a la pila.
     *
     * Aquesta funció inicialitza la baralla afegint cartes dels quatre pals:
     * Ors, Copes, Espases i Bastos, cadascun amb valors de l'1 al 12.
     * Un cop creades, les cartes es guarden a la pila.
     *
     * @throws IllegalStateException si la baralla ja ha estat inicialitzada prèviament.
     */
    fun crearBaralla(){
        for(i in 1..48){
            when{
                i in 1..12->{
                    baralla.add(Carta("Ors",i))
                }
                i in 13..24->{
                    baralla.add(Carta("Copes",i-12))
                }
                i in 25..36->{
                    baralla.add(Carta("Espases",i-24))
                }
                i in 37..48->{
                    baralla.add(Carta("Basts",i-36))
                }
            }
        }
        pila=baralla
    }

    /**
     * Barreja aleatòriament les cartes de la baralla i les assigna a la pila.
     *
     * @throws IllegalStateException Si la baralla està buida o no inicialitzada.
     */
    fun barrejar() {
        baralla.shuffle(Random)
        pila = baralla
    }

    /**
     * Extreu la següent carta de la pila.
     *
     * Si la pila conté almenys una carta, es retorna i es retira la primera.
     * En cas contrari, es mostra un missatge informatiu i es retorna `null`.
     *
     * @return La següent carta de la pila, o nul si la pila està buida.
     * @throws NoSuchElementException Si es fa servir `removeFirst()` en una llista buida.
     */
    fun seguentCarta(): Carta? {
        if (pila.size >= 1) {
            val cartaRepartida = pila.first()
            pila.removeFirst()
            return cartaRepartida
        } else {
            println("No queden cartes a la pila")
            return null
        }
    }


    /**
     * Extreu una quantitat concreta de cartes de la pila i les retorna com a llista.
     * Si hi ha prou cartes a la pila, les reparteix i les elimina de la pila.
     * A més, afegeix les cartes indicades al munt.
     *
     * @param quantitat El nombre de cartes que es volen demanar de la pila.
     * @param retorn Llista de cartes que s'han d'afegir al munt.
     * @return Una llista amb les cartes extretes de la pila, o `null` si no n'hi ha prou.
     * @throws IndexOutOfBoundsException Si es produeix un accés incorrecte a la pila.
     */
    fun cartesDisponibles():List<Carta>{
        var answer=""
        for (i in pila){
            answer+=i.toString()+"\n"
        }
        return pila
    }

    /**
     * Extreu una quantitat concreta de cartes de la pila i les retorna com a llista.
     *
     * Si hi ha prou cartes a la pila, les reparteix i les elimina de la pila.
     * A més, afegeix les cartes indicades al munt.
     *
     * @param quantitat El nombre de cartes que es volen demanar de la pila.
     * @param retorn Llista de cartes que s'han d'afegir al munt.
     * @return Una llista amb les cartes extretes de la pila, o `null` si no n'hi ha prou.
     * @throws IndexOutOfBoundsException Si es produeix un accés incorrecte a la pila.
     */
    fun demanarCartes(quantitat: Int, retorn: List<Carta>): List<Carta>? {
        if (pila.size >= quantitat) {
            val cartesRepartides = pila.take(quantitat)
            repeat(quantitat) {
                pila.removeAt(0)
            }
            munt.addAll(retorn)
            return cartesRepartides
        } else {
            println("No queden suficients cartes a la pila")
            return null
        }
    }


    /**
     * Reparteix una quantitat concreta de cartes des de la pila.
     *
     * Si hi ha prou cartes disponibles, s’extreuen de la pila i es retornen com a llista.
     *
     * @param quantitat El nombre de cartes que s’han de repartir.
     * @return Una llista amb les cartes repartides, o `null` si no n’hi ha prou a la pila.
     * @throws IndexOutOfBoundsException Si es produeix un accés incorrecte a la pila.
     */
    fun repartirCartes(quantitat: Int): List<Carta>? {
        if (pila.size >= quantitat) {
            val cartesRepartides = pila.take(quantitat)
            repeat(quantitat) {
                pila.removeAt(0)
            }
            return cartesRepartides
        } else {
            println("No queden suficients cartes a la pila")
            return null
        }
    }


    /**
     * Retorna totes les cartes actualment presents al munt.
     *
     * @return Una llista de cartes que hi ha al munt.
     */
    fun veureMunt(): List<Carta> {
        return munt
    }


    /**
     * Retorna una representació en forma de taula de la baralla actual.
     *
     * Mostra les cartes ordenades per files (1 al 12) i columnes per pinta
     * (Ors, Copes, Espases, Basts), creant una visualització alineada
     * del contingut de la baralla.
     *
     * @return Una cadena de text que representa la baralla.
     * @throws IndexOutOfBoundsException Si la baralla no conté 48 cartes.
     */
    fun niceNtidy(): String {
        var resposta =
            "${baralla[0]}  ${baralla[12]} ${baralla[24]} ${baralla[36]}\n" +
                    "${baralla[1]}  ${baralla[13]} ${baralla[25]} ${baralla[37]}\n" +
                    "${baralla[2]}  ${baralla[14]} ${baralla[26]} ${baralla[38]}\n" +
                    "${baralla[3]}  ${baralla[15]} ${baralla[27]} ${baralla[39]}\n" +
                    "${baralla[4]}  ${baralla[16]} ${baralla[28]} ${baralla[40]}\n" +
                    "${baralla[5]}  ${baralla[17]} ${baralla[29]} ${baralla[41]}\n" +
                    "${baralla[6]}  ${baralla[18]} ${baralla[30]} ${baralla[42]}\n" +
                    "${baralla[7]}  ${baralla[19]} ${baralla[31]} ${baralla[43]}\n" +
                    "${baralla[8]}  ${baralla[20]} ${baralla[32]} ${baralla[44]}\n" +
                    "${baralla[9]}  ${baralla[21]} ${baralla[33]} ${baralla[45]}\n" +
                    "${baralla[10]}  ${baralla[22]} ${baralla[34]} ${baralla[46]}\n" +
                    "${baralla[11]}  ${baralla[23]} ${baralla[35]} ${baralla[47]}\n"
        return resposta
    }

    /**
     * Construeix una representació en forma de taula de la baralla.
     *
     * Mostra totes les cartes de la baralla en format llegible, disposades en files de 4 cartes cadascuna.
     * S’afegeixen espais extra per mantenir l’alineació si els valors són inferiors a 10.
     *
     * @return Un String amb el contingut de la baralla.
     * @throws IndexOutOfBoundsException Si `baralla` conté elements nuls o es modifica durant la lectura.
     */
    override fun toString(): String {
        var resposta = ""
        for (i in baralla.indices) {
            resposta += baralla[i].toString()
            if (baralla[i].getValor() < 10) {
                resposta += " "
            }

            if ((i + 1) % 4 == 0) {
                resposta += "\n"
            } else {
                resposta += "  |   "
            }
        }
        return resposta
    }

}