package ca.quickdo.springintro.controller;

import ca.quickdo.springintro.dtos.ProductDTO;
import ca.quickdo.springintro.dtos.UnitConfigurationDTO;
import ca.quickdo.springintro.models.Product;
import ca.quickdo.springintro.models.Unit;
import ca.quickdo.springintro.repository.MovementsRepository;
import ca.quickdo.springintro.repository.ProductsRepository;
import ca.quickdo.springintro.repository.UnitsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/api/inventory")
public class InventoryController {
    private final ProductsRepository productsRepository;
    private final MovementsRepository movementsRepository;

    private final UnitsRepository unitsRepository;

    public InventoryController(ProductsRepository repository, MovementsRepository movementsRepository, UnitsRepository unitsRepository) {
        // Assigned Repositories dependency
        this.productsRepository = repository;
        this.movementsRepository = movementsRepository;
        this.unitsRepository = unitsRepository;
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

    @PostMapping(path = "/products")
    public ResponseEntity<?> addProduct(@RequestBody ProductDTO product) {
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

    @PostMapping(path = "/products/{productId}/units")
    @Transactional
    public ResponseEntity<?> createUnitsForProduct(
            @PathVariable Integer productId,
            @RequestBody List<UnitConfigurationDTO> unitConfigurations
    ) {
        Optional<Product> optionalProduct = productsRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Product product = optionalProduct.get();

        try {
            // Create units for the product with the specified configurations
            List<Unit> units = new ArrayList<>();

            for (UnitConfigurationDTO configuration : unitConfigurations) {
                if (productsRepository.existsByUnits_NameAndId(configuration.getName(), productId)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body("A unit already exists with the same name for the product");
                }

                Unit unit = createUnit(configuration, product);
                units.add(unit);
            }

            // Save the units
            unitsRepository.saveAll(units);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            // Handle any exception that occurred during unit creation
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Unit createUnit(UnitConfigurationDTO configuration, Product product) {
        Unit unit = Unit.builder()
                .name(configuration.getName())
                .price(configuration.getPrice())
                .baseMultiplier(configuration.getBaseMultiplier())
                .product(product)
                .build();
        return unit;
    }
}


