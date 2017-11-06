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
public class informacion {
    int Id;
    int idusuario;
    String info ;
    String apodo;

    public informacion() {
    }

    public informacion(int Id,int idusuario1, String info, String apodo) {
        this.Id = Id;
        this.idusuario=idusuario1;
        this.info = info;
        this.apodo = apodo;
    }

    public informacion(int idusuario1, String info, String apodo) {
        this.idusuario=idusuario1;
        this.info = info;
        this.apodo = apodo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
    @Override
    public String toString() {
        return "TablaPokemones{" + "id    =" + Id + ", de idusuario=" + idusuario + ", info=" + info + ", apodo =" + apodo + '}';
    }
}
