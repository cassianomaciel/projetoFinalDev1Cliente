/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.controller;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO.EntregadorDAO;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Entregador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntregadorControll 
{
    @Autowired
    EntregadorDAO entregadorDAO;
    
  
    @RequestMapping(path="/api/entregador", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Entregador inserir(@RequestBody Entregador entregador)
    {
        entregador.setId(0);
        Entregador entregadorSalvo = entregadorDAO.save(entregador);
        return entregadorSalvo;
    }   
    
    @RequestMapping(path="/api/entregador/{id}", method = RequestMethod.GET)
    public Entregador encontrar(@PathVariable int id)
    {
        return entregadorDAO.findOne(id); 
    }
    
    @RequestMapping(path = "/api/entregadores", method = RequestMethod.GET)
    public Iterable<Entregador> listar() 
    {
        return entregadorDAO.findAll();
    }
    
    @RequestMapping(path= "/api/entregador/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagar(@PathVariable int id) 
    {
        if(entregadorDAO.exists(id))
        {
            entregadorDAO.delete(id);
        }
    }
        
    @RequestMapping(path = "/api/entregador/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@PathVariable int id, @RequestBody Entregador entregador)
    {
        if(entregadorDAO.exists(id))
        {
            entregador.setId(id);
            entregadorDAO.save(entregador);
        }
    }
    
    @RequestMapping(path = "/api/entregador/pesquisar/nome", method = RequestMethod.GET)
    public Iterable<Entregador> pesquisaPorNome(
            @RequestParam(required = false) String igual,
            @RequestParam(required = false) String contem) {
        if(igual!=null){
            return entregadorDAO.findByNome(igual);
        } else {
            return entregadorDAO.findByNomeContainingOrderByNome(contem);
        }
    }
}
    
