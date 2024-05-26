package Repository;

import Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByTituloIgnoreCase(String nomeLivro);

    @Query("SELECT e FROM livro s JOIN s.titulo e WHERE e.autor ILIKE %:porTitulo%")
    List<Livro> livrosRegistrados(String porTitulo);

    @Query("SELECT e FROM livro s JOIN s.titulo e WHERE e.autor ILIKE %:porAutor%")
    List<Livro> livrosRegistrados(String porAutor);
}