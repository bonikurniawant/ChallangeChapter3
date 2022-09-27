package id.co.boni.challangechapter3.controller

class Controller(private val callback: Callback) {
    fun banding(player: String, com: String) {

        val batu = "Batu"
        val kertas = "Kertas"
        val gunting = "Gunting"

        val pemainMenang = player == gunting && com == kertas
                || player == kertas && com == batu
                || player == batu && com == gunting
        val comMenang = com == gunting && player == kertas
                || com == kertas && player == batu
                || com == batu && player == gunting
        val draw = player == com

        if (pemainMenang) {
            callback.tampilkanHasil("Pemain 1 MENANG!")

        } else if (comMenang) {
            callback.tampilkanHasil("Computer MENANG!")

        } else if (draw) {
            callback.tampilkanHasil("DRAW")

        }

    }

}