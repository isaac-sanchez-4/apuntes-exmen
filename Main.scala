object Main extends App {
  
    val jugadores = List("Alex", "Chen", "Marta", "Sindhu", "Luis")
    val puntuaciones = Array(18, 24, 21, 20, 26)

    def bust(puntuacion: Int): Boolean = puntuacion > 21  

    def estadoMano(puntuacion: Int) = if(bust(puntuacion)) "BUST" else  "VALIDA"

    def mejorMano(handA: Int, handB: Int): Int = { 
        if (bust(handA) && bust(handB)) 0  
        else if (bust(handA)) handB
        else if (bust(handB)) handA
        else if (handA > handB) handA 
        else handB
    }
    
    var i = 0
    var manosValidas = 0
    var manosBust = 0
    var mejorPuntuacionRonda1 = 0
    
    println("Ronda 1")
    while(i < puntuaciones.length) {
        val jug = jugadores(i)
        val punt = puntuaciones(i) 
        val estado = estadoMano(punt)
        
        println(s"$jug -> $punt -> $estado")

        if (estado == "VALIDA") manosValidas += 1
        else manosBust += 1

        mejorPuntuacionRonda1 = mejorMano(mejorPuntuacionRonda1, punt)
        
        i += 1
    }

    println("Resumen")
    println(s"Jugadores: ${jugadores.length}")
    println(s"Manos válidas: $manosValidas")
    println(s"Bust: $manosBust")
    println(s"Mejor puntuación válida: $mejorPuntuacionRonda1")
    
    val puntuacionesRonda2 = Array(22, 19, 20, 21, 17)
    var j = 0
    var manosValidas2 = 0 
    var manosBust2 = 0
    var mejorPuntuacionRonda2 = 0
    
    println("\nRonda 2")
    while (j < puntuacionesRonda2.length){
        val jug = jugadores(j)
        val punt = puntuacionesRonda2(j)
        val estado = estadoMano(punt)    
        
        println(s"$jug -> $punt -> $estado")
        
        if (estado == "VALIDA") manosValidas2 += 1 else manosBust2 += 1 //
        
        mejorPuntuacionRonda2 = mejorMano(mejorPuntuacionRonda2, punt) 
        
        j += 1
    }
    
    println("Resumen")
    println(s"Jugadores: ${jugadores.length}")
    println(s"Manos válidas: $manosValidas2")
    println(s"Bust: $manosBust2")
    println(s"Mejor puntuación válida: $mejorPuntuacionRonda2")

    println("\nComparación de las dos rondas")
    println(s"Mejor puntuación Ronda 1: $mejorPuntuacionRonda1")
    println(s"Mejor puntuación Ronda 2: $mejorPuntuacionRonda2")

    if (mejorPuntuacionRonda1 > mejorPuntuacionRonda2) {
        println("La primera ronda tuvo la mejor puntuación.")
    } else if (mejorPuntuacionRonda2 > mejorPuntuacionRonda1) {
        println("La segunda ronda tuvo la mejor puntuación.")
    } else {
        println("Ambas rondas tuvieron la misma mejor puntuación.")
    }
    
    println("\nForeach")
    puntuaciones.foreach { puntos => 
        println(s"La puntuacion es de $puntos")
    }
}