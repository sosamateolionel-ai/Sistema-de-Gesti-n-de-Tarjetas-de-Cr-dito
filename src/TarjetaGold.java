public class TarjetaGold extends TarjetaCredito {
    private static final double TOLERANCIA_LIMITE = 0.10;     // puede exceder el límite hasta un 10%
    private static final double COMISION_INTERNACIONAL_GOLD = 0.03; // 3%

    private int millasPorCadaMil;  // millas por cada $1000 consumidos
    private int millasAcumuladas;

    public TarjetaGold(String numero, String titular, double limiteCredito, int millasPorCadaMil) {
        super(numero, titular, limiteCredito);
        this.millasPorCadaMil = millasPorCadaMil;
        this.millasAcumuladas = 0;
    }

    public int getMillasAcumuladas() { return millasAcumuladas; }

    @Override
    public String getCategoria() { return "GOLD"; }

    @Override
    protected double getTasaComisionInternacional() {
        return COMISION_INTERNACIONAL_GOLD;
    }

    @Override
    public boolean validarCompra(double total) {
        return saldoUtilizado + total <= limiteCredito * (1 + TOLERANCIA_LIMITE);
    }

    @Override
    protected void acreditarBeneficios(double monto) {
        int millas = (int) (monto / 1000) * millasPorCadaMil;
        millasAcumuladas += millas;
        System.out.println("   + " + millas + " millas acumuladas.");
    }

    @Override
    public void mostrarEstadoCuenta() {
        super.mostrarEstadoCuenta();
        System.out.println("Millas acumuladas: " + millasAcumuladas);
    }
}