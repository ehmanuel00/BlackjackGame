package proyectoBlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BJ {

    //DEFINIENDO LAS LISTA CONTENEDORAS DE LAS CARTAS
    private static final List<String> figura = new ArrayList<>();
    private static final List<String> cartas = new ArrayList<>();

    //LISTA CONTENEDORA DE LAS MANOS DEL JUGADOR Y CRUPIER
    private List<String> manoJugador = new ArrayList<>();

    private List<String> manoCrupier = new ArrayList<>();

    //VARIABLES QUE ALMACENARAN EL VALOR DE LAS MANOS DEL JUGADOR Y CRUPIER
    private int valorManoJugador;
    private int valorManoCrupier;

    //CARGANDO LA LISTA DE CARTAS
    static {
        figura.add("♥");
        figura.add("♣");
        figura.add("♠");
        figura.add("♦");

        cartas.add("A");
        cartas.add("2");
        cartas.add("3");
        cartas.add("4");
        cartas.add("5");
        cartas.add("6");
        cartas.add("7");
        cartas.add("8");
        cartas.add("9");
        cartas.add("10");
        cartas.add("J");
        cartas.add("Q");
        cartas.add("K");
    }

    //ENTREGANDO CARTAS AL CRUPIER Y CALCULANDO VALOR
    public void cartasCrupier() {
        Random random = new Random();
        valorManoCrupier = 0;
        manoCrupier.clear();

        for (int i = 0; i < 2; i++) {
            String figuraAleatoria = figura.get(random.nextInt(4));
            String cartaAleatoria = cartas.get(random.nextInt(13));

            String cartaCrupier = cartaAleatoria + figuraAleatoria;

            actualizarValorManoCrupier(cartaAleatoria);
            manoCrupier.add(cartaCrupier);
        }
    }

    //ENTREGANDO CARTAS AL CRUPIER Y CALCULANDO VALOR
    public void cartasJugador() {
        Random random = new Random();
        valorManoJugador = 0;
        manoJugador.clear();

        for (int i = 0; i < 2; i++) {
            String figuraAleatoria = figura.get(random.nextInt(4));
            String cartaAleatoria = cartas.get(random.nextInt(13));

            String cartaJugador = cartaAleatoria + figuraAleatoria;

            actualizarValorManoJugador(cartaAleatoria);
            manoJugador.add(cartaJugador);
        }

        imprimirManoJugador();
    }

    //ASIGNANDO VALORES NUMERICOS A LAS CARTAS
    private int actualizarValorManoJugador(String carta) {
        int valor ;
        switch (carta) {
            case "A":
                if (valorManoJugador + 11 > 21) {
                    valor = 1;
                } else {
                    valor = 11;
                }
                break;

            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                valor = Integer.parseInt(carta);
                break;

            case "10":
            case "J":
            case "Q":
            case "K":
                valor = 10;
                break;

            default:
                valor = 0;
        }

        valorManoJugador += valor;

        return valorManoJugador;
    }
    //ASIGNANDO VALORES NUMERICOS A LAS CARTAS
    private int actualizarValorManoCrupier(String carta) {
        int valor;
        switch (carta) {
            case "A":
                if (valorManoCrupier + 11 > 21) {
                    valor = 1;
                } else {
                    valor = 11;
                }
                break;

            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                valor = Integer.parseInt(carta);
                break;

            case "10":
            case "J":
            case "Q":
            case "K":
                valor = 10;
                break;

            default:
                valor = 0;
        }

        valorManoCrupier += valor;

        return valorManoCrupier;
    }

    //MOSTRANDO LAS CARTAS DEL JUGADOR
    private void imprimirManoJugador() {
        for (String carta : manoJugador) {
            System.out.print(carta + " ");
        }
        System.out.println();
    }

    //MOSTRANDO LAS PRIMERA CARTA DEL CRUPIER
    private void imprimirManoCrupierInicio() {
        System.out.println(manoCrupier.get(0));
    }

    //MOSTRANDO TODAS LAS CARTAS DEL CRUPIER
    private void imprimirManoCuprierFinal() {
        for (String carta : manoCrupier) {
            System.out.print(carta + " ");
        }
    }

    //EJECUCION DE LA LOGICA DEL JUEGO
    public void pedirCartaJugador() {
        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        if (valorManoJugador == 21){
            System.out.println("BLACK JACK!!!!!!");
            System.out.println("LAS CARTAS DEL CUPRIER SON:");
            imprimirManoCuprierFinal();
        }

        if (valorManoCrupier != 21 ) {
            while (valorManoJugador < 21) {
                System.out.println("Desea otra carta (s/n)");
                String decision = scan.next().toLowerCase();

                if (decision.equals("s")) {
                    String figuraAleatoria = figura.get(random.nextInt(4));
                    String cartaAleatoria = cartas.get(random.nextInt(13));

                    String cartaJugador = cartaAleatoria + figuraAleatoria;

                    manoJugador.add(cartaJugador);

                    actualizarValorManoJugador(cartaAleatoria);
                    imprimirManoJugador();
                } else {
                    while (valorManoCrupier < 17) {
                        String figuraAleatoria = figura.get(random.nextInt(4));
                        String cartaAleatoria = cartas.get(random.nextInt(13));

                        String cartaCrupier = cartaAleatoria + figuraAleatoria;

                        actualizarValorManoCrupier(cartaAleatoria);
                        manoCrupier.add(cartaCrupier);
                    }
                    System.out.println("LAS CARTAS DEL JUGADOR SON:");
                    imprimirManoJugador();
                    if (valorManoCrupier < 22) {
                        System.out.println("LAS CARTAS DEL CUPRIER SON:");
                        imprimirManoCuprierFinal();
                    } else {
                        System.out.println("CRUPIER BUST!!!");
                        imprimirManoCuprierFinal();
                        System.out.println("\nJUGADOR GANA");
                    }
                    break;
                }

                if (valorManoJugador > 21) {
                    System.out.println("BUST !!!!! \nJUGADOR PIERDE");
                    System.out.println("\nLAS CARTAS DEL CUPRIER SON:");
                    imprimirManoCuprierFinal();
                    break;
                }

                if (valorManoJugador == 21){
                    imprimirManoCuprierFinal();
                    imprimirManoJugador();
                    ganador();
                }
            }
        }
    }

    public void crupierBJ(){
        Scanner scan = new Scanner(System.in);
        if (valorManoCrupier == 21 && (manoCrupier.get(0).equals("A"))){
            System.out.println("DESEA PAGAR UN SEGURO?(s/n)" );
            String seguro = scan.next();

            if (seguro.equals("s")){
                imprimirManoCuprierFinal();
                System.out.println("EMPATE");
            } else {
                pedirCartaJugador();
                ganador();
            }
        } else {
            pedirCartaJugador();
            ganador();
        }
    }

    //DETERMINAR GANADOR DE LA PARTIDA
    public void ganador() {
        if (valorManoJugador == valorManoCrupier && valorManoCrupier<22 && valorManoJugador<22) {
            System.out.println("\nEMPATE");
        }

        if (valorManoJugador > valorManoCrupier && valorManoCrupier<22 && valorManoJugador<22) {
            System.out.println("\nJUGADOR GANA");
        }

        if (valorManoJugador < valorManoCrupier && valorManoCrupier<22 && valorManoJugador<22) {
            System.out.println("\nJUGADOR PIERDE");
        }
    }

    public static void main(String[] args) {
        BJ blackjack = new BJ();
        System.out.println("LAS CARTAS DEL CUPRIER SON:");
        blackjack.cartasCrupier();
        blackjack.imprimirManoCrupierInicio();
        System.out.println("SUS CARTAS SON:");
        blackjack.cartasJugador();
        blackjack.crupierBJ();
    }
}
