package ca.quickdo.springintro.controller;

import ca.quickdo.springintro.dto.NewProductDto;
import ca.quickdo.springintro.models.Product;
import ca.quickdo.springintro.repository.ProductsRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {
    private final ProductsRepository productsRepository;

    public InventoryController(ProductsRepository repository) {
        this.productsRepository = repository;
    }

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            @RequestParam(required = false) Integer offset,
            @RequestParam(defaultValue= "50") Integer size,
            @RequestParam(required = false) Integer afterProductId
    ) {
        Pageable pageable = PageRequest.of(
                offset != null ? offset : 0,
                size,
                Sort.by(Sort.Direction.ASC, "id")
        );

        Page<Product> products;
        if (afterProductId != null) {
            products = productsRepository.findByIdGreaterThan(afterProductId, pageable);
        } else {
            products = productsRepository.findAll(pageable);
        }

        return ResponseEntity.ok(products);
    }

    @PostMapping(path = "/products")
    public ResponseEntity<?> addProduct(@RequestBody NewProductDto product) {
        if (this.productsRepository.existsByName(product.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("A product already exists with this nama");
        } else {
            var newProduct = Product.builder()
                    .name(product.getName())
                    .build();
            return ResponseEntity.of(Optional.of(productsRepository.save(newProduct)));


        }
    }
}
