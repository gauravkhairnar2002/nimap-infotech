package nimap.round1.controller;


import nimap.round1.entity.Categories;
import nimap.round1.entity.Products;
import nimap.round1.payload.CategoriesDto;
import nimap.round1.payload.ProductsDto;
import nimap.round1.service.CategoriesService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoriesController {
     private CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @PostMapping("/add")
     public String addCategories(@RequestBody Categories cat){

      categoriesService.addCategories(cat);
      return "saved sucessfully";


    }

    @DeleteMapping
    public String deleteCategories(@RequestParam long categoriesId){
        String s = categoriesService.deleteCategories(categoriesId);
        return s;
    }

    @PutMapping
    public String updateCategories(@RequestParam Long id ,@RequestBody CategoriesDto dto){
        System.out.println(dto.getName());
        categoriesService.updateCategories(id,dto);
        return "Category  update Successfully.";
    }


    @GetMapping
    public Categories getbyID(Long id){
        Categories categories= categoriesService.getbyId(id);
        return categories;


    }
    @GetMapping(value = "/list")
    public List<Categories> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        return categoriesService.getCategories(page,size);
    }

}
