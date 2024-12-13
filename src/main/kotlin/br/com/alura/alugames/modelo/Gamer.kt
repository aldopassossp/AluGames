//package br.com.alura.alugames.modelo
//
//import kotlin.random.Random
//
//data class Gamer(var nome:String, var email:String){
//
//    var dataNascimento:String? = null
//    var usuario:String? = null
//        set(value) {
//            field = value
//            if(idInterno.isNullOrBlank())(
//                criarIdInterno()
//            )
//        }
//    var idInterno:String? = null
//        private set
//
//    constructor(nome: String, email: String, dataNascimento: String, usuario: String):
//            this(nome, email) {
//                this.dataNascimento = dataNascimento
//                this.usuario = usuario
//                criarIdInterno()
//            }
//
//    init {
//        validarEmail()
//    }
//
//    fun getIdInterno():String? {
//        return idInterno
//    }
//
//    override fun toString(): String {
//        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno"
//    }
//
//    fun criarIdInterno(){
//        val numero = Random.nextInt(10000)
//        val tag = String.format("%04d", numero)
//
//        this.idInterno = "$usuario#$tag"
//    }
//
//    fun validarEmail(): String {
//        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
//        if (regex.matches(email)){
//            return email
//        } else {
//            throw IllegalArgumentException("E-mail Inválido!")
//        }
//    }
//}
package br.com.alura.alugames.modelo

import org.example.br.com.alura.alugames.modelo.Jogo
import kotlin.random.Random
import java.util.Scanner

data class Gamer(var nome: String, var email: String) {

    var dataNascimento: String? = null
    var usuario: String? = null
        set(value) {
            field = value
            if (idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }
    var idInterno: String? = null
        private set
    val jogosBuscados = mutableListOf<Jogo?>()

    constructor(nome: String, email: String, dataNascimento: String, usuario: String) :
            this(nome, email) {
        this.dataNascimento = dataNascimento
        this.usuario = usuario
        criarIdInterno()
    }

    init {
        validarEmail()
    }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    private fun criarIdInterno() {
        val numero = Random.nextInt(10000)
        val tag = String.format("%04d", numero)
        this.idInterno = "$usuario#$tag"
    }

    private fun validarEmail() {
        val regex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
        if (!regex.matches(email)) {
            throw IllegalArgumentException("E-mail Inválido!")
        }
    }

    companion object{
        fun criarGamer(leitura: Scanner): Gamer{
            println("Boas vindas ao AluGames! Vamos fazer o seu cadastro. Digite seu nome:")
            val nome = leitura.nextLine()
            println("Digite seu e-mail:")
            val email = leitura.nextLine()
            println("Diseja completar seu cadastro com usuário e data de nascimento?")
            val opcao = leitura.nextLine()

            if(opcao.equals("s",true)){
                println("Digite sua data de nascimento (DD/MM/AAAA):")
                val nascimento = leitura.nextLine()
                println("Digite seu nome de usuário:")
                val usuario = leitura.nextLine()

                return Gamer(nome, email, nascimento, usuario)
            }else {
                return Gamer(nome, email)
            }
        }
    }
}
