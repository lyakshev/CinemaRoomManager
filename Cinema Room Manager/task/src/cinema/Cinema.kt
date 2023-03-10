package cinema

import java.util.Scanner

val cinemaList: MutableList<MutableList<String>> = mutableListOf()
var totalIncome = 0
var currentIncome = 0
val scanner = Scanner(System.`in`)
var rows = 0
var seats = 0
var purchasedTickets = 0
var totalSeats = 0


fun main() {
    var exit = false

    initCinema()

    while (exit != true) {
        printMenu()
        when (scanner.nextInt()) {
            1 -> printCinema()
            2 -> buyTicket()
            3 -> statistics()
            0 -> exit = true

        }
    }
}

fun buyTicket() {
    var ticket = 0

    while (ticket != 1) {
        print("\nEnter a row number:\n> ")
        val selectedRow = scanner.nextInt()
        print("Enter a seat number in that row:\n> ")
        val selectedSeat = scanner.nextInt()

        if (selectedRow > rows || selectedSeat > seats) {
            println("\nWrong input!")
        } else {
            if (cinemaList[selectedRow-1][selectedSeat-1] == "B") {
                println("\nThat ticket has already been purchased!")
            } else {cinemaList[selectedRow-1][selectedSeat-1] = "B"
                val tPrice = ticketPrice(selectedRow)
                println("\nTicket price: $$tPrice")
                ticket = 1
                purchasedTickets++
                currentIncome += tPrice
            }
        }
    }
}


fun printMenu() {
    println("\n1. Show the seats")
    println("2. Buy a ticket")
    println("3. Statistics")
    print("0. Exit\n> ")
}


fun statistics() {
    val percent :Double = 100 * purchasedTickets.toDouble() / totalSeats.toDouble()
    val percentOutput = String.format("%.2f", percent)

    println("\nNumber of purchased tickets: $purchasedTickets")
    println("Percentage: $percentOutput%")
    println("Current income: $$currentIncome")
    println("Total income: $$totalIncome")
}


fun ticketPrice(sr: Int) :Int {

    if (rows * seats <= 60) {
        return 10
    } else {
        if (sr <= rows/2) {
            return 10
        } else {
            return 8
        }
    }
}


fun initCinema() {
    print("Enter the number of rows:\n> ")
    rows = scanner.nextInt()
    print("Enter the number of seats in each row:\n> ")
    seats = scanner.nextInt()
    for (i in 0 until rows) {
        val line= MutableList(seats) { "S" }
        cinemaList.add(line)
    }
    totalSeats = rows * seats

    if (totalSeats <= 60) {
        totalIncome = 10 * rows * seats
    } else {
       totalIncome = (10 * (rows/2) * seats) + (8 * (rows - rows/2) * seats)
    }
}


fun printCinema() {

    println("\nCinema:")
    print(" ")
    for (i in 1 .. seats){
        print(" $i")
    }
    print("\n")
    for (i in 0 until rows) {
        println("${i+1} " + cinemaList[i].joinToString(" "))
    }
}
