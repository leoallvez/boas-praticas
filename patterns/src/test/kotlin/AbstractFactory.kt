import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
//Produto Abstrato
interface Simpson
//Produtos Concretos
class HomerSimpson : Simpson
//
class MargeSimpson : Simpson
//Fábrica Abstrata
abstract class SimpsonFactory {

    abstract fun makeSimpson(): Simpson

    companion object {
        inline fun <reified T : Simpson> createFactory(): SimpsonFactory =
            when (T::class) {
                HomerSimpson::class -> HomerFactory()
                MargeSimpson::class -> MargeFactory()
                else -> throw IllegalArgumentException()
            }
    }
}
//Fábricas Concretas
class HomerFactory : SimpsonFactory() {
    override fun makeSimpson(): Simpson = HomerSimpson()
}

class MargeFactory : SimpsonFactory() {
    override fun makeSimpson(): Simpson = MargeSimpson()
}

class AbstractFactoryTest {

    @Test
    fun `Abstract Factory`() {
        val simpsonFactory = SimpsonFactory.createFactory<MargeSimpson>()
        val simpson = simpsonFactory.makeSimpson()
        println("Simpson instanciado: $simpson")

        assertThat(simpson).isInstanceOf(MargeSimpson::class.java)
    }
}
