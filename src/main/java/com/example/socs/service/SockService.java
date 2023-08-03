package com.example.socs.service;

import com.example.socs.exception.*;
import com.example.socs.model.Sock;
import com.example.socs.repository.SockRepository;
import org.springframework.stereotype.Service;

import static com.example.socs.exception.ErrorMesage.*;

@Service
public class SockService {
    private final SockRepository sockRepository;

    public SockService(SockRepository sockRepository) {
        this.sockRepository = sockRepository;
    }

    public Integer getSocksStock(String color, String operation, Integer cottonPart) {
        validateCottonPart(cottonPart);

        Integer socksCount = null;
        switch (operation) {
            case "moreThan":
                socksCount = sockRepository.getSocksCountByColorAndMoreThanCottonPart(color, cottonPart);
                break;
            case "lessThan":
                socksCount = sockRepository.getSocksCountByColorAndLessThanCottonPart(color, cottonPart);
                break;
            case "equal":
                socksCount = sockRepository.getSocksCountByColorAndEqualCottonPart(color, cottonPart);
                break;
            default:
                throw new UnknownOperationException();
        }

        if (socksCount == null) {
            return 0;
        } else {
            return socksCount;
        }
    }


    public Sock socksIncome(Sock sock) {
        validateCottonPart(sock.getCottonPart());
        validateQuantity(sock.getQuantity());

        Sock socksExist = sockRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        if (socksExist != null) {
            socksExist.setQuantity(socksExist.getQuantity() + sock.getQuantity());
            sock = socksExist;
        }
        sockRepository.save(sock);

        return sock;
    }


    public Sock socksOutcome(Sock sock) {
        validateCottonPart(sock.getCottonPart());
        validateQuantity(sock.getQuantity());

        Sock socksExist = sockRepository.findByColorAndCottonPart(sock.getColor(), sock.getCottonPart());
        if (socksExist == null) {
            throw new NoSuchItemInStockException(NO_SUCH_ITEM_IN_STOCK_MSG);
        } else if (socksExist.getQuantity() < sock.getQuantity()) {
            throw new NotEnoughInStockException(NOT_ENOUGH_IN_STOCK_MSG);
        } else {
            socksExist.setQuantity(socksExist.getQuantity() - sock.getQuantity());
            sock = socksExist;
            sockRepository.save(sock);
        }

        return sock;
    }

    private void validateCottonPart(Integer cottonPart) {
        if (cottonPart < 0 || cottonPart > 100) {
            throw new InvalidCottonPartException(INVALID_COTTON_PART_VALUE_MSG);
        }
    }

    private void validateQuantity(Integer quantity) {
        if (quantity < 1) {
            throw new InvalidSocksQuantityException(INVALID_SOCKS_QUANTITY_VALUE_MSG);
        }
    }
}

