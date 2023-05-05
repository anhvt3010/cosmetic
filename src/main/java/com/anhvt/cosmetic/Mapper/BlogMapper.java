package com.anhvt.cosmetic.Mapper;

import com.anhvt.cosmetic.Entity.Blog;
import com.anhvt.cosmetic.DTO.BlogDTO;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.anhvt.cosmetic.Utils.ConvertDate.TimestampToDate;

public class BlogMapper {
    public static BlogDTO convertToBlogDTO(Blog blog){
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setContent(blog.getContent());
        blogDTO.setImage(blog.getImage());
        blogDTO.setCreated_at(TimestampToDate(blog.getCreated_at()));
        blogDTO.setStatus(blog.getStatus());
        return blogDTO;
    }

    public static Iterable<BlogDTO> convertToBlogDTOs(Iterable<Blog> blogs) {
        return StreamSupport.stream(blogs.spliterator(), false)
                .map(BlogMapper::convertToBlogDTO)
                .collect(Collectors.toList());
    }
}
