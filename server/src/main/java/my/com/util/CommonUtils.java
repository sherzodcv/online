package my.com.util;


import my.com.exceptions.BadRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


public class CommonUtils {

    public static void validatePageAndSize(int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            throw new BadRequest("Page number can't be less than 1");
        }

        if (pageSize > AppConstants.MAX_PAGE_SIZE || pageSize < 1) {
            throw new BadRequest("Incorrect page size. Page size must be between 1 and " + AppConstants.MAX_PAGE_SIZE);
        }

    }

    public static Pageable createPageable(int pageNumber, int pageSize) {
        validatePageAndSize(pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return pageable;
    }
}
