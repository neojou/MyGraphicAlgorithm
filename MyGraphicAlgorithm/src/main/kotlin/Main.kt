package com.neojou

import kotlin.system.measureNanoTime

import java.util.EmptyStackException
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack

class Solution {

    class Vertex(name: String) {
        val name = name
    }

    class Graph(start: Vertex) {
        val start = start
        val vhm = HashMap<String, Vertex>()
        val ghm = HashMap<Vertex, PriorityQueue<Vertex>>()

        init {
            vhm.put(start.name, start)
        }

        fun addEdge(from: String, to: String) {
            var fv = vhm[from]
            if (fv == null) {
                fv = Vertex(from)
                vhm[from] = fv
            }

            var tv = vhm[to]
            if (tv == null) {
                tv = Vertex(to)
                vhm[to] = tv
            }

            var pq = ghm[fv]
            if (pq == null) {
                pq = PriorityQueue<Vertex>{ v1, v2 ->
                    v1.name.compareTo(v2.name)
                }
            }
            pq.add(tv)
            ghm[fv] = pq
        }

        fun findEulerTrail(): List<String> {
            val trail = LinkedList<String>()
            findEulerTrail_dfs(start, trail)
            return trail
        }

        fun findEulerTrail_dfs(sv : Vertex, trail : LinkedList<String>) {
            val pq = ghm[sv]
            while (!pq.isNullOrEmpty()) {
                val nv = pq.remove()
                findEulerTrail_dfs(nv, trail)
            }
            trail.addFirst(sv.name)
        }
    }

    fun findItinerary(tickets: List<List<String>>): List<String> {
        val jfk = Vertex("JFK")
        val g = Graph(jfk)

        tickets.forEach {
            g.addEdge(it.get(0), it.get(1))
        }

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

    val tickets3 = listOf(
        listOf<String>("JFK", "SFO"),
        listOf<String>("JFK", "ATL"),
        listOf<String>("SFO", "JFK"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"),
        listOf<String>("ATL", "AAA"),
        listOf<String>("AAA", "BBB"),
        listOf<String>("BBB", "ATL"))

    var res = sol.findItinerary(tickets1)
    println(res)
    res = sol.findItinerary(tickets2)
    println(res)
    res = sol.findItinerary(tickets3)
    println(res)
}
