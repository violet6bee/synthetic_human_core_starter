package com.shavlieva.synthetic_human_core_starter.controller;

import com.shavlieva.synthetic_human_core_starter.DTO.Command;
import com.shavlieva.synthetic_human_core_starter.service.CommandService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/commands")
@RequiredArgsConstructor
public class CommandController {
    private final CommandService commandService;

    @PostMapping("/command")
    public ResponseEntity<String> addCommand (@RequestBody @Valid Command command) {
        commandService.processCommand(command);
        return ResponseEntity.ok("Команда принята");
    }
}
