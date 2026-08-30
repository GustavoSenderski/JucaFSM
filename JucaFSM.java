public class JucaFSM {

    // Estados possíveis do Juca
    enum State {
        WORKING,
        EATING,
        SLEEPING
    }

    public static void main(String[] args) {

        // Estado e valores iniciais
        State state = State.WORKING;
        int hunger = 0;
        int fatigue = 0;

        System.out.println("Hora de ir para o trabalho!");

        // Simulação da máquina de estados
        for (int tick = 1; tick <= 20; tick++) {

            System.out.println("\n--- TICK " + tick + " ---");

            // Atualiza os valores de acordo com o estado atual
            switch (state) {

                case WORKING:
                    hunger += 2;
                    fatigue += 5;
                    break;

                case EATING:
                    hunger -= 5;
                    break;

                case SLEEPING:
                    hunger += 1;
                    fatigue -= 10;
                    break;
            }

            // Impede fome e cansaço de ficarem negativos
            hunger = Math.max(hunger, 0);
            fatigue = Math.max(fatigue, 0);

            // Exibe o estado atual
            switch (state) {

                case WORKING:
                    System.out.println("Trabalhando...");
                    break;

                case EATING:
                    System.out.println("Comendo...");
                    break;

                case SLEEPING:
                    System.out.println("Dormindo...");
                    break;
            }

            // Exibe os valores atuais
            System.out.println("Fome: " + hunger);
            System.out.println("Cansaço: " + fatigue);

            // Verifica as transições entre os estados
            switch (state) {

                case WORKING:

                    // O sono tem prioridade sobre a fome
                    if (fatigue > 50) {

                        state = State.SLEEPING;
                        System.out.println("Bateu um sono...");

                    } else if (hunger > 10) {

                        state = State.EATING;
                        System.out.println("Bateu uma fome...");
                    }

                    break;

                case EATING:

                    // Continua comendo até ficar satisfeito
                    if (hunger <= 0) {

                        hunger = 0;

                        System.out.println("Ufa! Já estou cheio...");

                        state = State.WORKING;

                        System.out.println("Hora de ir para o trabalho!");
                    }

                    break;

                case SLEEPING:

                    // Continua dormindo até eliminar o cansaço
                    if (fatigue <= 0) {

                        fatigue = 0;

                        // Ao acordar, verifica se está com fome
                        if (hunger <= 10) {

                            state = State.WORKING;
                            System.out.println("Hora de ir para o trabalho!");

                        } else {

                            state = State.EATING;
                            System.out.println("Bateu uma fome...");
                        }
                    }

                    break;
            }

            // Pausa de 1 segundo para acompanhar cada tick
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}