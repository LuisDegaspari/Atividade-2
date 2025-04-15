public class Ligacao {
    private String destino;
    private double distancia;
    private double trafego;
    private int pedagios;

    public Ligacao(String destino, double distancia, double trafego, int pedagios) {
        this.destino = destino;
        this.distancia = distancia;
        this.trafego = trafego;
        this.pedagios = pedagios;
    }

    public String getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getTrafego() {
        return trafego;
    }

    public int getPedagios() {
        return pedagios;
    }

    public double calcularTempo() {
        return (distancia * trafego) + (pedagios * 2);
    }

    @Override
    public String toString() {
        return destino +
               " | Distância: " + distancia +
               " | Tráfego: " + trafego +
               " | Pedágios: " + pedagios +
               " | Tempo: " + String.format("%.2f", calcularTempo()) + " min";
    }
}
