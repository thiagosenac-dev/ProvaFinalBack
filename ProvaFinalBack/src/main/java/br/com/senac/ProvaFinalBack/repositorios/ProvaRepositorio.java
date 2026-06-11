package br.com.senac.ProvaFinalBack.repositorios;

import br.com.senac.ProvaFinalBack.entidades.Prova;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProvaRepositorio extends JpaRepository<Prova, Long> {
    List<Prova> findByTitulo (String titulo);
}
