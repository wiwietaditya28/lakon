package com.example.demo.service;

import com.example.demo.model.BaseResponseDto;
import com.example.demo.model.Buah;
import com.example.demo.repository.BuahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBuahService {

    @Autowired
    private BuahRepository buahRepository;

    public BaseResponseDto<Buah> getBuahById(Long id) {
        var buah = buahRepository.findByIdAndIsDeleted(id, Boolean.FALSE);

        BaseResponseDto response = new BaseResponseDto<>();

        if (buah.isEmpty()) {
            response.setResponseCode("04");
            response.setMessage("Data tidak ditemukan");

            return response;
        }

        response.setResponseCode("00");
        response.setMessage("success");
        response.setResult(buah.get());

        return response;
    }
}
