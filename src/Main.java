//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    TarjetaCredito estandar = new TarjetaCredito("4000-1111", "Laura Gómez", 200000);
    TarjetaGold gold = new TarjetaGold("5000-2222", "Carlos Pérez", 500000, 1);
    TarjetaBlack black = new TarjetaBlack("6000-3333", "Marta Díaz", 1000000, 3, true);

    System.out.println("=== OPERACIONES TARJETA ESTÁNDAR ===");
    estandar.comprar(150000, false);
    estandar.comprar(40000, true);
    estandar.comprar(20000, false);   // supera el límite

    System.out.println("\n=== OPERACIONES TARJETA GOLD ===");
    gold.comprar(300000, false);
    gold.comprar(200000, true);       // usa el margen de tolerancia
    gold.comprar(50000, false);       // supera incluso la tolerancia

    System.out.println("\n=== OPERACIONES TARJETA BLACK ===");
    black.comprar(800000, true);
    black.comprar(350000, true);      // usa el margen de tolerancia
    black.comprar(100000, false);     // supera incluso la tolerancia

    System.out.println("\n=== ESTADOS DE CUENTA ===\n");
    TarjetaCredito[] tarjetas = { estandar, gold, black };
    for (TarjetaCredito t : tarjetas) {
        t.mostrarEstadoCuenta();
        System.out.println();
    }

}
