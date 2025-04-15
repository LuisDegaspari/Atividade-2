public class Cidade {
    private String nome;
    private ListaDupla<Ligacao> ligacoes;

    public Cidade(String nome) {
        this.nome = nome;
        this.ligacoes = new ListaDupla<>();
    }

    public String getNome() {
        return nome;
    }

    public ListaDupla<Ligacao> getLigacoes() {
        return ligacoes;
    }

    @Override
    public String toString() {
        return nome;
    }
}
