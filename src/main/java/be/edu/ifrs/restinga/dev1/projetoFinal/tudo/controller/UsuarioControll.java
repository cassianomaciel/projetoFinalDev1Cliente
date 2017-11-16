/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.controller;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO.UsuarioDAO;
import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Usuario;
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
public class UsuarioControll 
{
    @Autowired
    UsuarioDAO usuarioDAO;
  
    @RequestMapping(path="/api/usuario", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario inserir(@RequestBody Usuario usuario)
    {
        usuario.setId(0);
        Usuario usuarioSalvo = usuarioDAO.save(usuario);
        return usuarioSalvo;
    }   
    
    @RequestMapping(path = "/api/usuario/pesquisar/nome", method = RequestMethod.GET)
    public Iterable<Usuario> pesquisaPorNome(
            @RequestParam(required = false) String igual,
            @RequestParam(required = false) String contem) {
        if(igual!=null){
            return usuarioDAO.findByNome(igual);
        } else {
            return usuarioDAO.findByNomeContainingOrderByNome(contem);
        }
    }
    
    @RequestMapping(path = "/api/produtos/pesquisar/cpf", method = RequestMethod.GET)
    public Iterable<Usuario> pesquisaPorMarcas(@RequestParam(required = false) String igual) {
            return usuarioDAO.findByCpf(igual);
    }
    
    @RequestMapping(path="/api/usuario/{id}", method = RequestMethod.GET)
    public Usuario encontrar(@PathVariable int id)
    {
        return usuarioDAO.findOne(id); 
    }
    
    @RequestMapping(path="/api/usuario/pesquisar/email", method = RequestMethod.GET)
    public Iterable<Usuario> pesquisarPorEmail(@RequestParam String email)
    {
        return usuarioDAO.findByEmailContainingOrderByNome(email);
    }
    
    @RequestMapping(path = "/api/usuarios", method = RequestMethod.GET)
    public Iterable<Usuario> listar() 
    {
        return usuarioDAO.findAll();
    }
    
    @RequestMapping(path= "/api/usuario/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void apagar(@PathVariable int id) 
    {
        if(usuarioDAO.exists(id))
        {
            usuarioDAO.delete(id);
        }
    }
        
    @RequestMapping(path = "/api/usuario/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void atualizar(@PathVariable int id, @RequestBody Usuario usuario)
    {
        if(usuarioDAO.exists(id))
        {
            usuario.setId(id);
            usuarioDAO.save(usuario);
        }
    } 
}
