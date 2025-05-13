enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario {
    var nome: String = ""
    var email: String = ""
    var conteudosInscritos: List<ConteudoEducacional> = listOf()
}

data class ConteudoEducacional(var nome: String, val duracao: Int = 60, var nivel: Nivel = Nivel.BASICO)

data class Formacao(val nome: String, var conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        if (usuario.conteudosInscritos.isEmpty()) {
            usuario.conteudosInscritos = conteudos
        } else {
            usuario.conteudosInscritos = usuario.conteudosInscritos + conteudos
        }
        inscritos.add(usuario)
    }
}

fun main() {
    // Criando conteúdos educacionais
    val kotlinBasico = ConteudoEducacional("Kotlin Básico", 120, Nivel.BASICO)
    val kotlinIntermediario = ConteudoEducacional("Kotlin Intermediário", 180, Nivel.INTERMEDIARIO)
    val kotlinAvancado = ConteudoEducacional("Kotlin Avançado", 240, Nivel.DIFICIL)

    // Criando uma formação
    val formacaoKotlin = Formacao("Formação Kotlin", listOf(kotlinBasico, kotlinIntermediario, kotlinAvancado))

    // Criando usuários
    val usuario1 = Usuario().apply {
        nome = "João Silva"
        email = "joao.silva@email.com"
    }

    val usuario2 = Usuario().apply {
        nome = "Maria Oliveira"
        email = "maria.oliveira@email.com"
    }

    // Matriculando usuários na formação
    formacaoKotlin.matricular(usuario1)
    formacaoKotlin.matricular(usuario2)

    // Exibindo informações
    println("Formação: ${formacaoKotlin.nome}")
    println("Conteúdos da formação:")
    formacaoKotlin.conteudos.forEach { println("- ${it.nome} (${it.nivel}) - ${it.duracao} minutos") }

    println("\nUsuários matriculados:")
    formacaoKotlin.inscritos.forEach { usuario ->
        println("- ${usuario.nome} (${usuario.email})")
        println("  Conteúdos inscritos:")
        usuario.conteudosInscritos.forEach { println("  - ${it.nome}") }
    }
}
