package vicenzo.springthymeleafcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vicenzo.springthymeleafcrud.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
