package com.sparta.myselectshop.service;

import com.sparta.myselectshop.dto.FolderResponseDto;
import com.sparta.myselectshop.entity.Folder;
import com.sparta.myselectshop.entity.User;
import com.sparta.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FolderService {
    private final FolderRepository folderRepository;

    // 로그인한 회원에 폴더들 등록
    public void addFolders(List<String> folderNames, User user) {
//        System.out.println("폴더 이름 목록: " + folderNames);
//
//        if (folderNames == null || folderNames.isEmpty()) {
//            throw new IllegalArgumentException("폴더 이름 목록이 비어 있습니다.");
//        }

        // 입력으로 들어온 폴더 이름을 기준으로, 회원이 이미 생성한 폴더들을 조회합니다.
        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);
//        System.out.println("기존 폴더 목록: " + existFolderList.size()); // 기존 폴더의 개수 확인

        List<Folder> folderList = new ArrayList<>();

        for (String folderName : folderNames) {
            // 이미 생성한 폴더가 아닌 경우만 폴더 생성
            if (!isExistFolderName(folderName, existFolderList)) {
                Folder folder = new Folder(folderName, user);
                folderList.add(folder);
            } else {
                throw new IllegalArgumentException("폴더명이 중복되었습니다.");
            }
        }

        folderRepository.saveAll(folderList);
    }

    // 로그인한 회원이 등록된 모든 폴더 조회
    public List<FolderResponseDto> getFolders(User user) {
//        System.out.println("사용자 ID: " + user.getId()); // 사용자 ID 확인
        List<Folder> folderList = folderRepository.findAllByUser(user);

//        if (folderList.isEmpty()) {
//            System.out.println("사용자의 폴더 목록이 비어 있습니다.");
//        } else {
//            System.out.println("폴더 목록이 조회되었습니다.");
//        }

        List<FolderResponseDto> responseDtoList = new ArrayList<>();

        for (Folder folder : folderList) {
            System.out.println("폴더 이름: " + folder.getName()); // 폴더 이름 출력

            responseDtoList.add(new FolderResponseDto(folder));
        }

        return responseDtoList;
    }

    private Boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        // 기존 폴더 리스트에서 folder name 이 있는지?
        for (Folder existFolder : existFolderList) {
            if(folderName.equals(existFolder.getName())) {
                return true;
            }
        }
        return false;
    }

}
