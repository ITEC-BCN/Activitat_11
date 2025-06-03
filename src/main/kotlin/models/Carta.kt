package models

class Carta(private var pal:String, private var valor:Int) {
    private var espais=""
    fun getValor():Int{
        return valor
    }
    fun getPal():String{
        return pal
    }
    override fun toString(): String {
        repeat(9-pal.length){
            espais+=" "
        }
        return "Pal:${pal+espais}Valor:$valor"
    }
}