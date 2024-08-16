package com.example.demo.service;

import com.example.demo.dto.AddBuahReqDto;
import com.example.demo.model.BaseResponseDto;
import com.example.demo.model.Buah;
import com.example.demo.repository.BuahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DeleteBuahService {

    @Autowired
    private BuahRepository buahRepository;

    public BaseResponseDto<Buah> deleteBuah(Long id) {

        BaseResponseDto response = new BaseResponseDto<>();

        var data = buahRepository.findByIdAndIsDeleted(id, Boolean.FALSE);
        if (data.isEmpty()) {
            response.setResponseCode("04");
            response.setMessage("Data tidak ditemukan!");

            return response;
        }

        Buah dataDelete = data.get();
        dataDelete.setIsDeleted(Boolean.TRUE);
        dataDelete.setUpdatedDate(new Date());
        dataDelete.setUpdatedBy("user");

        buahRepository.save(dataDelete);

        response.setResponseCode("00");
        response.setMessage(dataDelete.getName() + " berhasil dihapus");

        return response;
    }
}
