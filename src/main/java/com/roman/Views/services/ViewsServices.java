package com.roman.Views.services;

import com.roman.Views.dto.ViewsResponse;
import com.roman.Views.model.Maids;
import com.roman.Views.repository.ViewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ViewsServices {

    private final ViewsRepository viewsRepository;

    public List<ViewsResponse> getMaidData(){
        List<Maids> viewMaidData = viewsRepository.findAll();
        return viewMaidData.stream().map(data -> mapToViewResponse(data)).toList();
    }

    private ViewsResponse mapToViewResponse(Maids maid){
        return ViewsResponse.builder()
                .id(maid.getId())
                .name(maid.getName())
                .email(maid.getEmail())
                .loe(maid.getLoe())
                .phone(maid.getPhone())
                .build();

    }
}
