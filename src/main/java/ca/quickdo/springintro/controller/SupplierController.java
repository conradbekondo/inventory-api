package ca.quickdo.springintro.controller;

import ca.quickdo.springintro.dtos.SupplierDTO;
import ca.quickdo.springintro.models.Supplier;
import ca.quickdo.springintro.repository.SuppliersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/suppliers")
public class SupplierController {

    private final SuppliersRepository suppliersRepository;

    @Autowired
    public SupplierController(SuppliersRepository suppliersRepository) {
        this.suppliersRepository = suppliersRepository;
    }

    @PostMapping("/suppliers")
    public ResponseEntity<?> addSupplier(@RequestBody SupplierDTO supplierDTO) {
        if (suppliersRepository.existsByName(supplierDTO.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("A supplier already exists with this name");
        } else {
            Supplier newSupplier = Supplier.builder()
                    .name(supplierDTO.getName())
                    .dateAdded(supplierDTO.getDateAdded())
                    .contact(supplierDTO.getContact())
                    .build();
            Supplier savedSupplier = suppliersRepository.save(newSupplier);
            return ResponseEntity.ok(savedSupplier);
        }
    }

    @GetMapping("/suppliers")
    public ResponseEntity<Page<SupplierDTO>> getSuppliers(
            @RequestParam(required = false) Long afterSupplierId,
            Pageable pageable
    ) {
        Page<Supplier> suppliers;
        if (afterSupplierId != null) {
            suppliers = suppliersRepository.findByIdGreaterThan(afterSupplierId, pageable);
        } else {
            suppliers = suppliersRepository.findAll(pageable);
        }

        Page<SupplierDTO> supplierDTOs = suppliers.map(supplier -> {

            return SupplierDTO.builder()
                    .name(supplier.getName())
                    .dateAdded(supplier.getDateAdded())
                    .contact(supplier.getContact())
                    .build();
        });

        return ResponseEntity.ok(supplierDTOs);
    }

    @DeleteMapping("/suppliers/{supplierId}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Long supplierId) {
        if (!suppliersRepository.existsById(supplierId)) {
            return ResponseEntity.notFound().build();
        }

        suppliersRepository.deleteById(supplierId);
        return ResponseEntity.noContent().build();
    }

}
