package com.example.socs.controller;


import com.example.socs.model.Sock;
import com.example.socs.service.SockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/socks")
public class SockController {
    private SockService sockService;

    @Operation(
            summary = "Получить общее количество носков на складе",
            parameters = {
                    @Parameter(name = "color", description = "цвет носков"),
                    @Parameter(name = "operation", description = "оператор сравнения значения количества хлопка в составе носков, " +
                            "одно значение из: moreThan, lessThan, equal"),
                    @Parameter(name = "cottonPart", description = "значение процента хлопка в составе носков")
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error"
                    )
            },
            tags = "Носки"
    )
    @GetMapping
    public ResponseEntity<Integer> getSocksStock(
            @RequestParam String color,
            @RequestParam String operation,
            @RequestParam Integer cottonPart
    ) {
        return ResponseEntity.ok(sockService.getSocksStock(color, operation, cottonPart));
    }

    @Operation(
            summary = "Зарегистрировать приход носков на склад",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error"
                    )
            },
            tags = "Носки"
    )
    @PostMapping("/income")
    public ResponseEntity<Sock> socksIncome(@RequestBody Sock sock) {
        return ResponseEntity.ok(sockService.socksIncome(sock));
    }

    @Operation(
            summary = "Зарегистрировать отпуск носков со склада",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error"
                    )
            },
            tags = "Носки"
    )
    @PostMapping("/outcome")
    public ResponseEntity<Sock> socksOutcome(@RequestBody Sock sock) {
        return ResponseEntity.ok(sockService.socksOutcome(sock));
    }
}

