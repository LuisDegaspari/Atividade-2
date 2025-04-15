import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ListaDupla<Cidade> cidades = new ListaDupla<>();

        while (true) {
            String menu = JOptionPane.showInputDialog(
                "Menu:\n" +
                "1. Cadastrar cidade\n" +
                "2. Cadastrar ligação direta\n" +
                "3. Listar cidades e ligações\n" +
                "4. Verificar ligação direta e tempo estimado\n" +
                "5. Listar entregas com tempo máximo\n" +
                "0. Sair"
            );

            if (menu == null || menu.equals("0")) break;

            switch (menu) {
                case "1": {
                    String nomeCidade = JOptionPane.showInputDialog("Nome da cidade:");
                    if (nomeCidade != null && !nomeCidade.trim().isEmpty()) {
                        cidades.inserir(new Cidade(nomeCidade.trim()));
                    }
                    break;
                }

                case "2": {
                    String origemLig = JOptionPane.showInputDialog("Cidade de origem:");
                    String destinoLig = JOptionPane.showInputDialog("Cidade de destino:");
                    double distancia = Double.parseDouble(JOptionPane.showInputDialog("Distância (km):"));
                    double trafego = Double.parseDouble(JOptionPane.showInputDialog("Fator de tráfego (0 a 2):"));
                    int pedagios = Integer.parseInt(JOptionPane.showInputDialog("Número de pedágios:"));

                    No<Cidade> noOrigemLig = buscarCidade(cidades, origemLig);
                    if (noOrigemLig != null) {
                        noOrigemLig.getDado().getLigacoes().inserir(new Ligacao(destinoLig, distancia, trafego, pedagios));
                    } else {
                        JOptionPane.showMessageDialog(null, "Cidade de origem não encontrada.");
                    }
                    break;
                }

                case "3": {
                    StringBuilder sb = new StringBuilder();
                    No<Cidade> atual = cidades.getInicio();
                    while (atual != null) {
                        Cidade cidade = atual.getDado();
                        sb.append("Cidade: ").append(cidade.getNome()).append("\n");
                        No<Ligacao> lig = cidade.getLigacoes().getInicio();
                        while (lig != null) {
                            sb.append("  → ").append(lig.getDado()).append("\n");
                            lig = lig.getProximo();
                        }
                        atual = atual.getProximo();
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;
                }

                case "4": {
                    String origemVerif = JOptionPane.showInputDialog("Cidade de origem:");
                    String destinoVerif = JOptionPane.showInputDialog("Cidade de destino:");

                    No<Cidade> noOrigemVerif = buscarCidade(cidades, origemVerif);
                    if (noOrigemVerif != null) {
                        Ligacao l = buscarLigacao(noOrigemVerif.getDado().getLigacoes(), destinoVerif);
                        if (l != null) {
                            JOptionPane.showMessageDialog(null,
                                "Existe ligação direta.\nTempo estimado: " + l.calcularTempo() + " min");
                        } else {
                            JOptionPane.showMessageDialog(null, "Não existe ligação direta.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cidade de origem não encontrada.");
                    }
                    break;
                }

                case "5": {
                    double limite = Double.parseDouble(JOptionPane.showInputDialog("Tempo máximo (min):"));
                    StringBuilder resultado = new StringBuilder("Entregas possíveis:\n");

                    No<Cidade> atual = cidades.getInicio();
                    while (atual != null) {
                        Cidade cidade = atual.getDado();
                        No<Ligacao> lig = cidade.getLigacoes().getInicio();
                        while (lig != null) {
                            Ligacao l = lig.getDado();
                            if (l.calcularTempo() <= limite) {
                                resultado.append(cidade.getNome()).append(" → ")
                                    .append(l.getDestino()).append(" (")
                                    .append(String.format("%.2f", l.calcularTempo())).append(" min)\n");
                            }
                            lig = lig.getProximo();
                        }
                        atual = atual.getProximo();
                    }
                    JOptionPane.showMessageDialog(null, resultado.toString());
                    break;
                }

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida");
            }
        }
    }

    public static No<Cidade> buscarCidade(ListaDupla<Cidade> lista, String nome) {
        No<Cidade> aux = lista.getInicio();
        while (aux != null) {
            if (aux.getDado().getNome().equalsIgnoreCase(nome.trim())) {
                return aux;
            }
            aux = aux.getProximo();
        }
        return null;
    }

    public static Ligacao buscarLigacao(ListaDupla<Ligacao> lista, String destino) {
        No<Ligacao> aux = lista.getInicio();
        while (aux != null) {
            if (aux.getDado().getDestino().equalsIgnoreCase(destino.trim())) {
                return aux.getDado();
            }
            aux = aux.getProximo();
        }
        return null;
    }
}
