package br.com.alura.alugames.servicos

import com.google.gson.Gson
import org.example.br.com.alura.alugames.modelo.InfoJogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {
    fun buscaJogo(id:String): InfoJogo{
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"

        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client
            .send(request, BodyHandlers.ofString())

        val json = response.body()
//        println(json)

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

}