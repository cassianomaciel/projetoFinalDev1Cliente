/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cassi
 */
@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, Integer>
{
    List<Usuario> findByNome(String nome);
    
    List<Usuario> findByNomeContainingOrderByNome(String nome);
    
    List<Usuario> findByCpf(String cpf);
    
    List<Usuario> findByEmailContainingOrderByNome(String email);
    
}
    

