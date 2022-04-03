public class CpfUtils {

    public static boolean isValido(String cpf) {
        String cpfGerado;
        cpf = obterSomenteNumeros(cpf);

        if (isTamanhoInvalido(cpf) || isDigitosIguais(cpf)) {
            return false;
        }

        cpfGerado = cpf.substring(0, 9);
        cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));
        cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));

        return cpfGerado.equals(cpf);
    }

    private static String obterSomenteNumeros(String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }

    private static boolean isTamanhoInvalido(String cpf){
        return cpf.length() != 11;
    }

    private static boolean isDigitosIguais(String cpf) {
        char[] charCpf = cpf.toCharArray();

        for (char c: charCpf) {
            if (c != cpf.charAt(0)) {
                return false;
            }
        }

        return true;
    }

    private static String calculoComCpf(String cpf) {
        int digGerado = 0;
        int mult = cpf.length() + 1;
        char [] charCpf = cpf.toCharArray();

        for (int i = 0; i < cpf.length(); i++) {
            digGerado += (charCpf[i] - 48) * mult--;
        }

        if (digGerado % 11 < 2) {
            digGerado = 0;
        } else {
            digGerado = 11 - digGerado % 11;
        }

        return String.valueOf(digGerado);
    }
}

