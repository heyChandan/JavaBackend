package com.jbdl63.library.dto;


import com.jbdl63.library.Model.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RangeDataDto {

    Integer size;
    List<Author> data;
}
