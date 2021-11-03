package my.com.controller;

import my.com.entity.Laptop;
import my.com.exceptions.ResourceNotFound;
import my.com.model.Result;
import my.com.payload.ReqLaptop;
import my.com.repository.LaptopRepository;
import my.com.service.LaptopService;
import my.com.util.CommonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/laptop")
public class LaptopController {

    private final LaptopRepository laptopRepository;
    private final LaptopService laptopService;

    public LaptopController(LaptopRepository laptopRepository, LaptopService laptopService) {
        this.laptopRepository = laptopRepository;
        this.laptopService = laptopService;
    }

    @GetMapping("/get/{page}/{size}")
    public Page<?> getAll(@PathVariable Integer page, @PathVariable Integer size) {
        Pageable pageable = CommonUtils.createPageable(page, size);
        return laptopRepository.findAll(pageable);
    }

    @PostMapping("/save/edit")
    public ResponseEntity<?> saveOrEdit(@RequestBody ReqLaptop reqLaptop) {
        Result save = laptopService.saveOrEdit(reqLaptop);
        return ResponseEntity.status(save.isSuccess() ? 200 : 409).body(save);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable UUID id) {
        try {
            Laptop laptop = laptopRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Laptop", "id", id));
            laptopRepository.delete(laptop);
            return new Result("success", true);
        } catch (ResourceNotFound resourceNotFound) {
            resourceNotFound.printStackTrace();
        }
        return new Result("error", false);
    }

    @GetMapping("/search/{search}")
    public ResponseEntity<Result> search(@PathVariable String search) {
        Result result = laptopService.search(search);
        return ResponseEntity.status(result.isSuccess() ? 200 : 409).body(result);
    }
}
