package com.neojou

class FindSmallestSetOfVerticles {
        fun findSmallestSetOfVerticles(n: Int, edges: List<List<Int>>): List<Int> {
            val revEdgeMap = revEdgeMaps(edges)
            showHashMap(revEdgeMap)
            val res = mutableListOf<Int>()
            for (i in 0 until n) {
                if (i in revEdgeMap.keys) continue
                res.add(i)
            }
            return res
        }

        fun revEdgeMaps(edges: List<List<Int>>) : HashMap<Int, MutableList<Int>> {
            val revEdgeMap = HashMap<Int, MutableList<Int>>()
            edges.forEach {
                val u = it.get(0)
                val v = it.get(1)
                val l : MutableList<Int> = revEdgeMap.get(v)?:mutableListOf()
                l.add(u)
                revEdgeMap.put(v, l)
            }
            return revEdgeMap
        }

        fun showList(l: List<Int>) {
            print("[")
            l.forEach {
                print(it)
                print(", ")
            }
            println("] ")
        }
        fun showHashMap(hm: HashMap<Int, MutableList<Int>>) {
            hm.forEach {
                print(it.key)
                print(" : ")
                showList(it.value)
            }
        }
}

/*
fun main() {
    val sol = FindSmallestSetOfVerticles()
    val n = 6
    val edges = listOf(listOf(0, 1), listOf(0, 2), listOf(2, 5), listOf(3, 4), listOf(4, 2))

    val output = sol.findSmallestSetOfVerticles(n, edges)
    output.forEach {
        System.out.print(it)
        System.out.print(", ")
    }
}
 */