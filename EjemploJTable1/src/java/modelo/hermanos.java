/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author queen_&_king
 */
public class hermanos {
    int Id;
    int idinf;
    String mayor ;
    String menor;

    public hermanos() {
    }

    public hermanos(int Id, int idinf, String mayor, String menor) {
        this.Id = Id;
        this.idinf = idinf;
        this.mayor = mayor;
        this.menor = menor;
    }

    public hermanos(int idinf, String mayor, String menor) {
        this.idinf = idinf;
        this.mayor = mayor;
        this.menor = menor;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdinf() {
        return idinf;
    }

    public void setIdinf(int idinf) {
        this.idinf = idinf;
    }

    public String getMayor() {
        return mayor;
    }

    public void setMayor(String mayor) {
        this.mayor = mayor;
    }

    public String getMenor() {
        return menor;
    }

    public void setMenor(String menor) {
        this.menor = menor;
    }
    
    @Override
    public String toString() {
        return "TablaPokemones{" + "idusuario    =" + Id + ", de id=" + idinf + ", hermanomayor=" + mayor + ", hermanomenor =" + menor + '}';
    }
    
}
