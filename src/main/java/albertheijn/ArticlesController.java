package albertheijn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ArticlesController {

    private final ArticleService service;

    public ArticlesController(ArticleService service) {
        this.service = service;
    }

    @GetMapping("/articles")
    public ResponseEntity<Object> getAll() {
        List<Article> articles = this.service.getAll();
        if (articles == null || articles.isEmpty()) {
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);
        }
        return new ResponseEntity<>(articles.stream().sorted(Comparator.comparing(Article::getTitle)
                ).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/articles/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") Integer id) {
        Article article = this.service.findById(id);
        if (article == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(article, HttpStatus.OK);
    }
}
