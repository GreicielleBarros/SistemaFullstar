import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class FullStar {

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equalsIgnoreCase("tela")) {

            rodarComInterfaceGrafica();

        } else {

            rodarNoTerminal();

        }
    }

    // ==================== BACK-END (TERMINAL) ====================

    public static void rodarNoTerminal() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- MODO TERMINAL INICIADO ---");

        System.out.println("Olá! Seja Bem-Vindo");

        System.out.println("Como deseja ser chamado?");

        String nomeUsuario = scanner.nextLine();

        if (nomeUsuario.contains(" ")) {

            System.out.println("Permitido");

        } else {

            System.out.println("Negado.");

            System.out.println("Digite novamente seu nome completo:");

            nomeUsuario = scanner.nextLine();

            if (nomeUsuario.contains(" ")) {

                System.out.println("Liberado");

            } else {

                System.out.println("Acesso negado definitivo.");

                return;
            }
        }

        System.out.println("Digite seu CPF:");

        String cpf = scanner.nextLine();

        if (cpf.length() >= 7) {

            System.out.println("CPF validado!");

        } else {

            System.out.println("CPF inválido.");

            return;
        }

        int anoNascimento;

        while (true) {

            System.out.println("Digite seu ano de nascimento:");

            String entrada = scanner.nextLine();

            try {

                anoNascimento = Integer.parseInt(entrada);

                if (anoNascimento >= 1920 && anoNascimento <= 2026) {

                    System.out.println("Ano validado!");

                    break;

                } else {

                    System.out.println("Ano inválido.");

                }

            } catch (NumberFormatException e) {

                System.out.println("Digite apenas números.");

            }
        }

        System.out.println("Crie uma senha de acesso:");

        String senha = scanner.nextLine();

        if (senha.length() >= 6) {

            System.out.println("Senha cadastrada com sucesso!");

            System.out.println("========= PAINEL FULLSTAR =========");

            System.out.println("Usuário: " + nomeUsuario);

            System.out.println("CPF: " + cpf);

            System.out.println("Ano de nascimento: " + anoNascimento);

            System.out.println("Acesso liberado.");

        } else {

            System.out.println("Senha muito curta.");

        }

        scanner.close();
    }

    // ==================== FRONT-END (INTERFACE GRÁFICA) ====================

    public static void rodarComInterfaceGrafica() {

        JFrame janela = new JFrame("FullStar");

        janela.setSize(500, 550);

        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        janela.setLayout(new GridLayout(11, 1, 10, 10));

        // COMPONENTES

        JLabel labelBoasVindas = new JLabel(
                "Olá! Como deseja ser chamado?",
                SwingConstants.CENTER
        );

        JTextField campoNome = new JTextField();

        JLabel labelCpf = new JLabel(
                "Digite seu CPF:",
                SwingConstants.CENTER
        );

        JTextField campoCpf = new JTextField();

        JLabel labelAno = new JLabel(
                "Digite seu ano de nascimento:",
                SwingConstants.CENTER
        );

        JTextField campoAno = new JTextField();

        JLabel labelSenha = new JLabel(
                "Crie uma senha:",
                SwingConstants.CENTER
        );

        JPasswordField campoSenha = new JPasswordField();

        JButton botaoEnviar = new JButton(
                "Próxima Etapa"
        );

        JLabel labelResultado = new JLabel(
                "",
                SwingConstants.CENTER
        );

        // ESCONDIDOS

        labelCpf.setVisible(false);

        campoCpf.setVisible(false);

        labelAno.setVisible(false);

        campoAno.setVisible(false);

        labelSenha.setVisible(false);

        campoSenha.setVisible(false);

        // ADICIONANDO

        janela.add(labelBoasVindas);

        janela.add(campoNome);

        janela.add(labelCpf);

        janela.add(campoCpf);

        janela.add(labelAno);

        janela.add(campoAno);

        janela.add(labelSenha);

        janela.add(campoSenha);

        janela.add(botaoEnviar);

        janela.add(labelResultado);

        botaoEnviar.addActionListener(new ActionListener() {

            private int erros = 0;

            @Override

            public void actionPerformed(ActionEvent e) {

                // ETAPA 1 - NOME

                if (!campoCpf.isVisible()) {

                    String nome = campoNome.getText();

                    if (!nome.contains(" ")) {

                        erros++;

                        if (erros < 2) {

                            labelResultado.setText(
                                    "Digite seu nome completo."
                            );

                        } else {

                            labelResultado.setText(
                                    "Acesso bloqueado."
                            );

                            botaoEnviar.setEnabled(false);

                        }

                        return;
                    }

                    labelResultado.setText(
                            "Nome validado."
                    );

                    labelCpf.setVisible(true);

                    campoCpf.setVisible(true);

                    janela.revalidate();
                }

                // ETAPA 2 - CPF

                else if (!campoAno.isVisible()) {

                    String cpf = campoCpf.getText();

                    if (cpf.length() < 7) {

                        labelResultado.setText(
                                "CPF inválido."
                        );

                        return;
                    }

                    labelResultado.setText(
                            "CPF validado."
                    );

                    labelAno.setVisible(true);

                    campoAno.setVisible(true);

                    janela.revalidate();
                }

                // ETAPA 3 - ANO

                else if (!campoSenha.isVisible()) {

                    try {

                        int ano = Integer.parseInt(
                                campoAno.getText()
                        );

                        if (ano >= 1920 && ano <= 2026) {

                            labelResultado.setText(
                                    "Ano validado."
                            );

                            labelSenha.setVisible(true);

                            campoSenha.setVisible(true);

                            janela.revalidate();

                        } else {

                            labelResultado.setText(
                                    "Ano inválido."
                            );
                        }

                    } catch (NumberFormatException ex) {

                        labelResultado.setText(
                                "Digite apenas números."
                        );
                    }
                }

                // ETAPA 4 - PAINEL FINAL

                else {

                    String senha = new String(
                            campoSenha.getPassword()
                    );

                    if (senha.length() < 6) {

                        labelResultado.setText(
                                "Senha muito curta."
                        );

                        return;
                    }

                    janela.getContentPane().removeAll();

                    janela.setLayout(new BorderLayout());

                    JPanel painel = new JPanel();

                    painel.setBackground(
                            new Color(30,30,30)
                    );

                    painel.setLayout(
                            new GridLayout(6,1)
                    );

                    JLabel titulo = new JLabel(
                            "PAINEL FULLSTAR",
                            SwingConstants.CENTER
                    );

                    titulo.setForeground(
                            Color.YELLOW
                    );

                    titulo.setFont(
                            new Font(
                                    "Arial",
                                    Font.BOLD,
                                    22
                            )
                    );

                    JLabel nome = new JLabel(
                            "Usuário: "
                                    + campoNome.getText(),
                            SwingConstants.CENTER
                    );

                    nome.setForeground(
                            Color.WHITE
                    );

                    JLabel cpf = new JLabel(
                            "CPF: "
                                    + campoCpf.getText(),
                            SwingConstants.CENTER
                    );

                    cpf.setForeground(
                            Color.WHITE
                    );

                    JLabel ano = new JLabel(
                            "Ano: "
                                    + campoAno.getText(),
                            SwingConstants.CENTER
                    );

                    ano.setForeground(
                            Color.WHITE
                    );

                    JLabel senhaOk = new JLabel(
                            "Senha cadastrada com sucesso",
                            SwingConstants.CENTER
                    );

                    senhaOk.setForeground(
                            Color.GREEN
                    );

                    JButton finalizar = new JButton(
                            "Finalizar"
                    );

                    finalizar.addActionListener(
                            ev -> System.exit(0)
                    );

                    painel.add(titulo);

                    painel.add(nome);

                    painel.add(cpf);

                    painel.add(ano);

                    painel.add(senhaOk);

                    painel.add(finalizar);

                    janela.add(
                            painel,
                            BorderLayout.CENTER
                    );

                    janela.revalidate();

                    janela.repaint();
                }
            }
        });

        janela.setLocationRelativeTo(null);

        janela.setVisible(true);
    }
}