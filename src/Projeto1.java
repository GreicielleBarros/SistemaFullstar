import java.util.Scanner;

public class Projeto1 {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ola! Seja Bem Vindo  ");
        System.out.println("Como deseja ser chamado ");
        String nomeUsuario = scanner.nextLine();

        if (nomeUsuario.contains(" ")){
            System.out.println("Permitido");
        }else {
            System.out.println("Negado.");
            System.out.println("Tente novamente");
            System.out.println("...............");

            System.out.println("Digite novamente seu nome completo");
            nomeUsuario = scanner.nextLine();
            if (nomeUsuario.contains(" ")) {
                System.out.println("Liberado ");
            } else {
                System.out.println();
            }
            return;
        }

        System.out.println("Vamos associar algumas informaçoes ");
        System.out.println("Digite seu CPF");
        String validacaoCpf = scanner.nextLine();


        if (validacaoCpf.length()>=7){
            System.out.println("Prosseguindo......");
            System.out.println(" ");

        }else {

        }
        }

    }

