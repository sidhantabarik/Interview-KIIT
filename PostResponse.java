package com.kiitinterveiwPrep.Interview.KIT.Payloads;



import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    private List<PostDto> content;
    private int pageNumber;
    private int PageSize;
    private long totalElements;
    private int totalPages;

    private  boolean lastPage;
}
