package br.com.alura.alugames.servicos

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.InfoGamerJson
import br.com.alura.alugames.utilitario.criaGamer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.example.br.com.alura.alugames.modelo.InfoJogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {

    fun consomeDados(endereco: String): String{

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client
            .send(request, BodyHandlers.ofString())

        return response.body()
    }

    fun buscaJogo(id:String): InfoJogo{
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val json = consomeDados(endereco)

        val gson = Gson()
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

//    val meuJogo = Jogo(
//        "Batman: Arkham Asylum Game of the Year Edition",
//        "https:\\/\\/shared.fastly.steamstatic.com\\/store_item_assets\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1702934705")
//
//    println(meuJogo.toString())
//
//    val novoJogo = Jogo(
//        capa = "https:\\/\\/shared.fastly.steamstatic.com\\/store_item_assets\\/steam\\/apps\\/35140\\/capsule_sm_120.jpg?t=1702934705",
//        titulo = "Batman: Arkham Asylum Game of the Year Edition"
//    )

        return meuInfoJogo
    }

    fun buscaGamers(): List<Gamer>{
        val endereco = "https://raw.githubusercontent.com/jeniblodev/arquivosJson/main/gamers.json"

        val json = consomeDados(endereco)

        val gson = Gson()
        val meuGamerTipo = object : TypeToken<List<InfoGamerJson>>() {}.type
        val listaGamer: List<InfoGamerJson> = gson.fromJson(json, meuGamerTipo)

        val listaGamerConvertida = listaGamer.map { infoGamerJson -> infoGamerJson.criaGamer() }
        return listaGamerConvertida
    }

}