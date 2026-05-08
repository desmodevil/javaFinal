package desmodevil.javafinal.dto.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PanEduardPageResponseDto<T> {

    private List<T> content;

    private int pageNumber;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean first;

    private boolean last;
}