package ro.fortech.pdfparser.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ro.fortech.pdfparser.entity.BalanceSheetEntity;
import ro.fortech.pdfparser.repository.BalanceSheetLineRepository;
import ro.fortech.pdfparser.repository.BalanceSheetRepository;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service


public class ParserService {


    private BalanceSheetRepository balanceSheetRepository;
    private BalanceSheetLineRepository balanceSheetLineRepository;

    public ParserService(BalanceSheetRepository balanceSheetRepository, BalanceSheetLineRepository balanceSheetLineRepository) {
        this.balanceSheetRepository = balanceSheetRepository;
        this.balanceSheetLineRepository = balanceSheetLineRepository;
    }

    public void addPdfToDatabase(ParsedPdfDto parsedPdfDto) {

        BalanceSheetEntity bal = new BalanceSheetEntity();
        bal = bal.toBalanceSheetEntity(parsedPdfDto);


        balanceSheetRepository.save(bal);
        for (int i = 0; i < bal.getLines().size(); i++)
            balanceSheetLineRepository.save(bal.getLines().get(i));

    }
}



