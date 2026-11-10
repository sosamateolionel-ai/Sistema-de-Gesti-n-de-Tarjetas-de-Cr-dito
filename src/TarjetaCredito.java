public class TarjetaCredito {
    private static final double COMISION_INTERNACIONAL = 0.05; // 5% para tarjeta estándar

    protected String numero;
    protected String titular;
    protected double limiteCredito;
    protected double saldoUtilizado;

    public TarjetaCredito(String numero, String titular, double limiteCredito) {
        this.numero = numero;
        this.titular = titular;
        this.limiteCredito = limiteCredito;
        this.saldoUtilizado = 0;
    }

    public String getNumero() { return numero; }
    public String getTitular() { return titular; }
    public double getLimiteCredito() { return limiteCredito; }
    public double getSaldoUtilizado() { return saldoUtilizado; }
    public double getDisponible() { return limiteCredito - saldoUtilizado; }

    public String getCategoria() { return "ESTÁNDAR"; }

    // Las subclases pueden ofrecer una comisión preferencial
    protected double getTasaComisionInternacional() {
        return COMISION_INTERNACIONAL;
    }

    protected double calcularComision(double monto, boolean internacional) {
        return internacional ? monto * getTasaComisionInternacional() : 0;
    }

    // Regla de validación: el total (con comisión) no puede superar el límite
    public boolean validarCompra(double total) {
        return saldoUtilizado + total <= limiteCredito;
    }

    // Gancho para beneficios (millas, seguros). La tarjeta estándar no tiene.
    protected void acreditarBeneficios(double monto) { }

    public boolean comprar(double monto, boolean internacional) {
        if (monto <= 0) {
            System.out.println("[RECHAZADA] " + numero + ": el monto debe ser positivo.");
            return false;
        }
        double comision = calcularComision(monto, internacional);
        double total = monto + comision;

        if (!validarCompra(total)) {
            System.out.println("[RECHAZADA] " + numero + ": compra de $" + fmt(monto) +
                    " (total $" + fmt(total) + ") supera el límite permitido.");
            return false;
        }

        saldoUtilizado += total;
        System.out.println("[APROBADA] " + numero + ": compra de $" + fmt(monto) +
                (internacional ? " (internacional, comisión $" + fmt(comision) + ")" : "") +
                " | Total: $" + fmt(total));
        acreditarBeneficios(monto);
        return true;
    }

    public void mostrarEstadoCuenta() {
        System.out.println("--- ESTADO DE CUENTA [" + getCategoria() + "] ---");
        System.out.println("Tarjeta: " + numero + " | Titular: " + titular);
        System.out.println("Límite de crédito: $" + fmt(limiteCredito));
        System.out.println("Consumos acumulados: $" + fmt(saldoUtilizado));
        System.out.println("Disponible: $" + fmt(getDisponible()));
    }

    protected String fmt(double valor) {
        return String.format("%.2f", valor);
    }
}