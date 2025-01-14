package org.example.br.com.alura.alugames.modelo

data class Jogo(val titulo:String, val capa:String) {

    var descricao:String? = null
    var preco = 0.0

    override fun toString(): String {
        return "Meu Jogo: \n" +
                "Titulo: $titulo \n"+
                "Capa: $capa \n"+
                "Preço: $preco \n"+
                "Descricao: $descricao"
    }


}