package br.com.danilomr.tcp.service;

import org.springframework.stereotype.Service;

@Service
public class CpfValidationService {

    public String validate(final String cpf) {
        if (isValid(cpf)) {
            return "CPF Válido";
        }
        return "CPF Inválido";
    }

    private boolean isValid(final String cpf) {
        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = ((int) cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = ((int) cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            return ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)));
        } catch (Exception ex) {
            System.out.println("Erro ao tentar verificar se o CPF é válido");
            return(false);
        }
    }
}
