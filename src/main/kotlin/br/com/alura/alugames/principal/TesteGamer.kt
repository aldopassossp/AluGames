import br.com.alura.alugames.modelo.Gamer

fun main(){
    val gamer1 = Gamer("Aldo", "aldopassos@gmail.com")
    println(gamer1)

    val gamer2 = Gamer("Raphael", "raphael@gmail.com", "19/02/1995", "raphael")
    println(gamer2)

    gamer1.let {
        it.dataNascimento = "18/09/2000"
        it.usuario = "aldopassos"

    }.also {
        println(gamer1.idInterno)
    }

    println(gamer1)
}