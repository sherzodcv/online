package my.com.service;

import my.com.entity.Images;
import my.com.entity.Laptop;
import my.com.exceptions.ResourceNotFound;
import my.com.model.Result;
import my.com.payload.ReqLaptop;
import my.com.repository.ImageRepository;
import my.com.repository.LaptopRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;
    private final ImageRepository imageRepository;

    public LaptopService(LaptopRepository laptopRepository, ImageRepository imageRepository) {
        this.laptopRepository = laptopRepository;
        this.imageRepository = imageRepository;
    }

    public Result saveOrEdit(ReqLaptop reqLaptop) {

        if (reqLaptop.getId() != null) {
            try {
                Laptop laptop = laptopRepository.findById(reqLaptop.getId()).orElseThrow(() -> new ResourceNotFound("Laptop", "id", reqLaptop.getId()));
                laptop.setHdd(reqLaptop.getHdd());
                laptop.setProtsessor(reqLaptop.getProtsessor());
                laptop.setPrSpeed(reqLaptop.getPrSpeed());
                laptop.setRam(reqLaptop.getRam());
                laptop.setScreen(reqLaptop.getScreen());
                laptop.setColor(reqLaptop.getColor());
                laptop.setModel(reqLaptop.getModel());
                laptop.setPrice(reqLaptop.getPrice());
                Images image = imageRepository.findByHashId(reqLaptop.getImageId()).orElseThrow(() -> new ResourceNotFound("Images", "id", reqLaptop.getImageId()));
                laptop.setImage(image);
                laptopRepository.save(laptop);
                return new Result("success", true, laptop);
            } catch (ResourceNotFound resourceNotFound) {
                resourceNotFound.printStackTrace();
            }
        } else {
            try {
                Laptop laptop = new Laptop();
                laptop.setHdd(reqLaptop.getHdd());
                laptop.setProtsessor(reqLaptop.getProtsessor());
                laptop.setPrSpeed(reqLaptop.getPrSpeed());
                laptop.setRam(reqLaptop.getRam());
                laptop.setScreen(reqLaptop.getScreen());
                laptop.setColor(reqLaptop.getColor());
                laptop.setModel(reqLaptop.getModel());
                laptop.setPrice(reqLaptop.getPrice());
                Images image = imageRepository.findByHashId(reqLaptop.getImageId()).orElseThrow(() -> new ResourceNotFound("Images", "id", reqLaptop.getImageId()));
                laptop.setImage(image);
                laptopRepository.save(laptop);
                return new Result("success", true, laptop);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new Result("error", false);
        }
        return new Result("error", false);
    }

    public Result search(String search) {

        try {
            List<Laptop> result = laptopRepository.findAllByHddContainingOrRamContainingOrProtsessorContainingOrPrSpeedContainingOrScreenContainingOrModelContainingOrPriceContainingOrColorContaining(search, search, search, search, search, search, search, search);
            return new Result("success", true, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result("error", false);
    }
}
