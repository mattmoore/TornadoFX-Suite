package com.github.ast.parser

import com.google.gson.Gson
import com.google.gson.JsonObject
import kastree.ast.psi.Parser
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.util.ArrayList

class KParserImplTest {
    @get: Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    /**
     * For recursive parsing
     */
    val gson = Gson()

    private fun setup(kotlinFile: String): JsonObject {
        val file = Parser.parseFile(kotlinFile, true)
        return gson.toJsonTree(file).asJsonObject
    }

    @Test
    fun `breakdown simple variable assignment into a decl`() {
        val node = setup(variable())

        breakdownStmts
        println(node)
    }

    private fun sourceCode() : String {
        return """
            package foo

            fun bar() : String {
                // Print hello
                println("Hello, World!")
            }

            fun baz() : String = println("Hello, again!")

            class Person(firstName: String, lastName: String) {
                var firstName : String = firstName
                var lastName : String = lastName

                fun fullName() : String {
                    return ""
                }

                fun sampleFunc(sampleArg: String, sampleArg2: Int) : Int {
                    return sampleArgs2
                }
            }

             var p = Person()
        """.trimIndent()
    }

    private fun tfxFunction(): String {
        return """
            fun editCatSchedule(catSchedule: CatSchedule) {
                val catScheduleScope = CatScheduleScope()
                catScheduleScope.model.item = catSchedule
                find(Editor::class, scope = catScheduleScope).openModal()
            }
        """.trimIndent()
    }

    private fun function() : String {
        return """
            package foo

            fun bar() : String {
                // Print hello
                println("Hello, World!")
            }
        """.trimIndent()
    }

    private fun variable() : String {
        return """
            package foo

            var p = Person()
        """.trimIndent()
    }

}