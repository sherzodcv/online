package my.com.service;

import lombok.RequiredArgsConstructor;
import my.com.entity.Images;
import my.com.exceptions.BadRequest;
import my.com.exceptions.ResourceNotFound;
import my.com.model.Result;
import my.com.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository repository;

    @Value("${upload}")
    private String downloadPath;


    public ResponseEntity<?> save(MultipartFile multipartFile) {

        Images image = new Images();

        image.setContentType(multipartFile.getContentType());
        image.setFileSize(multipartFile.getSize());
        image.setName(multipartFile.getOriginalFilename());
        image.setExtension(getExtension(image.getName()).toLowerCase());
        image.setHashId(UUID.randomUUID().toString());

        LocalDate date = LocalDate.now();

        // change value downloadPath
        String localPath = downloadPath + String.format(
                "/%d/%d/%d/%s",
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth(),
                image.getExtension().toLowerCase());

        image.setUploadPath(localPath);


        // downloadPath / year / month / day / extension
        File file = new File(localPath);

        // " downloadPath / year / month / day / extension "   crate directory
        file.mkdirs();

        // save MyFile into base
        repository.save(image);

        try {

            // copy bytes into new file or saving into storage
            multipartFile.transferTo(new File(file.getAbsolutePath() + "/" + String.format("%s.%s", image.getHashId(), image.getExtension())));

            Map<Object, Object> data = new HashMap<>();
            data.put("hashId", image.getHashId());
            return ResponseEntity.ok(new Result("File successfully saved!", true, data));

        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequest("File not saved!");
        }
    }


    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    public Images findByHashId(String hashId) {
        return repository.findByHashId(hashId)
                .orElseThrow(() -> new ResourceNotFound("File ", "hashId", hashId));
    }


    public ResponseEntity<?> delete(String hashId) {
        Images image = findByHashId(hashId);

        File file = new File(String.format("%s/%s.%s", image.getUploadPath(), image.getHashId(), image.getExtension()));

        if (file.delete() && repository.deleteByHashId(hashId)) {

            return ResponseEntity.ok(new Result(true, "File successfully deleted"));

        } else {
            return ResponseEntity.badRequest().body(
                    new Result(("File not deleted by hashId = " + hashId), false, null)
            );
        }

    }


}
