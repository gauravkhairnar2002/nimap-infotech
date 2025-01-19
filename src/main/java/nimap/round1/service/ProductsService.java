package nimap.round1.service;


import nimap.round1.entity.Categories;
import nimap.round1.entity.Products;
import nimap.round1.payload.ProductsDto;
import nimap.round1.repository.CategoriesRepository;
import nimap.round1.repository.ProductsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
  private  ProductsRepository productsRepository;
  private CategoriesRepository categoriesRepository;

    public ProductsService(ProductsRepository productsRepository, CategoriesRepository categoriesRepository) {
        this.productsRepository = productsRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public  String addProducts(ProductsDto pro, Long categoriesId){
        Products products = DtoToMap(pro);
        Optional<Categories> byId = categoriesRepository.findById(categoriesId);
        if(byId.isPresent()){
            Categories categories1 = byId.get();
            products.setCategories(categories1);
            productsRepository.save(products);
            return "Product add successfully";
        }
       throw new RuntimeException("categories not found");
    }
    public Products DtoToMap(ProductsDto dto){
        Products pro = new Products();
        pro.setName(dto.getName());
        return pro;
    }
    public String deleteProducts(Long id){
       Optional<Products> byId = productsRepository.findById(id);
       if (byId.isPresent()){
           productsRepository.deleteById(id);
           return "product deleted sucessfully";
       }
       throw  new RuntimeException("product not found");
    }

    public void updateProducts(Long id, ProductsDto dto) {
        Optional<Products> list = productsRepository.findById(id);
        Products products = list.get();
        products.setName(dto.getName());
        productsRepository.save(products);
    }

    public Products getbyId(Long id) {
        Optional<Products> byId = productsRepository.findById(id);
        Products products = byId.get();
        return products;
    }
    public List<Products> getProducts(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Products> all = productsRepository.findAll(pageable);
        return all.getContent();
    }
}
