package com.example.techshopgreg.entities;

public class Tarefa {
    private Long id;
    private String titulo;
    private String desc;
    private String status;
    private int deadline;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDesc() {
        return desc;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public int getDeadline() {
        return deadline;
    }
    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    
}
