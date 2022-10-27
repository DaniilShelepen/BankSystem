package com.daniil.bank.demo.services.impl;

import com.daniil.bank.demo.dal.entity.legal.LegalOffer;
import com.daniil.bank.demo.dal.entity.natural.NaturalOffer;
import com.daniil.bank.demo.dal.repository.LegalOfferRepository;
import com.daniil.bank.demo.dal.repository.NaturalOfferRepository;
import com.daniil.bank.demo.dto.LegalOfferDto;
import com.daniil.bank.demo.dto.NaturalOfferDto;
import com.daniil.bank.demo.services.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final NaturalOfferRepository naturalOfferRepository;
    private final LegalOfferRepository legalOfferRepository;


    @Override
    public void createNaturalOffer(NaturalOfferDto naturalOfferDto) {

        naturalOfferRepository.save(NaturalOffer.builder()
                .description(naturalOfferDto.getDescription())
                .sum(naturalOfferDto.getSum())
                .currency(naturalOfferDto.getCurrency())
                .clientStatus(naturalOfferDto.getClient_status())
                .percentageRate(naturalOfferDto.getPercentageRate())
                .available(naturalOfferDto.isAvailable())
                .timeMonth(naturalOfferDto.getMonths())
                .build());

    }

    @Override
    public void createLegalOffer(LegalOfferDto legalOfferDto) {
        legalOfferRepository.save(LegalOffer.builder()
                .description(legalOfferDto.getDescription())
                .sum(legalOfferDto.getSum())
                .currency(legalOfferDto.getCurrency())
                .clientStatus(legalOfferDto.getClient_status())
                .percentageRate(legalOfferDto.getPercentageRate())
                .available(legalOfferDto.isAvailable())
                .timeMonth(legalOfferDto.getMonths())
                .build());
    }


    @Override
    public void refactorNaturalOffer(Long id, NaturalOfferDto naturalOfferDto) {
        NaturalOffer naturalOffer = naturalOfferRepository.findById(id).orElseThrow(RuntimeException::new);//todo сюда свою ошибку впихни

        naturalOffer.setAvailable(naturalOfferDto.isAvailable());
        naturalOffer.setSum(naturalOfferDto.getSum());
        naturalOffer.setClientStatus(naturalOfferDto.getClient_status());
        naturalOffer.setDescription(naturalOfferDto.getDescription());
        naturalOffer.setCurrency(naturalOfferDto.getCurrency());
        naturalOffer.setPercentageRate(naturalOfferDto.getPercentageRate());

        naturalOfferRepository.save(naturalOffer);

    }

    @Override
    public void refactorLegalOffer(Long id, LegalOfferDto legalOfferDto) {

        LegalOffer legalOffer = legalOfferRepository.findById(id).orElseThrow(RuntimeException::new);//todo сюда свою ошибку впихни

        legalOffer.setAvailable(legalOfferDto.isAvailable());
        legalOffer.setSum(legalOfferDto.getSum());
        legalOffer.setClientStatus(legalOfferDto.getClient_status());
        legalOffer.setDescription(legalOfferDto.getDescription());
        legalOffer.setCurrency(legalOfferDto.getCurrency());
        legalOffer.setPercentageRate(legalOfferDto.getPercentageRate());

        legalOfferRepository.save(legalOffer);

    }

    @Override
    public void deleteNaturalOffer(Long id) {
        naturalOfferRepository.findById(id).orElseThrow(RuntimeException::new);//todo своя ошибка
        naturalOfferRepository.deleteById(id);

    }

    @Override
    public void deleteLegalOffer(Long id) {
        legalOfferRepository.findById(id).orElseThrow(RuntimeException::new);//todo своя ошибка
        legalOfferRepository.deleteById(id);
    }
}
