package com.keyin.TreeLogic;
import java.util.List;
import java.util.Optional;


import com.keyin.TreeLogic.model.TreeRecord;
import com.keyin.TreeLogic.repository.TreeRecordRepository;
import com.keyin.TreeLogic.service.TreeService;
import com.keyin.TreeLogic.controller.TreeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebMvcTest(TreeController.class)
public class TreeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private TreeService treeService;

    @Mock
    private TreeRecordRepository treeRecordRepository;

    @InjectMocks
    private TreeController treeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowInputForm() throws Exception {
        mockMvc.perform(get("/enter-numbers"))
                .andExpect(status().isOk())
                .andExpect(view().name("input-form"));
    }

    @Test
    public void testProcessNumbers() throws Exception {
        int[] numbers = {5, 3, 7, 2, 4};
        String jsonTree = "{\"value\":5,\"left\":{\"value\":3,\"left\":{\"value\":2,\"left\":null,\"right\":null},\"right\":{\"value\":4,\"left\":null,\"right\":null}},\"right\":{\"value\":7,\"left\":null,\"right\":null}}";

        TreeRecord treeRecord = new TreeRecord();
        treeRecord.setTreeJson(jsonTree);

        when(treeService.createBinarySearchTree(any(int[].class))).thenReturn(treeRecord);
        when(treeRecordRepository.save(any(TreeRecord.class))).thenReturn(treeRecord);

        mockMvc.perform(post("/process-numbers")
                        .param("numbers", "5, 3, 7, 2, 4"))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals(jsonTree));
    }

    @Test
    public void testViewPreviousTrees() throws Exception {
        TreeRecord treeRecord = new TreeRecord();
        treeRecord.setId(1L);
        treeRecord.setTreeJson("{\"value\":5,\"left\":null,\"right\":null}");

        when(treeRecordRepository.findAll()).thenReturn(List.of(treeRecord));

        mockMvc.perform(get("/previous-trees"))
                .andExpect(status().isOk())
                .andExpect(view().name("view-trees"))
                .andExpect(model().attributeExists("treeRecords"));
    }

    @Test
    public void testGetTree() throws Exception {
        Long id = 1L;
        TreeRecord treeRecord = new TreeRecord();
        treeRecord.setId(id);
        treeRecord.setTreeJson("{\"value\":5,\"left\":null,\"right\":null}");

        when(treeRecordRepository.findById(id)).thenReturn(Optional.of(treeRecord));

        mockMvc.perform(get("/get-tree")
                        .param("id", id.toString()))
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals("{\"value\":5,\"left\":null,\"right\":null}"));
    }
}
