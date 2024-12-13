package org.example.br.com.alura.alugames.principal

import br.com.alura.alugames.servicos.ConsumoApi
import org.example.br.com.alura.alugames.modelo.Jogo
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    println("Digite um código de jogo para buscar")
    val busca = leitura.nextLine()

    val buscaApi = ConsumoApi()
    val informacaoJogo = buscaApi.buscaJogo(busca)

//    try {
//        val meuJogo = Jogo(meuInfoJogo.info.title, meuInfoJogo.info.thumb)
//        println(meuJogo)
//    } catch( ex: NullPointerException ){
//        println("Jogo inexistente! Tente outro ID!")
//    }

    var meuJogo: Jogo? = null

    val resultado = runCatching {
        meuJogo = Jogo(informacaoJogo.info.title, informacaoJogo.info.thumb)
    //    println(meuJogo)
    }
    resultado.onFailure {
        println("Jogo inexistente! Tente outro ID!")
    }

    resultado.onSuccess {
        println("Deseja inserir uam descrição personalizada? S/N")
        val opcao = leitura.nextLine()
        if(opcao.equals("s",true)){
            println("Insira a descrição pesonalizada para o jogo")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        }else {
            meuJogo?.descricao = meuJogo?.titulo!!
        }
        println(meuJogo)
    }

}