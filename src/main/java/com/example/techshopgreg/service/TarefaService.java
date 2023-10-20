package com.example.techshopgreg.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.techshopgreg.entities.Tarefa;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Service
public class TarefaService {
    public Map<Long, Tarefa> tarefasPorCodigo = new HashMap<>();
    private Long proximoId = 1L;
    
    public List<Tarefa> buscarTarefas(){
        return new ArrayList<>(tarefasPorCodigo.values());
    }


    // MÉTODO POST
    public Tarefa adicionarTarefa(Tarefa tarefa) throws Exception{
        
        String titulo = tarefa.getTitulo();
        String desc = tarefa.getDesc();
        String status = tarefa.getStatus();
        int deadline = tarefa.getDeadline();
        
        Long id = proximoId++;
        tarefa.setId(id);

        if(tarefa == null){
            throw new Exception("Tarefa não pode estar vazio!");
        }
        if(titulo == null || titulo.isEmpty()){
            throw new Exception("Titulo não pode estar vazio!");
        }
        if(desc == null || desc.isEmpty()){
            throw new Exception("Descrição não pode estar vazia!");
        }
        if(deadline == 0){
            throw new Exception("Adicione uma deadline");
        }
        if(status == null || status.isEmpty()){
            throw new Exception("Adicione um status");
        }

        tarefasPorCodigo.put(id, tarefa);

        return tarefa;
    }


    //MÉTODO GET
    public Tarefa buscasTarefa(Long id) throws Exception {
        if(tarefasPorCodigo.containsKey(id)){
            return tarefasPorCodigo.get(id);
        }
        throw new Exception("Tarefa não encontrada");
    }

    //MÉTODO DELETE
    public void deletarTarefa(Long id) throws Exception{
        if(tarefasPorCodigo.containsKey(id)) {
            tarefasPorCodigo.remove(id);
        }else {
            throw new Exception("Tarefa não encontrada");
        }
    }

    //MÉTODO PATCH
    public Tarefa updateTarefa(Long id, Tarefa tarefaAtualizada) throws Exception{
        if(tarefaAtualizada == null){
            throw new Exception("Tarefa não encontrada");
        }

        String titulo = tarefaAtualizada.getTitulo();
        String desc = tarefaAtualizada.getDesc();
        String status = tarefaAtualizada.getStatus();
        int deadline = tarefaAtualizada.getDeadline();

        if(titulo == null || titulo.isEmpty()){
            throw new Exception("Campo de titulo não pode estar vazio!!!");
        }
        if(desc == null || desc.isEmpty()){
            throw new Exception("Campo de descrição não pode estar vazio!!!");
        }
        if(status == null || status.isEmpty()){
            throw new Exception("Campo de titulo não pode estar vazio!!!");
        }
        if(deadline == 0){
            throw new Exception("Campo de titulo não pode estar vazio!!!");
        }
    
        Tarefa tarefaExistente = tarefasPorCodigo.get(id);
    
        tarefaExistente.setTitulo(titulo);
        tarefaExistente.setDesc(desc);
        tarefaExistente.setStatus(status);
        tarefaExistente.setDeadline(deadline);
    
        return tarefaExistente;
    }
    



}
