package nimap.round1.controller;


import nimap.round1.entity.Categories;
import nimap.round1.entity.Products;
import nimap.round1.payload.ProductsDto;
import nimap.round1.service.ProductsService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping("/add")
    public String addProducts(@RequestBody ProductsDto proDto , @RequestParam Long categoriesId) {

        String s = productsService.addProducts(proDto, categoriesId);
        return s;

    }

    @DeleteMapping
    public String deleteProducts(@RequestParam long productsId){
       String s = productsService.deleteProducts(productsId);
               return s;
    }

    @PutMapping
    public String updateProducts(@RequestParam Long id ,@RequestBody ProductsDto dto){
        System.out.println(dto.getName());
        productsService.updateProducts(id,dto);
        return "Product update Successfully.";
    }


    @GetMapping
    public Products getbyID(Long id){
        Products products= productsService.getbyId(id);
        return products;


    }
    @GetMapping(value = "/list")
    public List<Products> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size){
        return productsService.getProducts(page,size);
    }

}

