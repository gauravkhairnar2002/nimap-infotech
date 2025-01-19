package nimap.round1.service;


import nimap.round1.entity.Categories;
import nimap.round1.entity.Products;
import nimap.round1.payload.CategoriesDto;
import nimap.round1.payload.ProductsDto;
import nimap.round1.repository.CategoriesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    private CategoriesRepository categoriesRepository;

    public CategoriesService(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    public void addCategories(Categories cat) {
        categoriesRepository.save(cat);
    }



    public String deleteCategories(Long id){
        Optional<Categories> byId = categoriesRepository.findById(id);
        if (byId.isPresent()){
            //multiple category and one product
            categoriesRepository.deleteById(id);
            return "Category deleted sucessfully";
        }
        throw  new RuntimeException("Category not found");
    }

    public void updateCategories(Long id, CategoriesDto dto) {
        Optional<Categories> list = categoriesRepository.findById(id);
        Categories categories = list.get();
        categories.setName(dto.getName());
        categoriesRepository.save(categories);
    }

    public Categories getbyId(Long id) {
        Optional<Categories> byId = categoriesRepository.findById(id);
        Categories categories = byId.get();
        return categories;
    }
    public List<Categories> getCategories(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Categories> all = categoriesRepository.findAll(pageable);
        return all.getContent();
    }
}
