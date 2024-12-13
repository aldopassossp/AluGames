package org.example.br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.servicos.ConsumoApi
import org.example.br.com.alura.alugames.modelo.Jogo
import transformarEmIdade
import java.util.Scanner


fun main() {

    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)

    println("Cadastro concluído com sucesso. Dados do gamer: ")
    println(gamer)

    println("Idade do gamer: " + gamer.dataNascimento?.transformarEmIdade())

    do{
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
            gamer.jogosBuscados.add(meuJogo)
        }
        println("Deseja buscar um novo jogo? S/N")
        val resposta = leitura.nextLine()

    }while(resposta.equals("s", true))

    println("Jogos Buscados:")
    println(gamer.jogosBuscados)

    println("Jogos ordenados por título:")
    gamer.jogosBuscados.sortBy { it?.titulo }

    gamer.jogosBuscados.forEach(){
        println("Titulo: " + it?.titulo )
    }

    val jogosFiltrados = gamer.jogosBuscados.filter{
        it?.titulo?.contains("batman", true) ?: false
    }

    println("\n Jogos filtrados:")
    println(jogosFiltrados)

    println("\n Deseja excluir algum jogo da lista original? S/N")
    val opcao = leitura.nextLine()
    if(opcao.equals("s",true)){
        println(gamer.jogosBuscados)
        println("\n Informe a posição do jogo que deseja excluir:")
        val posicao = leitura.nextInt()
        gamer.jogosBuscados.removeAt(posicao)
    }

    println("\n Lista atualizada:")
    println(gamer.jogosBuscados)

    println("\n Busca finalizada com sucesso!")


}