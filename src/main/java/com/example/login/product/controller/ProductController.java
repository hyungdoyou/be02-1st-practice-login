package com.example.login.product.controller;


import com.example.login.product.model.ProductDto;
import com.example.login.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity create(ProductDto productDto) {
        productService.create(productDto);

        return ResponseEntity.ok().body("상품 생성");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity list() {

        return ResponseEntity.ok().body(productService.list());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read(Integer id) {

        return ResponseEntity.ok().body(productService.read(id));
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/update")
    public ResponseEntity update(ProductDto productDto) {
        productService.update(productDto);

        return ResponseEntity.ok().body("상품 수정");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public ResponseEntity delete(Integer id) {
        productService.delete(id);
        return ResponseEntity.ok().body("상품 삭제");

    }
}
