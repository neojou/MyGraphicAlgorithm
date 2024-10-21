package com.neojou

import kotlin.system.measureNanoTime

import java.util.Stack
import java.util.EmptyStackException

class Solution {
    class Vertex(name: String) {
        val name = name
        val toNext = mutableListOf<Vertex>()

        fun insertToNext(v : Vertex) {
            var pos = 0
            while (pos < toNext.size) {
                val tnName = toNext.get(pos)!!.name
                if (v.name.compareTo(tnName) < 0)
                    break;
                pos++
            }
            toNext.add(pos, v)
        }
    }

    class Graph(start: Vertex) {
        val start = start
        val vh = HashMap<String, Vertex>()
        var edge_nums = 0

        init {
            vh.put(start.name, start)
        }

        fun addEdge(from: String, to: String) {
            val fv : Vertex
            if (vh.containsKey(from)) {
                fv = vh.get(from)!!
            } else {
                fv = Vertex(from)
                vh.put(from, fv)
            }

            val tv : Vertex
            if (vh.containsKey(to)) {
                tv = vh.get(to)!!
            } else {
                tv = Vertex(to)
                vh.put(to, tv)
            }
            fv.insertToNext(tv)
            edge_nums++
        }

        fun show () {
            showv(start, 0)
        }

        fun showv(v : Vertex, d : Int) {
            for(i in 0 until d)
                print("   ")
            print(v.name)

            if (v.toNext.size > 0)
                println(" -> ")

            for (pos in 0 until v.toNext.size) {
                val vi = v.toNext.get(pos)
                v.toNext.removeAt(pos)
                showv(vi, d + 1)
                v.toNext.add(pos, vi)
            }
            println()
        }

        fun findEulerTrail(): List<String> {
            val trail = mutableListOf<String>()
            findEulerTrail_dfs(start, 0, trail)
            return trail
        }

        fun findEulerTrail_dfs(v : Vertex, d : Int, trail : MutableList<String>) : Boolean {
            trail.add(v.name)
            if (d == edge_nums) {
                if (v.toNext.size > 0) {
                    println("Strange, toNext size : " + v.toNext.size)
                }
                return true
            }
            for (pos in 0 until v.toNext.size) {
                val vi = v.toNext.get(pos)
                v.toNext.removeAt(pos)
                val isFound = findEulerTrail_dfs(vi, d + 1, trail)
                v.toNext.add(pos, vi)
                if (isFound) return true
            }
            trail.removeLast()
            return false
        }
    }


    fun findItinerary(tickets: List<List<String>>): List<String> {
        val jfk = Vertex("JFK")
        val g = Graph(jfk)

        tickets.forEach {
            g.addEdge(it.get(0), it.get(1))
        }

        //g.show()
        val res = g.findEulerTrail()
        return res
    }
}

fun main() {
    val sol = Solution()

    val tickets1 = listOf(listOf<String>("MUC", "LHR"),
                    listOf<String>("JFK", "MUC"),
                    listOf<String>("SFO", "SJC"),
                    listOf<String>("LHR", "SFO"))

    val tickets2 = listOf(listOf<String>("JFK", "SFO"),
        listOf<String>("JFK", "ATL"),
        listOf<String>("SFO", "ATL"),
        listOf<String>("ATL", "JFK"),
        listOf<String>("ATL", "SFO"))

    var res = sol.findItinerary(tickets1)
    println(res)
    res = sol.findItinerary(tickets2)
    println(res)
}
