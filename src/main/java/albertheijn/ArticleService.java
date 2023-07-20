package albertheijn;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ArticleService {

    // will retrieve all articles stored
    public List<Article> getAll(){ return null;}
    // will use the id to find an article with the same id
    // if none is found, it will return null
    public Article findById(int id){return  null;}
}
