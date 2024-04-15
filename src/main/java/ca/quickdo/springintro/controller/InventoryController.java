package ca.quickdo.springintro.controller;

import ca.quickdo.springintro.models.Product;
import ca.quickdo.springintro.repository.ProductsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {
    private final ProductsRepository productsRepository;

    public InventoryController(ProductsRepository repository) {
        this.productsRepository = repository;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Product> getAllProducts() {
        return productsRepository.findAll();
    }
}
