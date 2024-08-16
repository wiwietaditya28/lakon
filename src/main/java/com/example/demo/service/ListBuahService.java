package com.example.demo.service;

import com.example.demo.dto.BuahRespDto;
import com.example.demo.model.Buah;
import com.example.demo.repository.BuahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListBuahService {

    @Autowired
    private BuahRepository buahRepository;

    public List<BuahRespDto> buahList() {
        var buahList = buahRepository.findAllByIsDeletedOrderByCreatedDate(Boolean.FALSE);

        List<BuahRespDto> result = new ArrayList<>();
        Long no = 1L;
        for (Buah buah : buahList) {
            try {
                BuahRespDto buahRespDto = new BuahRespDto();
                buahRespDto.setId(buah.getId());
                buahRespDto.setNo(no);
                buahRespDto.setName(buah.getName());

                result.add(buahRespDto);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            no++;
        }

        return result;
    }
}
