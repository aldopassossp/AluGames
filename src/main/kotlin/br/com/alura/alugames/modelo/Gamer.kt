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

import com.sun.org.apache.xerces.internal.impl.dv.xs.BaseDVFactory
import org.example.br.com.alura.alugames.modelo.Jogo
import kotlin.random.Random
import java.util.Scanner

data class Gamer(var nome: String, var email: String): Recomendavel {

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
    var plano: Plano = PlanoAvulso("BRONZE")
    val jogosBuscados = mutableListOf<Jogo?>()
    val jogosAlugados = mutableListOf<Aluguel>()
    private val listaNotas = mutableListOf<Int>()

    override val media: Double
        get() = listaNotas.average()

    override fun recomendar(nota: Int) {
        listaNotas.add(nota)
    }

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
        return "Gamer \n" +
                "Nome= $nome \n" +
                "E-mail= $email \n" +
                "Data de Nascimento= $dataNascimento \n" +
                "Usuario= $usuario \n" +
                "IdInterno= $idInterno \n" +
                "Reputação = $media"
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

    fun alugaJogo(jogo: Jogo, periodo: Periodo) : Aluguel {
        val aluguel = Aluguel(this, jogo, periodo)
        jogosAlugados.add(aluguel)
        return aluguel
    }

    fun jogosDoMes(mes:Int): List<Jogo> {
        return jogosAlugados.filter { aluguel -> aluguel.periodo.dataInicial.monthValue == mes }
            .map{aluguel -> aluguel.jogo}
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
