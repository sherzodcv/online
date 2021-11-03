package my.com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import my.com.entity.base.SuperEntity;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Images extends SuperEntity {

    private String name;

    private String hashId;

    private String uploadPath;

    private String contentType;

    private String extension;

    private Long fileSize;

}
