package Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro (@JsonAlias("title") String titulo,
                          @JsonAlias("authors") String autor,
                          @JsonAlias("name") String nome,
                          @JsonAlias("birth_year") String anoAniversario,
                          @JsonAlias("birth_year") String anoMorte)
        {
}