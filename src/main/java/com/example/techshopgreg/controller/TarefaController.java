package com.example.techshopgreg.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.techshopgreg.entities.Tarefa;
import com.example.techshopgreg.service.TarefaService;

@RestController
@RequestMapping("/greg/tarefa")
public class TarefaController {
    

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService){
        this.tarefaService = tarefaService;
    }


    //MÉTODO FIND
    @GetMapping("find")
    public ResponseEntity<?> buscarTarefas(){
        try {
            List<Tarefa> lista = tarefaService.buscarTarefas();

            if(lista.isEmpty()){
                return new ResponseEntity<>("A lista de tarefas está vazia!!", HttpStatus.NOT_FOUND);
            }else{
                return new ResponseEntity(lista, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro na requisição", HttpStatus.BAD_GATEWAY);
        }
    }
    

    //MÉTODO ADD
    @PostMapping("add")
    public ResponseEntity<?> adicionarTarefa(@RequestBody Tarefa tarefa) {
        try {
            tarefa = tarefaService.adicionarTarefa(tarefa);
            return new ResponseEntity<>(tarefa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //MÉTODO FIND BY ID
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<?> buscarTarefa(@PathVariable("id")Long id){
        try {
            Tarefa tarefa = tarefaService.buscasTarefa(id);
            return new ResponseEntity(tarefa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //MÉTODO DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletarTarefa(@PathVariable("id") Long id){
        try {
            tarefaService.deletarTarefa(id);
            return new ResponseEntity<>("Tarefa exlucida com sucesso", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //MÉTODO PATCH 
    @PatchMapping("/updatestatus/{id}")
    public ResponseEntity<?> atualizarStatusTarefa(@PathVariable("id") Long id, @RequestBody Tarefa tarefaAtualizada){
        try {

            if(tarefaAtualizada.getStatus() == null){
                return new ResponseEntity<>("Não pode estar vazio o status", HttpStatus.BAD_REQUEST);
            }
            Tarefa tarefaExistente = tarefaService.tarefasPorCodigo.get(id);
            tarefaExistente.setStatus(tarefaAtualizada.getStatus());
            return new ResponseEntity(tarefaExistente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> atualizarTarefa(@PathVariable("id") Long id, @RequestBody Tarefa tarefaAtualizada){
        try {

            if(tarefaAtualizada == null){
                return new ResponseEntity<>("Não pode estar vazio", HttpStatus.BAD_REQUEST);
            }
            Tarefa tarefaExistente = tarefaService.tarefasPorCodigo.get(id);
            tarefaExistente.setStatus(tarefaAtualizada.getStatus());
            tarefaExistente.setDeadline(tarefaAtualizada.getDeadline());
            tarefaExistente.setDesc(tarefaAtualizada.getDesc());
            tarefaExistente.setTitulo(tarefaAtualizada.getTitulo());
            return new ResponseEntity(tarefaExistente, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        
    }
}
