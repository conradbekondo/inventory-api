package ca.quickdo.springintro.controller;

import ca.quickdo.springintro.dtos.ProductDTO;
import ca.quickdo.springintro.models.Product;
import ca.quickdo.springintro.repository.ProductsRepository;
import ca.quickdo.springintro.repository.MovementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
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
    private final MovementsRepository movementsRepository;

    public InventoryController(ProductsRepository repository, MovementsRepository movementsRepository) {
        this.productsRepository = repository;
        this.movementsRepository = movementsRepository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Product> getAllProducts() {
        return productsRepository.findAll();
    }


    @GetMapping("/products")
    public ResponseEntity<Page<ProductDTO>> getProducts(
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = true) Integer size,
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

        Page<ProductDTO> productDTOs = products.map(product -> {
            int totalQuantity = movementsRepository.calculateTotalQuantityByProduct(product.getId());
            return ProductDTO.builder()
                    .name(product.getName())
                    .color(product.getColor())
                    .description(product.getDescription())
                    .quantity(totalQuantity)
                    .build();
        });

        return ResponseEntity.ok(productDTOs);
    }

}
