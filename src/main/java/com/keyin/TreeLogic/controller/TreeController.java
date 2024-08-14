package com.keyin.TreeLogic.controller;

import com.keyin.TreeLogic.model.TreeRecord;
import com.keyin.TreeLogic.repository.TreeRecordRepository;
import com.keyin.TreeLogic.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class TreeController {

    @Autowired
    private TreeService treeService;

    @Autowired
    private TreeRecordRepository treeRecordRepository;

    @GetMapping("/enter-numbers")
    public String showInputForm() {
        return "input-form";
    }

    @PostMapping("/process-numbers")
    @ResponseBody
    public String processNumbers(@RequestParam("numbers") String numbers) {
        String[] numStrings = numbers.split(",");
        int[] numArray = new int[numStrings.length];
        for (int i = 0; i < numStrings.length; i++) {
            numArray[i] = Integer.parseInt(numStrings[i].trim());
        }

        TreeRecord treeRecord = treeService.createBinarySearchTree(numArray);
        treeRecordRepository.save(treeRecord);

        return treeRecord.getTreeJson();
    }

    @GetMapping("/previous-trees")
    @ResponseBody
    public List<TreeRecord> getPreviousTrees() {
        return treeRecordRepository.findAll();
    }

    @GetMapping("/get-tree")
    @ResponseBody
    public String getTree(@RequestParam("id") Long id) {
        Optional<TreeRecord> optionalTreeRecord = treeRecordRepository.findById(id);
        if (optionalTreeRecord.isPresent()) {
            TreeRecord treeRecord = optionalTreeRecord.get();
            return treeRecord.getTreeJson();
        } else {
            return "Tree not found";
        }
    }
}
