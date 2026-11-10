public class TarjetaBlack extends TarjetaCredito {
    private static final double TOLERANCIA_LIMITE = 0.20; // puede exceder el límite hasta un 20%

    private int millasPorCadaMil;
    private int millasAcumuladas;
    private boolean seguroViaje;

    public TarjetaBlack(String numero, String titular, double limiteCredito,
                        int millasPorCadaMil, boolean seguroViaje) {
        super(numero, titular, limiteCredito);
        this.millasPorCadaMil = millasPorCadaMil;
        this.seguroViaje = seguroViaje;
        this.millasAcumuladas = 0;
    }

    public int getMillasAcumuladas() { return millasAcumuladas; }
    public boolean tieneSeguroViaje() { return seguroViaje; }

    @Override
    public String getCategoria() { return "BLACK"; }

    // Comisión preferencial: sin cargo por compras internacionales
    @Override
    protected double getTasaComisionInternacional() {
        return 0.0;
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
        System.out.println("Seguro de viaje: " + (seguroViaje ? "Incluido" : "No incluido"));
    }
}