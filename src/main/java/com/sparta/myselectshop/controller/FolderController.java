//package com.sparta.myselectshop.controller;
//
//import com.sparta.myselectshop.dto.FolderRequestDto;
//import com.sparta.myselectshop.dto.FolderResponseDto;
//import com.sparta.myselectshop.security.UserDetailsImpl;
//import com.sparta.myselectshop.service.FolderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.List;
//
//@Controller
//public class FolderController {
//
//    @Autowired
//    private final FolderService folderService;
//
//    public FolderController(FolderService folderService) {
//        this.folderService = folderService;
//    }
//
//    @PostMapping("/folders")
//    public void addFolders(@RequestBody FolderRequestDto folderRequestDto,
//                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
//
//        List<String> folderNames = folderRequestDto.getFolderNames();
//
//        folderService.addFolders(folderNames, userDetails.getUser());
//    }
//
//    // 회원이 등록한 모든 폴더 조회
//    @GetMapping("/folders")
//    public List<FolderResponseDto> getFolders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
//        return folderService.getFolders(userDetails.getUser());
//    }
//
//}
