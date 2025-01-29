import java.util.Scanner;

public class JogoAventura {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Fecha o scanner corretamente quando o programa for encerrado
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                scanner.close();
            } catch (Exception e) {
                // Ignora erros ao fechar
            }
        }));

        while (true) {
            if (!iniciarJogo()) {
                break; // Encerra o jogo se o jogador não quiser jogar novamente
            }
        }
        System.out.println("Obrigado por jogar!");
    }

    private static boolean iniciarJogo() {
        int caminho = escolherCaminho();
        if (!labirintoEspelhos()) return true; // Retorna ao início se explodir um espelho
        if (!batalhaFeiticeiro(caminho)) return true; // Retorna ao início se perder a luta

        System.out.println("Você avançou para a batalha final!");
        System.out.println("Fim do jogo.");

        return perguntarJogarNovamente();
    }

    private static int escolherCaminho() {
        System.out.println("Escolha o caminho: 1 – Rápido e seguro ou 2 – Rápido com danos mágicos");
        int escolha = lerNumero(1, 2);
        if (escolha == 1) {
            System.out.println("Você chegou sem danos, mas perdeu tempo.");
        } else {
            System.out.println("Você chegou rápido, mas sofreu danos mágicos.");
        }
        return escolha;
    }

    private static boolean labirintoEspelhos() {
        System.out.println("Você entrou no labirinto de espelhos.");
        while (true) {
            System.out.println("Escolha um espelho para atacar: 1, 2 ou 3");
            int escolha = lerNumero(1, 3);

            if (escolha == 1 || escolha == 3) {
                System.out.println("Espelho explode! Voltando ao início.");
                return false; // Indica que o jogo deve reiniciar
            } else {
                System.out.println("Você revelou a saída do labirinto!");
                return true;
            }
        }
    }

    private static boolean batalhaFeiticeiro(int caminho) {
        System.out.println("O feiticeiro apareceu e se preparou para atacar!");
        System.out.println("Escolha quem enfrentará o chefe: 1 – Superman ou 2 – Flash");
        int escolhaHeroi = lerNumero(1, 2);

        if (escolhaHeroi == 1) { // Superman
            if (caminho == 2) {
                System.out.println("Superman não aguenta os danos mágicos e perde. Voltando ao início.");
                return false;
            } else {
                System.out.println("Superman não é rápido o suficiente e perde. Voltando ao início.");
                return false;
            }
        } else { // Flash
            if (caminho == 1) {
                System.out.println("Flash é rápido o suficiente para vencer o feiticeiro!");
            } else {
                System.out.println("Flash usa sua velocidade para esquivar e vence o feiticeiro!");
            }
        }
        return true;
    }

    private static boolean perguntarJogarNovamente() {
        System.out.println("Deseja jogar novamente? (1 - Sim, 2 - Não)");
        int resposta = lerNumero(1, 2);
        return resposta == 1;
    }

    private static int lerNumero(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int numero = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer do Scanner
                if (numero >= min && numero <= max) {
                    return numero;
                }
            } else {
                scanner.next(); // Limpa entrada inválida
            }
            System.out.println("Entrada inválida! Escolha um número entre " + min + " e " + max + ".");
        }
    }
}
