/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;


public class Usuario {

    private Integer id;
    private Integer personaId;
    private String password;
    private String usuario;
    private Integer perfilId;

    public Usuario() {
    }

    public Usuario(Integer id, Integer personaId, String password, String usuario, Integer perfilId) {
        this.id = id;
        this.personaId = personaId;
        this.password = password;
        this.usuario = usuario;
        this.perfilId = perfilId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Integer personaId) {
        this.personaId = personaId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Integer perfilId) {
        this.perfilId = perfilId;
    }
}
