package com.example.demo.service;

import com.example.demo.dto.AddBuahReqDto;
import com.example.demo.model.BaseResponseDto;
import com.example.demo.model.Buah;
import com.example.demo.repository.BuahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddBuahService {

    @Autowired
    private BuahRepository buahRepository;

    public BaseResponseDto<Buah> addBuah(AddBuahReqDto reqDto) {

        BaseResponseDto response = new BaseResponseDto<>();

        var dataExist = buahRepository.findByNameIgnoreCaseAndIsDeleted(reqDto.getNama(), Boolean.FALSE);
        if (dataExist.isPresent()) {
            response.setResponseCode("01");
            response.setMessage(reqDto.getNama() + " sudah ada dalam list");

            return response;
        }

        Buah buah = new Buah();
        buah.setName(reqDto.getNama());
        buah.setIsDeleted(Boolean.FALSE);
        buah.setCreatedBy("user");
        buah.setCreatedDate(new Date());
        buah.setUpdatedBy("user");
        buah.setUpdatedDate(new Date());

        buahRepository.save(buah);

        response.setResponseCode("00");
        response.setMessage(buah.getName() + " berhasil ditambahkan");
        response.setResult(buah);

        return response;
    }

}
