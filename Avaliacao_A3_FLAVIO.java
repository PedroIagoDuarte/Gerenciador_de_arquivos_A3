// Define o pacote onde esta classe está localizada
package avaliacao;

// Importa a classe File para manipular arquivos e diretórios
import java.io.File;
// Importa a classe Paths para criar caminhos de diretórios de forma segura e independente do sistema operacional
import java.nio.file.Paths;
// Importa a classe Scanner para leitura de dados digitados pelo usuário
import java.util.Scanner;
// Importa a classe Desktop, usada para abrir arquivos com o programa padrão do sistema
import java.awt.Desktop;

// Declara a classe principal do programa
public class Avaliacao_A3_FLAVIO {

    // Método principal - ponto de entrada do programa Java
    public static void main(String[] args) {

        // Cria um objeto Scanner para ler entradas do teclado (usuário)
        Scanner scanner = new Scanner(System.in);

        // Imprime o título do trabalho e os nomes dos autores
        System.out.println("TRABALHO A3 OCSO\n\nFEITO POR:\nPedro Iago\nVinicius Aragão\nRyhan\nVinicius\n");

        // Obtém o nome do usuário atualmente logado no sistema operacional
        String usuario = System.getProperty("user.name");

        // Mostra o nome do usuário no console
        System.out.println("Usuário logado: " + usuario);

        // Obtém o caminho da pasta pessoal do usuário (ex: C:\Users\pepog)
        String home = System.getProperty("user.home");

        // Define o nome da pasta que será acessada (Downloads)
        String nomePasta = "Downloads";

        // Constrói o caminho completo da pasta Downloads (ex: C:\Users\pepog\Downloads)
        String caminhoPasta = Paths.get(home, nomePasta).toString();

        // Início do loop principal do menu, repete até o usuário escolher sair
        while (true) {

            // Exibe o menu com as opções
            System.out.println("\n[1] Abrir pasta Downloads");
            System.out.println("[2] Sair");

            // Solicita que o usuário escolha uma opção
            System.out.print("Escolha uma opção: ");
            int escolha = -1; // Inicializa a variável da escolha

            try {
                // Lê a entrada do usuário e tenta convertê-la para número inteiro
                escolha = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Se a conversão falhar (usuário digitou letra, por exemplo), mostra erro
                System.out.println("Entrada inválida. Tente novamente.");
                continue; // Reinicia o loop
            }

            // Se o usuário escolheu a opção 2, o programa encerra
            if (escolha == 2) {
                System.out.println("Encerrando o programa. Até logo!");
                break; // Sai do loop
            }

            // Se a opção digitada for diferente de 1, é inválida
            if (escolha != 1) {
                System.out.println("Opção inválida.");
                continue; // Volta para o menu
            }

            // Cria um objeto File representando a pasta Downloads
            File pastaDownloads = new File(caminhoPasta);

            // Verifica se a pasta realmente existe e se é um diretório
            if (!pastaDownloads.exists() || !pastaDownloads.isDirectory()) {
                System.out.println("Pasta Downloads não encontrada.");
                continue; // Volta para o menu
            }

            // Obtém todos os arquivos e subpastas contidos na pasta Downloads
            File[] arquivos = pastaDownloads.listFiles();

            // Verifica se a pasta está vazia ou houve erro ao acessar os arquivos
            if (arquivos == null || arquivos.length == 0) {
                System.out.println("A pasta está vazia.");
                continue; // Volta ao menu
            }

            // Inicializa o índice usado para numerar os arquivos listados
            int index = 1;

            // Exibe a lista de arquivos encontrados na pasta Downloads
            System.out.println("\nArquivos encontrados na pasta Downloads:");
            for (File f : arquivos) {
                // Verifica se é um arquivo (não uma pasta)
                if (f.isFile()) {
                    // Mostra o índice e o nome do arquivo
                    System.out.printf("[%d] %s%n", index++, f.getName());
                }
            }

            // Se nenhum arquivo foi listado (só havia pastas), mostra mensagem e volta ao menu
            if (index == 1) {
                System.out.println("Não há arquivos nesta pasta.");
                continue;
            }

            // Solicita ao usuário que escolha um arquivo digitando o número correspondente
            System.out.print("Digite o número do arquivo que deseja abrir: ");
            int escolhaArquivo = -1; // Inicializa a variável

            try {
                // Lê a entrada do usuário e converte para número inteiro
                escolhaArquivo = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Caso a entrada não seja um número válido
                System.out.println("Número inválido.");
                continue;
            }

            // Reinicia o índice e prepara para buscar o arquivo escolhido
            index = 1;
            File arquivoEscolhido = null;

            // Percorre novamente a lista de arquivos para encontrar o que o usuário escolheu
            for (File f : arquivos) {
                if (f.isFile()) {
                    if (index == escolhaArquivo) {
                        // Se o número bater com o índice, salva o arquivo
                        arquivoEscolhido = f;
                        break;
                    }
                    index++;
                }
            }

            // Se não encontrou nenhum arquivo com o número fornecido, mostra erro
            if (arquivoEscolhido == null) {
                System.out.println("Arquivo inválido.");
                continue;
            }

            // Mostra o caminho completo do arquivo que será aberto
            System.out.println("Abrindo arquivo: " + arquivoEscolhido.getAbsolutePath());

            // Verifica se o sistema suporta a funcionalidade de abrir arquivos com o Desktop
            if (Desktop.isDesktopSupported()) {
                try {
                    // Tenta abrir o arquivo com o programa padrão do sistema
                    Desktop.getDesktop().open(arquivoEscolhido);
                } catch (Exception e) {
                    // Em caso de erro ao tentar abrir o arquivo
                    System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
                }
            } else {
                // Caso o Desktop não seja suportado no sistema operacional
                System.out.println("Abrir arquivos não é suportado neste sistema.");
            }
        }

        // Fecha o objeto Scanner, liberando o recurso de entrada
        scanner.close();
    }
}
