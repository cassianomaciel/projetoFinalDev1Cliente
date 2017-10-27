/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.edu.ifrs.restinga.dev1.projetoFinal.tudo.DAO;

import be.edu.ifrs.restinga.dev1.projetoFinal.tudo.modelo.Entregador;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jezer
 */

@Repository
public interface EntregadorDAO extends CrudRepository<Entregador, Integer>
{
    List<Entregador> findByNome(String nome);
    
    List<Entregador> findByNomeContainingOrderByNome(String nome);
    
    List<Entregador> findByCpf(String cpf);
    
    List<Entregador> findByEmailContainingOrderByNome(String email);
}
