package br.com.senac.ProvaFinalBack.service;
import br.com.senac.ProvaFinalBack.dtos.ProvaFiltroDto;
import br.com.senac.ProvaFinalBack.dtos.ProvaRequestDto;
import br.com.senac.ProvaFinalBack.entidades.Prova;
import br.com.senac.ProvaFinalBack.repositorios.ProvaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProvaService {
    private ProvaRepositorio provaRepositorio;

    public ProvaService(ProvaRepositorio provaRepositorio) {
        this.provaRepositorio = provaRepositorio;
    }

    public List<Prova> listar(ProvaFiltroDto filtro) {
        if(filtro.getTitulo() != null) {
            return provaRepositorio.findByTitulo(filtro.getTitulo());
        }

        return provaRepositorio.findAll();
    }

    public Prova criar(ProvaRequestDto cliente) {
        Prova clientePersist =
                this.provarequestDtoParaProva(cliente);

        return provaRepositorio.save(clientePersist);
    }

    public Prova atualizar(
            Long id,
            ProvaRequestDto prova) {
        if(provaRepositorio.existsById(id)) {
            Prova provaPersist =
                    this.provarequestDtoParaProva(prova);
            provaPersist.setId(id);

            return provaRepositorio.save(provaPersist);
        }

        throw new RuntimeException("Prova não encontrada");
    }

    private Prova provarequestDtoParaProva(
            ProvaRequestDto entrada) {
        Prova saida = new Prova();
        saida.setTitulo(entrada.getTitulo());
        saida.setMateria(entrada.getMateria());
        saida.setData(entrada.getData());

        return saida;
    }

    public void deletar(Long id) {
        if(provaRepositorio.existsById(id)) {
            provaRepositorio.deleteById(id);
        }

        throw new RuntimeException("Prova não encontrada:");
    }

}
