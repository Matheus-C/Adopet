/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author Mac
 */
public interface PessoaFisica {
    public String getCPF();
    public void setCPF(String CPF);
    
    public Date getDataNascimento();
    public void setDataNascimento(Date date);
    
}
