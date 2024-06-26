package com.hudson.soares.clientes.clientes.model.interfaces.validators;

import java.util.InputMismatchException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFConstraint implements ConstraintValidator<CPF, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
            // considera-se erro CPF"s formados por uma sequencia de numeros iguais
            if (value.equals("00000000000") ||
                value.equals("11111111111") ||
                value.equals("22222222222") || value.equals("33333333333") ||
                value.equals("44444444444") || value.equals("55555555555") ||
                value.equals("66666666666") || value.equals("77777777777") ||
                value.equals("88888888888") || value.equals("99999999999") ||
                (value.length() != 11))
                return(false);
    
            char dig10; 
            char dig11;
            int sm;
            int i; 
            int r;
            int num; 
            int peso;
    
            // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
            try {
            // Calculo do 1o. Digito Verificador
                sm = 0;
                peso = 10;
                for (i=0; i<9; i++) {
            // converte o i-esimo caractere do CPF em um numero:
            // por exemplo, transforma o caractere "0" no inteiro 0
            // (48 eh a posicao de "0" na tabela ASCII)
                num = (value.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
                }
    
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                    dig10 = '0';
                else dig10 = (char)(r + 48); // converte no respectivo caractere numerico
    
            // Calculo do 2o. Digito Verificador
                sm = 0;
                peso = 11;
                for(i=0; i<10; i++) {
                num = (value.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
                }
    
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11))
                     dig11 = '0';
                else dig11 = (char)(r + 48);
    
            // Verifica se os digitos calculados conferem com os digitos informados.
                if ((dig10 == value.charAt(9)) && (dig11 == value.charAt(10)))
                     return(true);
                else return(false);
                    } catch (InputMismatchException erro) {
                    return(false);
                }
    
    }
    
}
