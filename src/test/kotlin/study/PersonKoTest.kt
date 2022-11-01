package study

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.shouldBe

class PersonKoTest : StringSpec({
    "이름 붙인 인자" {
        val person = Person(name = "홍종표", age = 26, nickname = "jpoh281")
        person.name shouldBe "홍종표"
        person.age shouldBe 26
        person.nickname shouldBe "jpoh281"
    }

    "널 타입" {
        val person = Person(name = "홍종표", age = 26, nickname = null)
        person.nickname.shouldBeNull()
    }

    "기본 인자" {
        val person = Person(name = "홍종표", age = 26)
        person.nickname shouldBe person.name
    }

    "데이터 클래스" {
        val person1 = Person(name = "홍종표", age = 26)
        val person2 = Person(name = "홍종표", age = 26)

        person1 shouldBe person2
    }
})
